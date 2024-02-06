package com.tang.javafunction.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SimplifiedFileTestDemo1 {
    public static void main(String[] args) {
        try {
            zipDirectory(new File("E:\\JavaFiles\\test"), new File("E:\\JavaFiles\\test.zip"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipDirectory(File sourceFolder, File zipFile) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            zipFolderContents(sourceFolder, sourceFolder, zos);
        }
    }

    private static void zipFolderContents(File rootFolder, File currentFolder, ZipOutputStream zos) throws IOException {
        for (File file : currentFolder.listFiles()) {
            if (file.isDirectory()) {
                zipFolderContents(rootFolder, file, zos);
            } else {
                zos.putNextEntry(new ZipEntry(getRelativePath(rootFolder, file)));
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                }
                zos.closeEntry();
            }
        }
    }

    private static String getRelativePath(File rootFolder, File file) {
        String rootFolderPath = rootFolder.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        return filePath.startsWith(rootFolderPath) ? filePath.substring(rootFolderPath.length() + 1) : file.getName();
    }
}