package com.hang.IO.IOdemo;

import java.io.*;

/**
 * @author Hang
 * @date 2020-10-08 17:22
 *
 * 1、读取键盘输入，打印到控制台 在刷题网站刷算法题的时候，在程序开头都需要和键盘进行交互，常常用到行夺取器 BufferedReader 和转换流 InputStreamReader。
 */
public class KeyInAndPrintConsole {
    public static void main(String[] args) {
        try {
            keyInAndPrintConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void keyInAndPrintConsole() throws IOException {
        PrintWriter out = null;
        BufferedReader br = null;
        try{
            System.out.println("请输入:");
            out = new PrintWriter(System.out, true);
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(1);
                }
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            out.close();
            br.close();
        }
    }
}
