package cn.wd.lzl.generator.domain;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;


@Slf4j
public abstract class AuthRepositoryInitializer<T extends BaseRequest> {

    /**
     * 入库前初始化创建者信息
     *
     * @param apiRequest
     */
    public void initCreater(T apiRequest) {
        try {
            Method setCreateId = this.getClass().getMethod("setCreateId", String.class);
            setCreateId.invoke(this, apiRequest.getCreateId());

            Method setCreateBy = this.getClass().getMethod("setCreateBy", String.class);
            setCreateBy.invoke(this, apiRequest.getCreateBy());

            Method setCreateName = this.getClass().getMethod("setCreateName", String.class);
            setCreateName.invoke(this, apiRequest.getCreateName());

            Method setCreateTime = this.getClass().getMethod("setCreateTime", Date.class);
            setCreateTime.invoke(this, new Date());

            Method setUpdateBy = this.getClass().getMethod("setUpdateBy", String.class);
            setUpdateBy.invoke(this, apiRequest.getCreateBy());

            Method setUpdateName = this.getClass().getMethod("setUpdateName", String.class);
            setUpdateName.invoke(this, apiRequest.getCreateName());

            Method setUpdateTime = this.getClass().getMethod("setUpdateTime", Date.class);
            setUpdateTime.invoke(this, new Date());
            
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ("setDeptName".equals(method.getName())) {
                    Method setDeptName = this.getClass().getMethod("setDeptName", String.class);
                    setDeptName.invoke(this, apiRequest.getDeptName());
                }

                if ("setDeptId".equals(method.getName())){
                    Method setDeptId = this.getClass().getMethod("setDeptId", String.class);
                    setDeptId.invoke(this, apiRequest.getDeptId());
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据修改者
     *
     * @param apiRequest
     */
    public void initUpdater(T apiRequest) {
        try {
            Method setUpdateBy = this.getClass().getMethod("setUpdateBy", String.class);
            setUpdateBy.invoke(this, apiRequest.getCreateBy());

            Method setUpdateName = this.getClass().getMethod("setUpdateName", String.class);
            setUpdateName.invoke(this, apiRequest.getCreateName());

            Method setUpdateTime = this.getClass().getMethod("setUpdateTime", Date.class);
            setUpdateTime.invoke(this, new Date());

            // Method getVersion = this.getClass().getMethod("getVersion");
            // Long version = (Long) getVersion.invoke(this);
            // if (null != version) {
            // Method setVersion = this.getClass().getMethod("setVersion", Long.class);
            // setVersion.invoke(this, version++);
            // }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
