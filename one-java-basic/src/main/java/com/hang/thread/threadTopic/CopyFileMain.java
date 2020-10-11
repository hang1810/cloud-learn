package com.hang.thread.threadTopic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

/**
 * @author Hang
 * @date 2020-10-08 16:35
 */
public class CopyFileMain {
    public static void main(String[] args) {
        //两个线程实例
        CopyFile cf1 = new CopyFile("C://测试//黎明 - 我可以忘记你.ncm", "D://测试//黎明 - 我可以忘记你.ncm");
        CopyFile cf2 = new CopyFile("C://测试//说散就散.ncm", "D://测试//说散就散.ncm");
        cf1.start();
        cf2.start();
    }
}

class CopyFile extends Thread {
    public File older;//源文件路径
    public File newer;//目标文件路径

    public CopyFile(String older, String newer) {
        this.older = new File(older);
        this.newer = new File(newer);
    }

    @Override
    public void run() {
        FileInputStream fis = null;
        FileOutputStream fos = null;


        try {
            fis = new FileInputStream(older);
            fos = new FileOutputStream(newer);

            long len = older.length();//获取源文件的长度    跟  fis.available()的结果
            System.out.println(len);

            //文件大小的十分之一
            byte[] b = new byte[(int) (older.length() / 10)];
            //声明一个字节数组，每次读取的数据存到这里
            int length = 0;//返回每次读取的数据长度

            double temp = 0;

            DecimalFormat df = new DecimalFormat("##%");
            //最多将b.length个字节读入一个byte数组中，也就是每次读1/10个字节，然后循环，直到读到问价尾
            while ((length = fis.read(b)) != -1) {//读到文件尾会返回-1
                fos.write(b, 0, length);//把每次读取的内容，输出到目标路径文件中
                temp += length;//把每次读取的数据长度累加
                double d = temp / len;//计算出已经读取的长度占文件总长度的比率

                int jd = (int) d;
                if (jd % 10 == 0) {
                    System.out.println(older.getName() + "文件复制了：" + df.format(d));//将小数给格式化
                }
                System.out.println(older.getName() + "文件已复制了：" + d);
            }
            System.out.println(older.getName() + "复制完毕！");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}