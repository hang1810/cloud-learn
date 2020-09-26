package com.hang.thread.threadCreate.callable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author Hang
 * @date 2020-09-26 14:06

 * 1．实现Callable接口，需要返回值类型
 * 2. 重写call方法，需要抛出异常
 * 3．创建目标对象
 * 4．创建执行服务:ExecutorService ser = Executors.newFixedThreadPool(1);
 * 5．提交执行:Future<Boolean> result1 = ser.submit(t1);
 * 6．获取结果:boolean r1 = result1.get()
 * 7．关闭服务: ser.shutdownNow();
 */
public class CallableThreadDownloadPhoto implements Callable<Boolean> {

    String url;//网络图片地址
    String name;//保存的文件名
    public CallableThreadDownloadPhoto(String url,String name){
        this.name = name;
        this.url = url;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() throws Exception {
        WebDownloaderForCallable webDownloaderForCallable = new WebDownloaderForCallable();
        webDownloaderForCallable.downloader(url,name );
        System.out.println("下载了文件名为："+name);
        return  true;

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadDownloadPhoto t1 = new CallableThreadDownloadPhoto("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1504113410,2167981085&fm=26&gp=0.jpg", "线程1下载的图片");
        CallableThreadDownloadPhoto t2 = new CallableThreadDownloadPhoto("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=632941681,3742464140&fm=11&gp=0.jpg", "线程2下载的图片");
        CallableThreadDownloadPhoto t3 = new CallableThreadDownloadPhoto("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2027822491,778025875&fm=11&gp=0.jpg", "线程3下载的图片");

        //创建执行服务数量
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        Future<Boolean> result3 = ser.submit(t3);

        //获取结果:boolean r1 = result1.get()
        boolean r1 = result1.get();
        boolean r2 = result1.get();
        boolean r3 = result1.get();

        //关闭服务: ser.shutdownNow();
        ser.shutdownNow();
    }


}
class WebDownloaderForCallable{
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