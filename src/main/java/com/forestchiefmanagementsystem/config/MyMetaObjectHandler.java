package com.forestchiefmanagementsystem.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 字段填充插件
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        metaObject.setValue("isDelete",0);
        this.strictInsertFill(metaObject, "isDelete", Integer.class, 0); // 起始版本 3.3.0(推荐)
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
        // 或者
        this.strictInsertFill(metaObject, "updateUser", String.class,"1"); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictInsertFill(metaObject, "createUser", String.class, "1"); // 起始版本 3.3.3(推荐)
        // 或者
//        this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
        // 或者
        this.strictUpdateFill(metaObject, "updateUser", String.class,"1"); // 起始版本 3.3.3(推荐)
        // 或者
//        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }


}