package org.ike.pms.mybatisplus.mybaitsplusdemo.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class generator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java")
                .setAuthor("ike")
                .setIdType(IdType.ID_WORKER_STR)
                .setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/pms?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC")
                // dsc.setSchemaName("public");
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456");

        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent("org.ike.pms.mybatisplus.mybaitsplusdemo.template")
                .setController("controller")
                .setEntity("model")
                .setMapper("dao")
                .setService("service")
//                .setServiceImpl("service.impl")
                .setXml("dao.mapping");


        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setInclude("t_user")
//                .setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(packageConfig.getModuleName() + "_");


        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        String entityTemplate = "/template/entity.vm";
        String serviceTemplate = "/template/service.vm";
        String serviceImplTemplate = "/template/serviceImpl.vm";
        String mappingTemplate = "/template/mapping.vm";
        fileOutConfigList.add(new FileOutConfig(entityTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return (globalConfig.getOutputDir()+packageConfig.getParent()+"/"+packageConfig.getEntity()
                        + "/" + tableInfo.getEntityName()).replaceAll("\\.","/") + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig(serviceTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return (globalConfig.getOutputDir()+packageConfig.getParent()+"/"+packageConfig.getService()
                        + "/" + tableInfo.getServiceName()).replaceAll("\\.","/") + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig(serviceImplTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return (globalConfig.getOutputDir()+packageConfig.getParent()+"/"+packageConfig.getServiceImpl()
                        + "/" + tableInfo.getServiceImplName()).replaceAll("\\.","/") + StringPool.DOT_JAVA;
            }
        });
        fileOutConfigList.add(new FileOutConfig(mappingTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return (globalConfig.getOutputDir()+packageConfig.getParent()+"/"+packageConfig.getXml()
                        + "/" + tableInfo.getXmlName()).replaceAll("\\.","/") + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(fileOutConfigList)
                        .setFileCreate(new IFileCreate() {
                            @Override
                            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                                checkDir(filePath);
                                //对于已存在的文件，只需重复生成 entity
                                File file = new File(filePath);
                                boolean exist = file.exists();
                                if (exist) {
                                    if (FileType.ENTITY == fileType || FileType.XML == fileType) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                                if (filePath.endsWith("Controller.java")) {
                                    return false;
                                }
                                //不存在的文件都需要创建
                                return  true;
                            }
                        });

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(entityTemplate)
                .setXml(mappingTemplate)
                .setService(serviceTemplate)
                .setServiceImpl(serviceImplTemplate);

        autoGenerator.setStrategy(strategyConfig)
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .execute();
    }
}
