package com.example.springbootdemomytool.utils.freemarkerdemo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName ClientDemo
 * @Description Freemarker 将html模板生成一个文件
 *     from https://www.jianshu.com/p/7625ab5678fb
 * @Author hup
 * @Date 2020/9/23 16:41
 * @Version 1.0
 */
public class ClientDemo {

    @Test
    public void genFile() throws Exception {
        // step1 创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对应的版本号
        Configuration configuration = new Configuration(Configuration.getVersion());
        // step2 设置模板文件所在路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\project\\spring-boot-demo1\\spring-boot-demo-mytool\\src\\main\\java\\com\\example\\springbootdemomytool\\templates"));
        // step3 设置模板文件使用的字符集。一般就是utf-8
        configuration.setDefaultEncoding("utf-8");
        // step4 加载一个模板，创建一个模板对象 获取模板
        Template template = configuration.getTemplate("hello-1.html");
        // step5 创建一个模板使用的数据集，可以是pojo也可以是map.一般是Map
        Map dataModel = new HashMap();
        dataModel.put("hello", "this is a freemarker test.");
        dataModel.put("name", "Jack");
        dataModel.put("age", "18");
        dataModel.put("sex", "male");
        // step6 创建一个Writer对象，一般创建--FileWriter对象，指定生成的文件名
        Writer writer = new FileWriter(new File("D:/temp/out/hello"+System.currentTimeMillis()+".html"));
        // step7 调用模板对象的process方法输出文件
        template.process(dataModel, writer);
        // step8
        writer.close();


    }
}
