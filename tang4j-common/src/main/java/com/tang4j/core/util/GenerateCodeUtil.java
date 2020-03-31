package com.tang4j.core.util;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.tang4j.core.support.GenerateConfig;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GenerateCodeUtil {

    /**
     * 根据数据库表名自动生成 bean mapper service impl文件的工具类
     *
     * @param generateConfig 配置参数类
     */
    public static void generateByTables(GenerateConfig generateConfig) {
        //设置配置信息
        GlobalConfig config = new GlobalConfig();//全局配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();//数据源配置
        StrategyConfig strategyConfig = new StrategyConfig();//策略配置
        PackageConfig packageConfig = new PackageConfig();//包配置
        TemplateConfig templateConfig = new TemplateConfig();//模板配置
        //通过反射检验参数是否齐全或者符合规范
        Field[] fields = generateConfig.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(generateConfig);
                if (!field.getName().contains("super")) {
                    if (DataUtil.isEmpty(obj)) {
                        throw new RuntimeException(generateConfig.getClass().getName() + ":" + field.getName() + "沒有可用的值");
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
            field.setAccessible(false);
        }
        //数据源配置
        dataSourceConfig.setDbType(generateConfig.getDbType())
                .setUrl(generateConfig.getDbUrl())
                .setUsername(generateConfig.getDbUser())
                .setPassword(generateConfig.getDbPassword())
                .setDriverName(generateConfig.getDbDriverName());

        //策略配置
        //获取父类字段 父类字段在子类实体化时忽略
        @SuppressWarnings("unchecked")
        Field[] allDeclaredFields = ReflectUtil.getAllDeclaredFields(generateConfig.getSuperEntityClass());
        String[] superEntityColumns = new String[allDeclaredFields.length];
        for (int i = 0; i < allDeclaredFields.length; i++) {
            superEntityColumns[i] = UnderscoreUtil.underscoreName(allDeclaredFields[i].getName()).toLowerCase();
        }
        strategyConfig
                .setCapitalMode(true)//驼峰命名
                .setSuperEntityClass(generateConfig.getSuperEntityClass().getName())
                .setSuperMapperClass(generateConfig.getSuperMapperClass().getName())
                .setSuperServiceClass(generateConfig.getSuperServiceClass().getName())
                .setSuperServiceImplClass(generateConfig.getSuperServiceImplClass().getName())
                .setSuperControllerClass(generateConfig.getSuperControllerClass().getName())
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)//【实体】是否为LomBok模型（默认 false）
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)//驼峰命名
                .setRestControllerStyle(true)//设置RestController注解
                    //自定义实体，公共字段（设置了这个会影响xml字段生成完整，所以使用injectionConfig）
//                .setSuperEntityColumns(superEntityColumns)//
                .setInclude(generateConfig.getTableNameArr());//修改替换成你需要的表名，多个表名传数组

        //全局配置
        config.setActiveRecord(false)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setDateType(DateType.ONLY_DATE)//日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
                .setSwagger2(true)
                .setIdType(IdType.ID_WORKER)
                .setServiceName("%sService")
                .setAuthor(generateConfig.getAuthorName())//开发者
                .setOutputDir(generateConfig.getOutputDir())
                .setFileOverride(true);//是否覆盖已有文件 默认false
        //包配置
        packageConfig
                .setParent(generateConfig.getPackageName())
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model");//xml会默认生成在mapper下的xml文件里面

        //模板配置
        templateConfig.setXml("templates/mapper.xml.vm");
        templateConfig.setEntity("templates/entity.java.vm");
        templateConfig.setController("templates/controller.java.vm");

        //校验规则配置（这是模拟数据）
        String validRulesJSONStr = "{\n" +
                "\n" +
                "\t\"nickname\": [{\n" +
                "\t\t\t\"name\": \"nickname\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"required\": true,\n" +
                "\t\t\t\"message\": \"缺少field1参数\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"min\": 5,\n" +
                "\t\t\t\"max\": 10,\n" +
                "\t\t\t\"message\": \"长度必须满足5-10个字符\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"regex\": \"\",\n" +
                "\t\t\t\"message\": \"日期格式不正确\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"field2\": [{\n" +
                "\t\t\t\"name\": \"field2\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"required\": true,\n" +
                "\t\t\t\"message\": \"缺少field2参数\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"min\": 5,\n" +
                "\t\t\t\"max\": 10,\n" +
                "\t\t\t\"message\": \"长度必须满足5-10个字符\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"regex\": \"\",\n" +
                "\t\t\t\"message\": \"身份证号格式不正确\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        JSONObject validRules = JSONObject.parseObject(validRulesJSONStr);


        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                //自定义配置，在模版中cfg.superColumns 获取
                // TODO 这里解决子类会生成父类属性的问题，在模版里会用到该配置
                map.put("superColumns", superEntityColumns);
                //字段校验规则,，在模版中cfg.validRules 获取
                // TODO 这里解决字段校验规则，在模版里会用到该配置
                map.put("validRules", validRules);
                this.setMap(map);
            }
        };
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig).execute();
    }


}
