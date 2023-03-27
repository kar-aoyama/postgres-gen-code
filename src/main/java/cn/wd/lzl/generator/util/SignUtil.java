package cn.wd.lzl.generator.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 消息生成摘要算法
 *
 * @author
 */
public class SignUtil {

    private static String md5Str(String paramStr) {
        byte[] btInput = paramStr.getBytes(StandardCharsets.UTF_8);
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        mdInst.update(btInput);
        byte[] mDbyte = mdInst.digest();

        StringBuilder md5Str = new StringBuilder();
        for (byte paramByte : mDbyte) {
            if (Integer.toHexString(0xFF & paramByte).length() == 1) {
                md5Str.append("0").append(Integer.toHexString(0xFF & paramByte));
            } else {
                md5Str.append(Integer.toHexString(0xFF & paramByte));
            }
        }
        return md5Str.toString().toUpperCase();
    }

    /**
     * 处理字符串添加"" 问题
     */
    private static String dealString(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) return jsonString;
        int length = jsonString.length();
        if (length <= 0) return jsonString;
        char beginChar = jsonString.charAt(0);
        String resultStr = "";
        if ("\"".equals("" + beginChar)) {
            resultStr = jsonString.substring(1, length - 1);
            return resultStr;
        }
        return jsonString;
    }

    /**
     * 根据内容生成摘要
     */
    public static String generateApiSign(Map<String, Object> map) {
        TreeMap<String, Object> sortedMap = new TreeMap<>(map);
        StringBuilder signedStr = new StringBuilder();
        if (!CollectionUtils.isEmpty(sortedMap)) {
            sortedMap.forEach(
                    (key, value) -> {
                        if (!key.equals("dataSign") && !StringUtils.isEmpty(value)) {
                            signedStr.append(key);
                            signedStr.append("=");
                            String jsonString;
                            if (value instanceof Date) {
                                jsonString = DateUtil.formatDateTime((Date) value);
                            } else {
                                jsonString = JSONObject.toJSONString(value, SerializerFeature.WriteMapNullValue);

                            }
                            String dealString = dealString(jsonString);
                            signedStr.append(dealString);
                            signedStr.append("&");
                        }
                    }
            );
        }

        return signedStr.toString().replace("\\n", "").replace("\\r", "").replace("\\", "");
    }

}