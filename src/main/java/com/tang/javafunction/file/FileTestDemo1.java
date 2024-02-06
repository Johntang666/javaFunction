package com.tang.javafunction.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileTestDemo1 {
    public static void main(String[] args) {
        File sourceFolder = new File("E:\\JavaFiles\\test");
        File zipFile = new File("E:\\JavaFiles\\test.zip");
        try {
            zipDirectory(sourceFolder, zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipDirectory(File sourceFolder, File zipFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        zipFolderContents(sourceFolder, sourceFolder, zos);
        zos.close();
    }

    private static void zipFolderContents(File rootFolder, File currentFolder, ZipOutputStream zos) throws IOException {
        for (File file : currentFolder.listFiles()) {
            if (file.isDirectory()) {
                zipFolderContents(rootFolder, file, zos);
            } else {
                String entryName = getRelativePath(rootFolder, file);
                ZipEntry zipEntry = new ZipEntry(entryName);
                zos.putNextEntry(zipEntry);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                fis.close();
                zos.closeEntry();
            }
        }
    }

    private static String getRelativePath(File rootFolder, File file) {
        String rootFolderPath = rootFolder.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        if (filePath.startsWith(rootFolderPath)) {
            return filePath.substring(rootFolderPath.length() + 1);
        } else {
            return file.getName();
        }
    }
}
