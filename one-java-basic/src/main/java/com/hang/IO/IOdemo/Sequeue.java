package com.hang.IO.IOdemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

/**
 * @author Hang
 * @date 2020-10-09 15:02
 * 将多个输入流当成一个输入流依次读取
 */
public class Sequeue {
    public static void main(String[] args) throws IOException {
        sequeue();
    }
    public static void sequeue() throws IOException {
        FileInputStream fistream1 =null;
        FileInputStream fistream2 =null;
        SequenceInputStream sistream =null;
        FileOutputStream fostream =null;
        try {
            fistream1 = new FileInputStream("D:/测试/A.txt");
            fistream2 = new FileInputStream("D:/测试/B.txt");
            sistream = new SequenceInputStream(fistream1, fistream2);
            fostream = new FileOutputStream("D:/测试/C.txt");
            int temp;
            while( ( temp = sistream.read() ) != -1) {
                System.out.print( (char) temp );
                fostream.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            fostream.close();
            sistream.close();
            fistream1.close();
            fistream2.close();
        }
    }
}
