package com.hang.thread.threadCAS.ABA;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Hang
 * @date 2020-10-13 13:29
 *
 * ABA问题示及解决方案
 *
 * 参数说明
 *       value 表示 需要操作的对象
 *       valueOffset 表示 对象(value)的地址的偏移量（通过Unsafe.objectFieldOffset(Field valueField)获取）
 *       expect 表示更新时value的期待值
 *       update 表示将要更新的值
 *
 * unsafe();-->出现ABA问题，但是CAS仍然完成，并且结果为true
 * safeByAtomicStampedReference -->出现ABA问题，采用AtomicStampedReference 添加版本号来设置 CAS，结果为false
 */


public class AtomicIntegerTest {
    private static AtomicInteger a = new AtomicInteger(1);
    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);
    public static void main(String[] args) {
        unsafe();
         try {
            Thread.sleep(5000);
            safeByAtomicStampedReference();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void unsafe(){
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 = " + a);
            // 定义变量 a = 1
            try {
                Thread.sleep(1000);
                // 等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("此时内存a的值为"+a);
            boolean isCASSuccess = a.compareAndSet(1, 2);//表示更新时value的期待值=1
            // CAS操作
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果: " + isCASSuccess);
        }, "主操作线程");

        Thread other = new Thread(() -> {
            Thread.yield();
            // 确保thread-main线程优先执行
            a.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
            System.out.println("操作线程" + Thread.currentThread() + ",【increment】 ,值 = " + a);
            a.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
            System.out.println("操作线程" + Thread.currentThread() + ",【decrement】 ,值 = " + a);
        }, "干扰线程");
        main.start();
        other.start();
        System.out.println("线程不安全示例开始");


    }

    public static void safeByAtomicStampedReference(){
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp();
            //获取当前标识别
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);
            //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
        },"主操作线程");
        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
        },"干扰线程");
        main.start();
        other.start();
        System.out.println("\n"+"\n"+"\n"+"线程安全示例开始");
    }
}
