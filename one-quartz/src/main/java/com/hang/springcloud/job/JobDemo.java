package com.hang.springcloud.job;

import org.quartz.*;

import java.util.Date;

/**
 * Job 最基础的demo 使用
 * @author Hang
 * @date 2020-07-24 23:04
 */
@DisallowConcurrentExecution
public class JobDemo implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        try{
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        //创建工作详情 JobDetail 用来描述Job实现类及其它相关的静态信息
        JobDetail jobDetail=jobExecutionContext.getJobDetail();
        //获取工作的名称
        String name = jobDetail.getKey().getName();//任务名
        String group = jobDetail.getKey().getGroup();//任务group
        String job=jobDetail.getJobDataMap().getString("dataDemo");//任务中的数据
        System.out.println("job执行，job名："+name+" Data:"+job+" group:"+group+" "+new Date());
    }
}
