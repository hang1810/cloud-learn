package com.hang.thread.threadCreate.runnable;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 练习Thread ，实现多线程同步下载图片
 * @author Hang
 * @date 2020-09-25 16:08
 */
public class MyThreadByRunnableDownloadPhoto implements Runnable {

    String url;//网络图片地址
    String name;//保存的文件名
    public MyThreadByRunnableDownloadPhoto(String url,String name){
        this.name = name;
        this.url = url;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloaderForRunnable webDownloaderForRunnable = new WebDownloaderForRunnable();
        webDownloaderForRunnable.downloader(url,name );
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        MyThreadByRunnableDownloadPhoto t1 = new MyThreadByRunnableDownloadPhoto("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1504113410,2167981085&fm=26&gp=0.jpg", "线程1下载的图片");
        MyThreadByRunnableDownloadPhoto t2 = new MyThreadByRunnableDownloadPhoto("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=632941681,3742464140&fm=11&gp=0.jpg", "线程2下载的图片");
        MyThreadByRunnableDownloadPhoto t3 = new MyThreadByRunnableDownloadPhoto("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2027822491,778025875&fm=11&gp=0.jpg", "线程3下载的图片");

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

      }

}

class WebDownloaderForRunnable{
    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloadery异常");
        }
    }
}
