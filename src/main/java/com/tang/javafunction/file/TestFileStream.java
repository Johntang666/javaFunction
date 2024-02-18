package com.tang.javafunction.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/2/6 17:03
 */
public class TestFileStream {
    public static void main(String[] args) {
        new TestFileStream().testBufferedReader();
    }

    public void testBufferedReader() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int read;
            while ((read = reader.read()) != -1) {
                System.out.println("read = " + (char) read);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }
    }

    /*public void testInputStream() {
        // 1. 读取文件
        try (BufferedReader reader = new BufferedReader(new FileReader("path_to_file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 读取文件夹
        File folder = new File("path_to_folder");
        Arrays.stream(folder.listFiles()).forEach(System.out::println);

        // 3. 读取网络资源
        *//*try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://example.com").openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*//*

        // 4. 读取内存中的数据
        byte[] data = "Hello, world!".getBytes(StandardCharsets.UTF_8);
        try (InputStream stream = new ByteArrayInputStream(data)) {
            int b;
            while ((b = stream.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
