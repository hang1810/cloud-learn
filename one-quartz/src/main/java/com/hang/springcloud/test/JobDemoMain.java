package com.hang.springcloud.test;

import com.hang.springcloud.job.JobDemo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

import static org.quartz.DateBuilder.*;


/**
 * JobDemoMain
 * @author Hang
 * @date 2020-07-24 23:58
 */
public class JobDemoMain {
    public static void main(String[] args) throws InterruptedException, SchedulerException {
        testCronTrigger();
    }
    public static void testCronTrigger() throws SchedulerException,InterruptedException{
        //1、创建 scheduler 调度器 核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个Trigger, 创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")  )
                .build();

        //3、创建 jobDetail JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class)
                .withIdentity("jobDemo", "groupDemo")
                .usingJobData("dataDemo", "hello world")
                .build();

        //4 通过调度器将 Trigger  jobDetail 相关联，注册起来
        scheduler.scheduleJob(jobDetail, trigger);

        //5、启动调度器，内部注册的所有注册器开始计时
        scheduler.start();

        //6、关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();

    }
public static void testDailyTimeIntervalTrigger() throws SchedulerException,InterruptedException{
        //1、创建 scheduler 调度器 核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个Trigger, 创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()//startNow api在Trigger一旦加入scheduler 立即生效。即开始时间
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                            .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9,0))
                            .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(21,0))
                            .onDaysOfTheWeek(MONDAY,TUESDAY,FRIDAY,SATURDAY)
//                            .withRepeatCount(10)
                )
                .build();

        //3、创建 jobDetail JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class)
                .withIdentity("jobDemo", "groupDemo")
                .usingJobData("dataDemo", "hello world")
                .build();

        //4 通过调度器将 Trigger  jobDetail 相关联，注册起来
        scheduler.scheduleJob(jobDetail, trigger);

        //5、启动调度器，内部注册的所有注册器开始计时
        scheduler.start();

        //6、关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();

    }
    public static void testCalendarIntervalTrigger() throws SchedulerException,InterruptedException{
        //1、创建 scheduler 调度器 核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个Trigger, 创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()//startNow api在Trigger一旦加入scheduler 立即生效。即开始时间
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule() .withIntervalInDays(2))//每2天执⾏⼀次
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule() .withIntervalInMonths(2))//每2月执⾏⼀次
//                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule() .withIntervalInYears(2))//每2年执⾏⼀次
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule() .withIntervalInDays(2))//每2天执⾏⼀次
                .build();

        //3、创建 jobDetail JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class)
                .withIdentity("jobDemo", "groupDemo")
                .usingJobData("dataDemo", "hello world")
                .build();

        //4 通过调度器将 Trigger  jobDetail 相关联，注册起来
        scheduler.scheduleJob(jobDetail, trigger);

        //5、启动调度器，内部注册的所有注册器开始计时
        scheduler.start();

        //6、关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();

    }
    public static void testSimpleTrigger() throws SchedulerException,InterruptedException {
        //1、创建 scheduler 调度器 核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个Trigger, 创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()//startNow api在Trigger一旦加入scheduler 立即生效。即开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever())//2秒一次一直执行 可自定义
                .endAt(new GregorianCalendar(2020,07,25,15,00,00).getTime())
                .build();

        //3、创建 jobDetail JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class)
                .withIdentity("jobDemo", "groupDemo")
                .usingJobData("dataDemo", "hello world")
                .build();

        //4 通过调度器将 Trigger  jobDetail 相关联，注册起来
        scheduler.scheduleJob(jobDetail, trigger);

        //5、启动调度器，内部注册的所有注册器开始计时
        scheduler.start();

        //6、关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();
    }
}
