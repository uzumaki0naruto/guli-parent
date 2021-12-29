package com.atguigu.service_statistics;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class code {


    public static void main(String[] args) {
//        需要构建一个代码自动生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
//        配置策略
//        1，获取全局配置
        GlobalConfig gc = new GlobalConfig();
//获取用户目录
        String propertyPath = System.getProperty("user.dir");
//                代码生产到哪个目录
        gc.setOutputDir("E:\\guli-parent\\service\\service_statistics"+"/src/main/java");//gai
        gc.setAuthor("author：吴嘉伟");
        gc.setFileOverride(false); //是否覆盖
        gc.setServiceName("%sService");//去Serv的I前缀
        gc.setIdType(IdType.ASSIGN_ID);//Id生成器，会按照雪花算法自动生成
        gc.setDateType(DateType.ONLY_DATE);//时间类型为date
        gc.setSwagger2(true);//开启swagger
        gc.setOpen(false);//执行文件后不打开该文件所在位置
        autoGenerator.setGlobalConfig(gc);
//        生成数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/guli?serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);
//        包的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("service_statistics");//模块名
        packageConfig.setParent("com.example");                     //注意
        packageConfig.setEntity("entity");//实体包
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);
//策略配置之自动填充
        TableFill createTime = new TableFill ("gmt_create", FieldFill.INSERT);
        TableFill  updateTime = new TableFill ("gmt_modified", FieldFill.INSERT_UPDATE);
        List<TableFill > tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
//        策略配置核心代码
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setInclude("statistics_daily")    //设置需要生成的表
//                .setCapitalMode(true) //全局大写命名
//                .setDbColumnUnderline(true) // 指定表名和字段名是否使用了下划线
                .setEntityLombokModel(true)//自动生成lombok
//                .setTablePrefix(packageConfig.getModuleName()+"t")//生成实体时去掉表前缀
//                .setTablePrefix("t"+"_")
                .setLogicDeleteFieldName("is_deleted")
                .setTableFillList(tableFills)//自动填充
                .setVersionFieldName("version")//乐观锁

                .setNaming(NamingStrategy.underline_to_camel) // 数据库字段下划线转驼峰命令策略
                .setColumnNaming(NamingStrategy.underline_to_camel);//数据库列名下划线转驼峰命令策略
//                .setTablePrefix("tbl_") // 设置表前缀
        AutoGenerator autoGenerator1 = autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
