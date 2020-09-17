package com.hang.springcloud.config;

import com.hang.springcloud.job.WeatherDataSyncJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Quartz Configuration.
 * @author Hang
 * @date 2020-07-20 20:21
 */
@Configuration
public class QuartzConfiguration {
    private static final int TIME = 1800; // 更新频率 1800

    /* JobDetail
     *  你定义了一个实现Job接口的类，这个类仅仅表明该job需要完成什么类型的任务，除此之外，Quartz还需要知道该Job实例所包含的属性；这将由JobDetail类来完成。
     *
     */
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherDataSyncJob") //job name
                .storeDurably().build();
    }

    // Trigger
    @Bean
    public Trigger weatherDataSyncTrigger() {


        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("weatherDataSyncTrigger")
                .withSchedule(schedBuilder).build();
    }
}
