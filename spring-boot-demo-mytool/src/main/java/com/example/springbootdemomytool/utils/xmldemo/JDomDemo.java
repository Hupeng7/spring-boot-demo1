package com.example.springbootdemomytool.utils.xmldemo;

/**
 * @ClassName JDomDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/3 16:02
 * @Version 1.0
 */

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.*;

/**
 * @author JDOM 生成与解析XML文档
 */
public class JDomDemo implements XmlDocument {

    public static void main(String[] args) {
        JDomDemo jDomDemo = new JDomDemo();
        jDomDemo.createXml("xml1.xml");

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<employees><employee><name>ddvip</name><sex>m</sex><age>23</age></employee></employees>";

        String xmlStr1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<root>\n" +
                "\t<head>\n" +
                "<channel>1101</channel>\n" +
                "<currentBusinessCode>CF004106</currentBusinessCode>\n" +
                "\t\t<flowNo>201610080000229845</flowNo>\n" +
                "\t\t<reqTime>20161008105548</reqTime>\n" +
                "\t\t<sign></sign>\n" +
                "\t\t<signFlag></signFlag>\n" +
                "\t</head>\n" +
                "\t<reqdata>\n" +
                "\t\t<idNo>421087198601130073</idNo>\n" +
                "\t\t<cellNo>15572183677</cellNo>\n" +
                "\t\t<userName>孟如</userName>\n" +
                "\t\t<gid>brid</gid>\n" +
                "\t</reqdata>\n" +
                "</root>";


        try {
            Map<String, Object> map = doXMLParse(xmlStr1);
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            }


            Set<Map.Entry<String, Object>> entry = map.entrySet();
            for (Map.Entry<String, Object> entry1 : map.entrySet()
            ) {
                System.out.println(entry1.getKey() + "====" + entry1.getValue());
            }
            System.out.println();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createXml(String fileName) {
        Document document;
        Element root;
        root = new Element("employees");
        document = new Document(root);
        Element employee = new Element("employee");
        root.addContent(employee);
        Element name = new Element("name");
        name.setText("ddvip");
        employee.addContent(name);
        Element sex = new Element("sex");
        sex.setText("m");
        employee.addContent(sex);
        Element age = new Element("age");
        age.setText("23");
        employee.addContent(age);
        XMLOutputter XMLOut = new XMLOutputter();
        try {
            XMLOut.output(document, new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parserXml(String fileName) {
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document document = builder.build(fileName);
            Element employees = document.getRootElement();
            List employeeList = employees.getChildren("employee");
//            for (int i = 0; iElement employee = (Element) employeeList.get(i);
//            List employeeInfo = employee.getChildren();
//            for (int j = 0; jSystem.out.println(((Element) employeeInfo.get(j)).getName() + ":" + ((Element) employeeInfo.get(j)).getValue());

        } catch (JDOMException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //xml解析
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

}

