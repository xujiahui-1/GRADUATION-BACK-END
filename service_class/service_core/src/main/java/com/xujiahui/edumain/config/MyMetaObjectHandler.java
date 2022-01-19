package com.xujiahui.edumain.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mybatis plus 自动填充 MyMetaObjectHandler
 */
@Component
public class MyMetaObjectHandler  implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        //gmtCreate,gmtModified是类中的属性名称，不是字段名称
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
