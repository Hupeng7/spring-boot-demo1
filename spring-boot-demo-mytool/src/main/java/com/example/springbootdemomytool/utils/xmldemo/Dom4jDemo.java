package com.example.springbootdemomytool.utils.xmldemo;

/**
 * @ClassName Dom4jDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/3 15:48
 * @Version 1.0
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Dom4j 生成XML文档与解析XML文档
 */
public class Dom4jDemo implements XmlDocument {

    @Override
    public void createXml(String fileName) {
        Document document = DocumentHelper.createDocument();
        Element employees = document.addElement("employees");
        Element employee = employees.addElement("employee");
        Element name = employee.addElement("name");
        name.setText("ddvip");
        Element sex = employee.addElement("sex");
        sex.setText("m");
        Element age = employee.addElement("age");
        age.setText("29");
        try {
            Writer fileWriter = new FileWriter(fileName);
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }


    }


    @Override
    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Element employees = document.getRootElement();
            for (Iterator i = employees.elementIterator(); i.hasNext(); ) {
                Element employee = (Element) i.next();
                for (Iterator j = employee.elementIterator(); j.hasNext(); ) {
                    Element node = (Element) j.next();
                    System.out.println(node.getName() + ":" + node.getText());
                }

            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("dom4j parserXml");
    }

    public static void main(String[] args) {
        String xmlStr = "\n" +
                "<note>\n" +
                "<to>George</to>\n" +
                "<from>John</from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget the meeting!</body>\n" +
                "</note>";
        try {
            Map<String, String> map = xmlToMap(xmlStr, "UTF-8");
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Map<String, String> xmlToMap(String xml, String charset) throws UnsupportedEncodingException, DocumentException {

        Map<String, String> respMap = new HashMap<String, String>();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new ByteArrayInputStream(xml.getBytes(charset)));
        Element root = doc.getRootElement();
        xmlToMap(root, respMap);
        return respMap;
    }

    public static Map<String, String> xmlToMap(Element tmpElement, Map<String, String> respMap) {
//        ArrayList<String> strings = new ArrayList<>();
//        for (String s : strings
//        ) {
//
//        }
        if (tmpElement.isTextOnly()) {
            respMap.put(tmpElement.getName(), tmpElement.getText());
            return respMap;
        }

        @SuppressWarnings("unchecked")
        Iterator<Element> eItor = tmpElement.elementIterator();
        while (eItor.hasNext()) {
            Element element = eItor.next();
            xmlToMap(element, respMap);
        }
        return respMap;

    }
}