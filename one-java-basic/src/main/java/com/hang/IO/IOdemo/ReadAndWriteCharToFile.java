package com.hang.IO.IOdemo;

import java.io.*;

/**
 * @author Hang
 * @date 2020-10-08 19:42
 *
 * 用字符流进行读写操作
 */
public class ReadAndWriteCharToFile {
    public static void main(String[] args) throws IOException {
         readAndWriteCharToFile();
//         stringNode();

    }

    public static void readAndWriteCharToFile(){
        Reader reader = null;
        Writer writer = null;

        try {
            File readFile  = new File("D:/测试/FileInputStreamTest.txt");
            reader = new FileReader(readFile );
            File writerFile = new File("D:/测试/FileOutputStreamTest.txt");
            writer = new FileWriter(writerFile);

            char[] byteArray = new char[(int) readFile.length()];
            int size = reader.read(byteArray); //将数据读入一个字节数组，同时返回实际读取的字节数。如果返回-1，表示读到了输入流的末尾。
            System.out.println("大小:" + size + "个字符");
            System.out.println("内容:" + new String(byteArray));
//            writer.write(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stringNode() throws IOException {
        StringReader sr =null;
        StringWriter sw =null;
        try {
            String str = "学习不刻苦 不如卖红薯 ";

            char[] buf = new char[32];
            int hasRead = 0;
            // StringReader将以String字符串为节点读取数据
            sr = new StringReader(str);
            while ((hasRead = sr.read(buf)) > 0) {
                System.out.print(new String(buf, 0, hasRead));
            }
            // 由于String是一个不可变类，因此创建StringWriter时，实际上是以一个StringBuffer作为输出节点
            sw = new StringWriter();
            System.out.println(sw.toString());
            sw.write(buf);
            sw.write("黑夜给了我黑色的眼睛 ");
            sw.write("我却用它寻找光明 ");
            // toString()返回sw节点内的数据
            System.out.println(sw.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sw.close();
            sr.close();
        }
    }
}
