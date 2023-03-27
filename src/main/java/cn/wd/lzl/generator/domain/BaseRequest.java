package cn.wd.lzl.generator.domain;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class BaseRequest {
    /**
     * 创建人账号
     */

    private String createBy;

    /**
     * 创建人ID
     */

    private String createId;
    /**
     * 创建人姓名
     */

    private String createName;

    /**
     * 所属部门ID
     */

    private String deptId;
    /**
     * 所属部门名称
     */

    private String deptName;

    /**
     * 数据权限SQL
     */

    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
