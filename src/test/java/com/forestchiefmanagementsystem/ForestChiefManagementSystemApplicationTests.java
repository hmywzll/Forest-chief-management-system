package com.forestchiefmanagementsystem;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ForestChiefManagementSystemApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/forest_chief_management_system?useSSL=false&serverTimezone=GMT%2B8", "root", "1346792580")
                .globalConfig(builder -> {
                    builder.author("hm") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\java\\xm\\ForestChiefManagementSystem\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder
                            .parent("com.forestchiefmanagementsystem") // 设置父包名
//                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\java\\xm\\ForestChiefManagementSystem\\src\\main\\resources\\com\\forestchiefmanagementsystem\\mapper")) // 设置mapperXml生成路径
                            .entity("pojo");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("account") // 设置需要生成的表名
                            .addTablePrefix() // 设置过滤表前缀
                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .logicDeleteColumnName("is_delete");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}
