package com.tang.javafunction;

import cn.hutool.core.io.FileUtil;
import com.tang.javafunction.utils.ExcelAsposeUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;

@SpringBootTest
class JavaFunctionApplicationTests {
    @Resource
    ExcelAsposeUtil excelAsposeUtil;

    @Test
    void contextLoads() {
        byte[] readBytes = FileUtil.readBytes("E:\\javaFunction\\转换PDF.xlsx");
        try {
            // 文件写入
            FileOutputStream fileOutputStream = new FileOutputStream("E:\\javaFunction\\转换PDF.pdf");
            excelAsposeUtil.excelToPdf(readBytes).writeTo(fileOutputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
