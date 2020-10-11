package com.hang.IO.IOdemo;

import java.io.*;

/**
 * @author Hang
 * @date 2020-10-08 17:37
 *
 * 用字节流读写文件
 */
public class ReadAndWriteByteToFile {
    public static void main(String[] args) {
        try {
            readAndWriteByteToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readAndWriteByteToFile() throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream("D:/测试/FileInputStreamTest.txt");
            os = new FileOutputStream("D:/测试/FileOutStreamTest.txt");
            byte[] buf = new byte[4];
            int hasRead = 0 ;
            while ((hasRead = is.read(buf))!=-1){
                os.write(buf, 0, hasRead);
            }
            System.out.println("write success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
           is.close();
           os.close();
        }
    }
}
