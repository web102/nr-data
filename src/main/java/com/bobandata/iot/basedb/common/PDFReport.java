package com.bobandata.iot.basedb.common;

import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/11/22 22:04
 * @Description:
 */

@RestController
@RequestMapping("/pdf")
public class PDFReport {
    private static final Logger logger = LoggerFactory.getLogger(PDFReport.class);

    @PostMapping("/pdfReport")
    public Result pdfReport(@RequestParam(value = "type") String type,@RequestBody String[] msg) throws IOException {
        if(msg.length==0){
            return new Result(Constant.MethodResult.FAIL.getMethodResult(), true);
        }

        Document document = new Document();// 建立一个Document对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String pdfName = sdf.format(new Date());


        String path = ".\\pdfReport\\";
        if (!new File(path).isDirectory()) {
            new File(path).mkdir();
        }
        File file ;
        int i = 0;
        do {
            String fName = i==0?pdfName:pdfName+"("+(i)+")";
            i++;
            file = new File(path,fName+type+".pdf");
        }while (file.exists());

        document.setPageSize(PageSize.A4);// 设置页面大小
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }


//中文字体
        BaseFont bfChinese = null;
        try {
            bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Font font = new Font(bfChinese, 10, Font.NORMAL);

        for (String message : msg) {
            Paragraph paragraph = new Paragraph(message,font);
            try {
                document.add(paragraph);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
    }
}
