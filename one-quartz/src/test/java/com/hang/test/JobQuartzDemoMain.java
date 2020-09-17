package com.hang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Hang
 * @date 2020-07-25 15:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_quartz.xml")
public class JobQuartzDemoMain  {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void test() throws InterruptedException, SchedulerException {
        System.out.println("hello");
        Thread.sleep(1000);

        //删除job
        scheduler.pauseTrigger(TriggerKey.triggerKey("hw_trigger", "hw_trigger_group"));
        scheduler.unscheduleJob(TriggerKey.triggerKey("hw_trigger", "hw_trigger_group"));
        scheduler.deleteJob(JobKey.jobKey("job1","group1"));//移除trigger后，删除⼯作

     /*
         scheduler.pauseJob(new JobKey("job2","group1"));// 暂停⼯作
         Thread.sleep(3000);
         scheduler.resumeJob(new JobKey("job2","group1"));// 恢复⼯作
     */
      /*
         Thread.sleep(2000);
         GroupMatcher<JobKey> group1 = GroupMatcher.groupEquals("group1");
         scheduler.pauseJobs(group1); //暂停⼯作组中所有⼯作
         Thread.sleep(2000);
         scheduler.resumeJobs(group1);
         //删除⼯作，没有group的操作
     */
     /*
         CronTrigger Trigger = TriggerBuilder.newTrigger().withIdentity(jt.getJobName(), jt.getJobGroup())
         .withSchedule(CronScheduleBuilder.cronSchedule(jt.getCronExpression())).build();
         scheduler.rescheduleJob(TriggerKey.triggerKey(jt.getJobName(),jt.getJobGroup()),cronTrigger);
     **/
    }
}
