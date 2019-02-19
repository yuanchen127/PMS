package org.pms.javabase.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerHandler {
    public static void main(String[] args) throws IOException {
        autoGenerator();
    }

    public static void autoGenerator() throws IOException {
        // 参数值
        Map<String, Object> map = new HashMap<String, Object>();
        org.ike.pms.api.freemarker.Base base = new org.ike.pms.api.freemarker.Base("String", "name");
        map.put("info", base);
//        map.put("type", "String");
//        map.put("name", "testName");
        map.put("package", "org.ike.pms.api.freemarker");
        /** 使用clazz */
        org.ike.pms.api.freemarker.Clazz clazz = new org.ike.pms.api.freemarker.Clazz();
        org.ike.pms.api.freemarker.Clazz.packageName = "org.ike.pms.api.freemarker";
        clazz.addField("String", "name");
        /** end */
        // 模板目录
        String templateDirectory = "src/main/resources/template";
        // 模板名称
        String templateFile = "testTemplate.ftl";

        // 模板生成后存放目录
//        String targetPath = "C:/Users/Administrator/Desktop/test";
        String targetPath = "src/main/java/org/ike/pms/api/freemarker";
        // 模板生成后新文件名
        String fileName = "Ntest.java";

        map.put("class", fileName.split("\\.")[0]);
        org.ike.pms.api.freemarker.Clazz.className = fileName.split("\\.")[0];
//        map.put("class", fileName);
        // 创建文件夹
        new File(targetPath).mkdirs();
        File nFile = new File(targetPath +"/"+ fileName);
        if (nFile.exists()) {
            throw new RuntimeException("File \'"+fileName+"\' already exists");
        }

        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);

            Template template = getConfiguration(templateDirectory).getTemplate(templateFile, "UTF-8");
//            template.process(map, writer);
            template.process(clazz, writer);
            System.out.println("File \'"+targetPath +"/"+ fileName+"\' is created");
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.flush();
            writer.close();
        }
    }

    private static Configuration getConfiguration(String templateDirectory) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        try {
            configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            configuration.setDirectoryForTemplateLoading(new File(templateDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return configuration;
    }
}
