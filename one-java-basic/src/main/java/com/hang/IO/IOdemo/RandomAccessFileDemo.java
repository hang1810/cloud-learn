package com.hang.IO.IOdemo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Hang
 * @date 2020-10-08 20:04
 *
 * 随机读写文件 使用 RandomAccessFile 可以实现对文件的随机读取，主要是通过 seek（） 方法实现指针偏移。
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        randomAccessFileReadAndWrite();
    }
    public static void randomAccessFileReadAndWrite() throws IOException {
        RandomAccessFile randomAccessFile =null;
        try {
            // 创建一个RandomAccessFile对象
            randomAccessFile = new RandomAccessFile("D:/测试/FileInputStreamTest.txt", "rw");
            // 通过seek方法来移动指针
            randomAccessFile.seek(10);
            // 获取当前指针
            long pointerBegin = randomAccessFile.getFilePointer();
            // 从当前指针开始读
            byte[] contents = new byte[30];
            randomAccessFile.read(contents);
            long pointerEnd = randomAccessFile.getFilePointer();
            System.out.println("pointerBegin:" + pointerBegin + " pointerEnd:" + pointerEnd  );
            System.out.println( new String(contents)  );
            randomAccessFile.seek(30);
            // 获取当前指针
            long begin = randomAccessFile.getFilePointer();
            randomAccessFile.write(contents);
            long end = randomAccessFile.getFilePointer();
            System.out.println("begin:" + begin + "end:" + end  );
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            randomAccessFile.close();
        }
    }
}
