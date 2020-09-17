package com.hang.springcloud.vo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author Hang
 * @date 2020-07-25 16:19
 */
@Data
public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String timeZoneId;
}

