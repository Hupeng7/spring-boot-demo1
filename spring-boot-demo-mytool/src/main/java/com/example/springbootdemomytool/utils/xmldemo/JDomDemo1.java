package com.example.springbootdemomytool.utils.xmldemo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JDom
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/4 10:08
 * @Version 1.0
 */
public class JDomDemo1 {

    public static void main(String[] args) {

        String xmlStr1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<root>\n" +
                "\t<head>\n" +
                "\t\t<flowNo>201610080000229845</flowNo>\n" +
                "\t\t<reqTime>20161008105548</reqTime>\n" +
                "\t</head>\n" +
                "\t<reqdata>\n" +
                " <thirdSeq>20200608001</thirdSeq>\n" +
                "    <loanType>JFD100</loanType>\n" +
                "    <docCde>DZY052</docCde>\n" +
                "    <docName>10452019030112345678_ DZY052.png</docName>\n" +
                "   <docSourceUrl><![CDATA[https://extapi.fadada.com/api2//getdocs.action?app_id=001004&timestamp=20200603100507&v=2.0&msg_digest=QUE4RkI3MUM5Nzg4ODU5QjVCNDRDRTBBMDVGQUQwRDE2OEU3MDlBRQ==&send_app_id=null&transaction_id=df9179c216064890a623c13b8131f3bc]]></docSourceUrl>\n" +
                "\t</reqdata>\n" +
                "</root>\n";

        String xmlStr2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><root><head><flowNo>201610080000229845</flowNo><reqTime>20161008105548</reqTime></head><reqdata><thirdSeq>20200608001</thirdSeq><loanType>JFD100</loanType><docCde>DZY052</docCde><docName>10452019030112345678_ DZY052.png</docName><docSourceUrl><![CDATA[https://extapi.fadada.com/api2//getdocs.action?app_id=001004&timestamp=20200603100507&v=2.0&msg_digest=QUE4RkI3MUM5Nzg4ODU5QjVCNDRDRTBBMDVGQUQwRDE2OEU3MDlBRQ==&send_app_id=null&transaction_id=df9179c216064890a623c13b8131f3bc]]></docSourceUrl></reqdata></root>";
        JSONObject jsonObject = XML.toJSONObject(xmlStr1);
        System.out.println("json str: " + jsonObject);

        // String rootStr = jsonObject.getStr("root");
        JSONObject root = jsonObject.get("root", JSONObject.class);

        System.out.println("rootStr: " + root + "\n");

        JSONObject reqdata = root.get("reqdata", JSONObject.class);
        System.out.println("reqdata: " + reqdata);

        String docName = (String) reqdata.get("docName");
        System.out.println("docName: " + docName);
        String docSourceUrl = (String) reqdata.get("docSourceUrl");
        System.out.println("docSourceUrl: " + docSourceUrl);
        String thirdSeq = reqdata.get("thirdSeq").toString();
        System.out.println("thirdSeq: " + thirdSeq);

        try {
            Map map = doXMLParse(xmlStr1);
            Map mapT = new HashMap();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();

                System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
                // 值的hashMap
//                Map map1 = doXMLParse(entry.getValue().toString());
//                Iterator<Map.Entry<String, Object>> iteratorT = map1.entrySet().iterator();
//                while (iteratorT.hasNext()) {
//                    Map.Entry<String, Object> entryT = iteratorT.next();
//                    mapT.put(entryT.getKey(), entryT.getValue());
//                }


            }

            Iterator<Map.Entry<String, Object>> iterator1 = mapT.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<String, Object> entry = iterator1.next();
                System.out.println("f key: " + entry.getKey() + " value: " + entry.getValue());

            }
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

    public static String getRemoteHost() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


}
