package com.hang.springcloud.controller;

import com.hang.springcloud.vo.JobAndTrigger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hang
 * @date 2020-07-25 16:12
 */
@RestController
@RequestMapping("/quartz")
public class TestJobDemoController {



        @Autowired //注入了工厂中 调度器
        private Scheduler scheduler;

        @RequestMapping("/add")
        public String addJob(JobAndTrigger jt) throws ClassNotFoundException, SchedulerException {
            // 创建JobDetail
            JobDetail jobDetail=null;
            jobDetail = JobBuilder.newJob((Class<? extends Job>)Class.forName(jt.getJobClassName()))
                    .withIdentity(jt.getJobName(), jt.getJobGroup()).storeDurably(true).build();
            CronTrigger cronTrigger = null;
            cronTrigger = TriggerBuilder.newTrigger().withIdentity(jt.getJobName(),jt.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(jt.getCronExpression()))
                    .build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
            return "index";
        }
    }

