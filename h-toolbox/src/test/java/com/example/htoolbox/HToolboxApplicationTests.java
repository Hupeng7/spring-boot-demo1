package com.example.htoolbox;

import com.example.htoolbox.utils.pdfutil.PdfUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HToolboxApplication.class)
public class HToolboxApplicationTests {

    @Test
    public void convertText() {
        String pdfPath = "D:\\work\\doc\\paper\\编程环境和软件工具安装手册.pdf";
        PdfUtil.convertText(pdfPath);
    }

    @Test
    public void pdf2doc() {
        String pdfPath = "E:\\02-work\\book\\Spring Cloud Alibaba笔记.pdf";
        PdfUtil.pdf2doc(pdfPath);
    }



}
