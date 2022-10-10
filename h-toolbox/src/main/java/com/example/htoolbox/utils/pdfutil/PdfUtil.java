package com.example.htoolbox.utils.pdfutil;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

/**
 * @ClassName PdfUtil
 * @Description
 * @Author H
 * @Date 2022/9/23 13:48
 * @Version 1.0
 */
@Slf4j
public class PdfUtil {

    /**
     * 将pdf文件转为doc文件
     * 无格式
     *
     * @param pdfPath
     */
    public static void convertText(String pdfPath) {
        PDDocument doc = null;
        OutputStream outputStream = null;
        Writer writer = null;
        PDFTextStripper pdfTextStripper = null;
        try {
            doc = PDDocument.load(new File(pdfPath));
            outputStream = new FileOutputStream(pdfPath.substring(0, pdfPath.indexOf(".")) + ".doc");
            writer = new OutputStreamWriter(outputStream, "UTF-8");
            pdfTextStripper = new PDFTextStripper();
            int pageNumber = doc.getNumberOfPages();
            pdfTextStripper.setSortByPosition(true);
            pdfTextStripper.setStartPage(1);
            pdfTextStripper.setEndPage(pageNumber);
            pdfTextStripper.writeText(doc, writer);
            writer.close();
            doc.close();
        } catch (IOException e) {
            log.error("convert error: {}", e);
        }
        log.info("convert success");
    }

    public static void pdf2doc(String pdfPath) {
        long startTime = System.currentTimeMillis();

        try {
            String wordPath = pdfPath.substring(0, pdfPath.lastIndexOf(".")) + ".docx";
            File file = new File(wordPath);
            FileOutputStream outputStream = new FileOutputStream(file);
            Document document = new Document(pdfPath);
            document.save(outputStream, SaveFormat.DocX);
            outputStream.close();
            removeWatermark(new File(wordPath));
            log.info("time consuming:{}", (System.currentTimeMillis() - startTime));
        } catch (IOException e) {
            log.error("pdf2doc error: {}", e);
        }
    }

    /**
     * 移除文字水印
     *
     * @param file
     * @return
     */
    public static boolean removeWatermark(File file) {
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
            // 段落
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                String text = paragraph.getText();
                if ("Evaluation Only. Created with Aspose.PDF. Copyright 2002-2021 Aspose Pty Ltd.".equals(text)) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    runs.forEach(e -> e.setText("", 0));
                }
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            doc.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            log.error("remove watermark error: {}", e);
        }
        return true;
    }


}
