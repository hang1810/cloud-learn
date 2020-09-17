package com.hang.springcloud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hang
 * @date 2020-07-26 16:55
 */
//TB_APP_QUARTZ表的实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppQuartz {
    private Integer quartzId;  //id  主键
    private String jobName;  //任务名称
    private String jobGroup;  //任务分组
    private String startTime;  //任务开始时间
    private String cronExpression;  //corn表达式
    private String invokeParam;//需要传递的参数

}