package org.ike.pms.mybatisplus.mybaitsplusdemo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.io.File;
import java.nio.file.Paths;

public class OtherGenerator {

    private static String packageName = "org.ike.pms.mybatisplus.mybaitsplusdemo";
    private static String outDir = System.getProperty("user.dir") + "/src/main/java";
    private static String entity = "entity";
    private static String mapper = "dao";
    private static String service = "service";
    private static String impl = "service.impl";
    private static String controller = "controller";
    private static String xml = "dao.xml";
    private static boolean isOverEntity = true;
    private static boolean isOverController = false;
    private static boolean isOverService = false;
    private static boolean isOverServiceImpl = false;
    private static boolean isOverMapper = false;
    private static boolean isOverXml = true;

    private static String entityVM = "/templates/entity.vm";
    private static String controllerVM = "/templates/controller.vm";
    private static String serviceVM = "";
    private static String serviceImplVM = "";
    private static String mapperVM = "";
    private static String xmlVM = "";

    private static String[] baseDir = {entity, mapper, service, impl, controller};

    public static void main(String[] args) {
        //user -> UserService, 设置成true: user -> IUserService
        generateByTables(false, packageName,"book", "t_user");
    }

    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/pms?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(false)
                .setEntityLombokModel(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .entityTableFieldAnnotationEnable(true)
                .setNaming(NamingStrategy.underline_to_camel)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);
        config.setActiveRecord(true)
                .setAuthor("ike")

                .setOutputDir(outDir)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setFileOverride(true)
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList;
                .setBaseColumnList(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        PackageConfig pcf = initPackage();

        TemplateConfig tc = initTemplateConfig(packageName);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pcf)
                .setTemplate(tc)
                .execute();
    }

    private static TemplateConfig initTemplateConfig(String packageName) {
        TemplateConfig tc = new TemplateConfig();
        for (String tmp : baseDir) {
//            initVM(tc);
            File file = new File(Paths.get(outDir, String.join("/", packageName.split("\\.")), tmp).toString());
            String[] list = file.list();
            if (list != null && list.length > 0) {
                if (!isOverController) {
                    tc.setController(null);
                }
                if (!isOverService) {
                    tc.setService(null);
                }
                if (!isOverServiceImpl) {
                    tc.setServiceImpl(null);
                }
                if (!isOverEntity) {
                    tc.setEntity(null);
                }
                if (!isOverMapper) {
                    tc.setEntity(null);
                }
                if (!isOverXml) {
                    tc.setXml(null);
                }
            }
        }
        return tc;
    }

    private static void initVM(TemplateConfig tc) {
        if (stringIsNotNull(entityVM)) {
            tc.setEntity(entityVM);
        }
        if (stringIsNotNull(mapperVM)) {
            tc.setMapper(mapperVM);
        }
        if (stringIsNotNull(serviceImplVM)) {
            tc.setServiceImpl(serviceImplVM);
        }
        if (stringIsNotNull(serviceVM)) {
            tc.setService(serviceVM);
        }
        if (stringIsNotNull(xmlVM)) {
            tc.setXml(xmlVM);
        }
        if (stringIsNotNull(controllerVM)) {
            tc.setController(controllerVM);
        }
    }

    private static boolean stringIsNotNull(String s) {
        if (null != s && !s.equals("") && s.length() > 0 && s.trim().length() > 0) {
            return true;
        }
        return false;
    }

    private static PackageConfig initPackage() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);
        packageConfig.setService(service);
        packageConfig.setServiceImpl(impl);
        packageConfig.setController(controller);
        packageConfig.setEntity(entity);
        packageConfig.setXml(xml);
        return packageConfig;
    }
}

