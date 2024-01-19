package com.tang.javafunction.utils;


import com.aspose.cells.License;
import com.aspose.cells.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Aspose excel转pdf工具类
 */
@Component
public class ExcelAsposeUtil {

    @Autowired
    ResourceLoader resourceLoader;

    private License aposeLic;

    private  InputStream stream;

    private  Logger logger = LoggerFactory.getLogger(ExcelAsposeUtil.class);

    public boolean getLicense(){
        boolean result = false;
        Resource resource = resourceLoader.getResource("classpath:license.xml");
        aposeLic = new License();
        try {
            stream = resource.getInputStream();
            aposeLic.setLicense(stream);
            result = true;
        } catch (Exception e) {

            logger.error("ExcelAspose license 加载失败"+e.toString());
        }
        return result;
    }

    /**
     * excel 转为pdf 输出。
     * @param body
     * @return
     */
    public ByteArrayOutputStream excelToPdf(byte[] body) throws Exception {
        if (!getLicense()){
            return null;
        }
        Workbook excel = new Workbook(new ByteArrayInputStream(
                body));// 原始excel路径
        ByteArrayOutputStream outPDF = new ByteArrayOutputStream();
        com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
        pdfSaveOptions.setOnePagePerSheet(true);
        int[] autoDrawSheets={3};
        //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
        autoDraw(excel,autoDrawSheets);
        int[] showSheets={0};
        //隐藏workbook中不需要的sheet页。
        printSheetPage(excel,showSheets);
        excel.save(outPDF, pdfSaveOptions);
        return outPDF;
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     * @param wb
     * @param page 自动拉伸的页的sheet数组
     */
    private static void autoDraw(Workbook wb,int[] page){
        if(null!=page&&page.length>0){
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }


    /**
     * 隐藏workbook中不需要的sheet页。
     * @param wb
     * @param page 显示页的sheet数组
     */
    private static void printSheetPage(Workbook wb,int[] page){
        for (int i= 1; i < wb.getWorksheets().getCount(); i++)  {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if(null==page||page.length==0){
            wb.getWorksheets().get(0).setVisible(true);
        }else{
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }

}