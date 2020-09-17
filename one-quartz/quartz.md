版本：

Quartz 2.2.3

官⽹链接

![Image text](D:%5CCODE%5Cgit%5Ccloud-learn%5Cone-quartz%5Cquartz.assets%5Cclipboard-1595668187306.png)

#### #⼀、 Quartz简介

##### 1.1 简介

```
Quartz是⼀个任务调度框架。⽐如你遇到这样的问题
想每⽉29号，信⽤卡⾃动还款
想每年4⽉1⽇⾃⼰给当年暗恋⼥神发⼀封匿名贺卡
想每隔1⼩时，备份⼀下⾃⼰的学习笔记
```

```
这些问题总结起来就是：在某⼀个有规律的时间点⼲某件事。并且时间的触发的条件可以⾮常复杂（⽐如每⽉最后⼀个⼯作⽇的17:50），复杂到需要⼀个专⻔的框架来⼲这个事。
Quartz就是来⼲这样的事，你给它⼀个触发条件的定义，它负责到了时间点，触发相应的Job起来⼲活如果应⽤程序需要在给定时间执⾏任务，或者如果系统有连续维护作业，那么Quartz是理想的解决⽅案。
```

##### 1.2 特点

###### 1.2.1 作业调度

```
作业被安排在⼀个给定的触发时运⾏。触发器可以使⽤以下指令的组合来创建：
在⼀天中的某个时间（到毫秒）
在⼀周的某⼏天
在每⽉的某⼀天
在⼀年中的某些⽇期
不在注册的⽇历中列出的特定⽇期（如商业节假⽇除外）
重复特定次数
重复进⾏，直到⼀个特定的时间/⽇期
⽆限重复
重复的延迟时间间隔
```

###### 1.2.2 作业持久性

```
Quartz的设计包括⼀个作业存储接⼝，有多种实现。
通过使⽤包含的JDBCJobStore，所有的作业和触发器配置为“⾮挥发性”都存储在通过JDBC关系数据库。
通过使⽤包含的RAMJobStore，所有的作业和触发器存储在RAM，因此不计划执⾏仍然存在 - 但这是⽆需使⽤外部数据库的优势。
```



#### ⼆、 Quartz使⽤

##### 2.1 导⼊依赖

```xml
<dependencies>
    <!--Quartz任务调度-->
    <!-- https://mvnrepository.com/artifact/org.quartz-scheduler/quartz -->
    <dependency>
        <groupId>org.quartz-scheduler</groupId>
        <artifactId>quartz</artifactId>
        <version>2.2.3</version>
    </dependency>
</dependencies>
```



##### 2.2  定义Job

```java
package com.hang.springcloud.job;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;
/**
 * Job 最基础的demo 使用
 * @author Hang
 * @date 2020-07-24 23:04
 */
public class JobDemo implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //创建工作详情 JobDetail 用来描述Job实现类及其它相关的静态信息
        JobDetail jobDetail=jobExecutionContext.getJobDetail();
        //获取工作的名称
        String name = jobDetail.getKey().getName();//任务名
        String group = jobDetail.getKey().getGroup();//任务group
        String job=jobDetail.getJobDataMap().getString("dataDemo");//任务中的数据
        System.out.println("job执行，job名："+name+" Data:"+job+" group:"+group+" "+new Date());
    }
}
```

##### 2.3 ***\*API\*******\*测试\****

```java
package com.hang.springcloud.test;

import com.hang.springcloud.job.JobDemo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * JobDemoMain
 * @author Hang
 * @date 2020-07-24 23:58
 */
public class JobDemoMain {
    public static void main(String[] args) throws SchedulerException,InterruptedException {
        //1、创建 scheduler 调度器 核心组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2、定义一个Trigger, 创建触发器：Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()//startNow api在Trigger一旦加入scheduler 立即生效。即开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                       .withIntervalInSeconds(2).repeatForever())//2秒一次一直执行 可自定义
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

```

##### 2.4 配置

```properties
# 名为：quartz.properties，放置在classpath下，如果没有此配置则按默认配置启动
# 指定调度器名称，⾮实现类
org.quartz.scheduler.instanceName = DefaultQuartzScheduler04 # 指定线程池实现类
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool # 线程池线程数量
org.quartz.threadPool.threadCount = 10 # 优先级，默认5
org.quartz.threadPool.threadPriority = 5 # ⾮持久化job
org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
```

#####  2.5 核心类说明

```
Scheduler： 调 度 器 。 所 有 的 调 度 都 是 由 它 控 制 Scheduler就是Quartz的⼤脑，所有任务都是由它来设施
Schduelr包含⼀个两个重要组件: JobStore和ThreadPool
JobStore是会来存储运⾏时信息的，包括Trigger,Schduler,JobDetail，业务锁等ThreadPool就是线程池，Quartz有⾃⼰的线程池实现。所有任务的都会由线程池执⾏

SchdulerFactory，顾名思义就是来⽤创建Schduler了，
有两个实现：DirectSchedulerFactory和 StdSchdulerFactory。
前者可以⽤来在代码⾥定制你⾃⼰的Schduler参数。
后者是直接读取classpath下的quartz.properties（不存在就都使⽤默认值）配置来实例化Schduler。
通常来讲，我们使⽤StdSchdulerFactory也就⾜够了。
SchdulerFactory本身是⽀持创建RMI stub的，可以⽤来管理远程的Scheduler，功能与本地⼀样
```

#### 三、Trigger 

##### 	3.1 、SimpleTrigger

```
指定从某⼀个时间开始，以⼀定的时间间隔（单位是毫秒）执⾏的任务。它适合的任务类似于：9:00 开始，每隔1⼩时，执⾏⼀次。
它的属性有：
repeatInterval 重复间隔
repeatCount 重复次数。实际执⾏次数是 repeatCount+1。因为在startTime的时候⼀定会执⾏⼀次。
```

例如：

```java
SimpleScheduleBuilder.simpleSchedule().
withIntervalInSeconds(10).//每隔10秒执⾏⼀次repeatForever().//永远执⾏
build();
```

```java
SimpleScheduleBuilder.simpleSchedule().
withIntervalInMinutes(3).//每隔3分钟执⾏⼀次withRepeatCount(3).//执⾏3次
build();
```

##### 3.2 、CalendarIntervalTrigger 

```
类似于SimpleTrigger，指定从某⼀个时间开始，以⼀定的时间间隔执⾏的任务。   
但是不同的是SimpleTrigger指定的时间间隔为毫秒，没办法指定每隔⼀个⽉执⾏⼀次（每⽉的时间间隔不是固定值），
CalendarIntervalTrigger⽀持的间隔单位有秒，分钟，⼩时，天，⽉，   年，星期。
```

```java
CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
.withIntervalInDays(2) //每2天执⾏⼀次
.build();

CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
.withIntervalInWeeks(1) //每周执⾏⼀次
.build();
```

##### 3.3、DailyTimeIntervalTrigger

```
指定每天的某个时间段内，以⼀定的时间间隔执⾏任务。并且它可以⽀持指定星期。
它适合的任务类似于：指定每天9:00 ⾄ 18:00 ，每隔70秒执⾏⼀次，并且只要周⼀⾄周五执⾏。它的属性有:
startTimeOfDay 每天开始时间endTimeOfDay 每天结束时间daysOfWeek 需要执⾏的星期interval 执⾏间隔
intervalUnit 执⾏间隔的单位（秒，分钟，⼩时，天，⽉，年，星期）
repeatCount 重复次数
```

```java
DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0)) //每天9：00开始
.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 0)) //18：00 结 束
.onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周⼀⾄周五执⾏
.withIntervalInHours(1) //每间隔1⼩时执⾏⼀次
.withRepeatCount(100) //最多重复100次（实际执⾏100+1次）
.build();

DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(10, 0)) //每天10：00开始
.endingDailyAfterCount(10) //每天执⾏10次，这个⽅法实际上根据 startTimeOfDay+interval*count
算 出 endTimeOfDay
.onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY) //周⼀⾄周五执⾏
.withIntervalInHours(1) //每间隔1⼩时执⾏⼀次
.build();
```

##### 3.4、CronTrigger

```
适合于更复杂的任务，它⽀持类型于Linux Cron的语法（并且更强⼤）。基本上它覆盖了以上三个Trigger的绝⼤部分能⼒（但不是全部）——
当然，也更难理解。
它适合的任务类似于：每天0:00,9:00,18:00各执⾏⼀次。它的属性只有:
Cron表达式。但这个表示式本身就够复杂了
```

```
CronScheduleBuilder.cronSchedule("0 0/2 10-12 * * ?") // 每天10:00-12:00，每隔2分钟执⾏⼀次
.build();

cronSchedule("0 30 9 ? * MON") // 每周⼀，9:30执⾏⼀次
.build();

CronScheduleBuilder.weeklyOnDayAndHourAndMinute(MONDAY,9, 30) //等同于 0 30 9 ? * MON
.build();
```

| ***\*位置\**** | ***\*时间域\**** | ***\*允许值\**** | ***\*特殊值\**** |
| -------------- | ---------------- | ---------------- | ---------------- |
| 1              | 秒               | 0-59             | , - * /          |
| 2              | 分钟             | 0-59             | , - * /          |
| 3              | ⼩时             | 0-23             | , - * /          |
| 4              | ⽇期             | 1-31             | , - * ? / L W    |
| 5              | ⽉份             | 1-12             | , - * /          |
| 6              | 星期             | 1-7              | , - * ? / L #    |
| 7              | 年份（可选）     | 1-31             | , - * /          |

```
[1] [2] [3] ? [5] 3#5
```

- 星号(*)：可⽤在所有字段中，表示对应时间域的每⼀个时刻，例如， 在分钟字段时，表示“每分钟”； 问号（?）：该字符只在⽇期和星期字段中使⽤，它通常指定为“不确定值”

- 减号(-)：表达⼀个范围，如在⼩时字段中使⽤“10-12”，则表示从10到12点，即10,11,12；

- 逗号(,)：表达⼀个列表值，如在星期字段中使⽤“MON,WED,FRI”，则表示星期⼀，星期三和星期五；

- 斜杠(/)：x/y表达⼀个等步⻓序列，x为起始值，y为增量步⻓值。如在分钟字段中使⽤0/15，则表示为0,15,30和45秒，⽽5/15在分钟字段中 表示5,20,35,50，你也可以使⽤*/y，它等同于0/y；

- L：该字符只在⽇期和星期字段中使⽤，代表“Last”的意思，但它在两个字段中意思不同。L在⽇期字段中，表示这个⽉份的最后⼀天，如⼀⽉的 31号，⾮闰年⼆⽉的28号；如果L⽤在星期中，则表示星期六，等同于7。但是，如果L出现在星期字段⾥，⽽且在前⾯有⼀个数值X，则表示“这  个⽉的最后⼀个周x”，例如，6L表示该⽉的最后星期五；

- W：该字符只能出现在⽇期字段⾥，是对前导⽇期的修饰，表示离该⽇期最近的⼯作⽇。例如15W表示离该⽉15号最近的⼯作⽇，如果该⽉15号是  星期六，则匹配14号星期五；如果15⽇是星期⽇，则匹配16号星期⼀；如果15号是星期⼆，那结果就是15号星期⼆。但必须注意关联的匹配⽇期  不能够跨⽉，如你指定1W，如果1号是星期六，结果匹配的是3号星期⼀，⽽⾮上个⽉最后的那天。W字符串只能指定单⼀⽇期，⽽不能指定⽇期范  围；

- LW组合：在⽇期字段可以组合使⽤LW，它的意思是当⽉的最后⼀个⼯作⽇；



| ***\*表示式\****         | ***\*说明\****                                               |
| ------------------------ | ------------------------------------------------------------ |
| 秒分时⽇⽉周             |                                                              |
| 0 0 12 * * ?             | 每天12点运⾏                                                 |
| 0 15 10 * * ?            | 每天10:15运⾏                                                |
| 0 15 10 * * ? 2008       | 在2008年的每天10：15运⾏                                     |
| 0 * 14 * * ?             | 每天14点到15点之间每分钟运⾏⼀次，开始于14:00，结束于14:59。 |
| 0 0/5 14 * * ?           | 每天14点到15点每5分钟运⾏⼀次，开始于14:00，结束于14:55。    |
| 0 0/5 14,18 * * ?        | 每天14点到15点每5分钟运⾏⼀次，此外每天18点到19点每5钟也运⾏⼀次。 |
| 0 0-5 14 * * ?           | 每天14:00点到14:05，每分钟运⾏⼀次。                         |
| 0 0-5/2 14 * * ?         | 每天14:00点到14:05，每2分钟运⾏⼀次。                        |
| 0 10,44 14 ? 3 WED       | 3⽉每周三的14:10分和14:44，每分钟运⾏⼀次。                  |
| 0 15 10 ? * MON-FRI      | 每周⼀，⼆，三，四，五的10:15分运⾏。                        |
| 0 15 10 15 * ?           | 每⽉15⽇10:15分运⾏。                                        |
| 0 15 10 L * ?            | 每⽉最后⼀天10:15分运⾏。                                    |
| 0 15 10 ? * 6L           | 每⽉最后⼀个星期五10:15分运⾏。【此时天必须是"?"】           |
| 0 15 10 ? * 6L 2007-2009 | 在2007,2008,2009年每个⽉的最后⼀个星期五的10:15分运⾏。      |

```
Calendar不是jdk的java.util.Calendar，不是为了计算⽇期的。它的作⽤是在于补充Trigger的时间。可以排除或加⼊某⼀些特定的时间点。
以”每⽉29⽇零点⾃动还信⽤卡“为例，我们想排除掉每年的2⽉29号零点这个时间点（因为平年和润年2⽉不⼀样）。这个时  间，就可以⽤Calendar来实现
```

```
Quartz提供以下⼏种Calendar，注意，所有的Calendar既可以是排除，也可以是包含，取决于： HolidayCalendar。指定特定的⽇期，⽐如20140613。精度到天。
DailyCalendar。指定每天的时间段（rangeStartingTime,     rangeEndingTime)，格式是HH:MM[:SS[:mmm]]。也就是最⼤精度可以到毫秒。
WeeklyCalendar。指定每星期的星期⼏，可选值⽐如为java.util.Calendar.SUNDAY。精度是天。MonthlyCalendar。指定每⽉的⼏号。可选值为1-31。精度是天
AnnualCalendar。 指定每年的哪⼀天。使⽤⽅式如上例。精度是天。
CronCalendar。指定Cron表达式。精度取决于Cron表达式，也就是最⼤精度可以到秒。
```

```
当scheduler⽐较繁忙的时候，可能在同⼀个时刻，有多个Trigger被触发了，但资源不⾜（⽐如线程池不⾜）。那么这个时候⽐    剪⼑⽯头布更好的⽅式，就是设置优先级。优先级⾼的先执⾏。
需要注意的是，优先级只有在同⼀时刻执⾏的Trigger之间才会起作⽤，如果⼀个Trigger是9:00，另⼀个Trigger是9:30。那么⽆    论后⼀个优先级多⾼，前⼀个都是先执⾏。
优先级的值默认是5，当为负数时使⽤默认值。最⼤值似乎没有指定，但建议遵循Java的标准，使⽤1-10，不然⻤才知道看到
【优先级为10】是时，上头还有没有更⼤的值。
```

#### 四、 Job并发

job是有可能并发执⾏的，⽐如⼀个任务要执⾏10秒中，⽽调度算法是每秒中触发1次，那么就有可能多个任务被并发执⾏。

有时候我们并不想任务并发执⾏，⽐如这个任务要去”获得数据库中所有未发送邮件的名单“，如果是并发执⾏，就需要⼀个数据库锁去避免⼀个  数据被多次处理。这个时候⼀个@DisallowConcurrentExecution解决这个问题

```java
@DisallowConcurrentExecution
public class DoNothingJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException { 			System.out.println("操作");
    }
}
```

注意，@DisallowConcurrentExecution是对JobDetail实例⽣效，也就是如果你定义两个JobDetail，引⽤同⼀个Job类，是可以并发执⾏的

```java
@DisallowConcurrentExecution //会不允许并发执⾏，（如果每1s触发⼀次，但每个job要执⾏3秒）
public class MyJob implements Job{

 @Override
 public void execute(JobExecutionContext context) throws JobExecutionException {
     try {
     	Thread.sleep(3000);
     } catch (InterruptedException e) {
     	e.printStackTrace();
     }
     System.out.println("任务调度：组："+group+",⼯作名："+name+" "+data+new Date());
  }
}
```

#### 五、 Spring整合Quartz

##### 5.1、依赖

```xml
        <!-- quartz 模块 -->
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz-jobs</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
```

##### 5.2 配置

```
调度器 SchedulerFactoryBean SchedulerFactoryBean
触发器 CronTriggerFactoryBean CronTriggerFactoryBean
JobDetail JobDetailFactoryBean JobDetailFactoryBean
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--
        Spring整合Quartz进行配置遵循下面的步骤：
        1：定义工作任务的Job
        2：定义触发器Trigger，并将触发器与工作任务绑定
        3：定义调度器，并将Trigger注册到Scheduler
     -->

    <!-- 1：定义任务的bean ，这里使用JobDetailFactoryBean,也可以使用MethodInvokingJobDetailFactoryBean ，配置类似-->
    <bean name="lxJob" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
        <!-- 指定job的名称 -->
        <property name="name" value="job1"/>
        <!-- 指定job的分组 -->
        <property name="group" value="group1"/>
        <!-- 指定具体的job类 -->
        <property name="jobClass" value="com.hang.springcloud.job.JobDemo"/>
        <!-- 如果为false，当没有活动的触发器与之关联时会在调度器中会删除该任务 (可选) -->
        <property name="durability" value="true"/>
        <!-- （可选）
             指定spring容器的key，如果不设定在job中的jobmap中是获取不到spring容器的
             其实现了ApplicationContextWare,则其中的setApplicationContext方法会得到
             当前的工厂对象，且将工厂对象存在了类中的一个属性“applicationContext”中，源码如下

             getJobDataMap().put(this.applicationContextJobDataKey, this.applicationContext);
             则在Job的jobmap中可以获得工厂对象，如果需要可以使用
			 (ApplicationContext) jobDataMap.get("applicationContext04");
			 jobDataMap.get("data04");

			 .usingJobData("data04", "hello world~~")
			 .usingJobData("applicationContext04",spring工厂对象)
        -->
        <!--<property name="applicationContextJobDataKey" value="applicationContext04"/>-->
    </bean>


    <!-- 2.2：定义触发器的bean，定义一个Cron的Trigger，一个触发器只能和一个任务进行绑定 -->
    <bean id="cronTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <!-- 指定Trigger的名称 -->
        <property name="name" value="hw_trigger"/>
        <!-- 指定Trigger的名称 -->
        <property name="group" value="hw_trigger_group"/>
        <!-- 指定Tirgger绑定的Job -->
        <property name="jobDetail" ref="lxJob"/>
        <!-- 指定Cron 的表达式 ，当前是每隔5s运行一次 -->
        <property name="cronExpression" value="* * * * * ?" />
    </bean>

    <!-- 3.定义调度器，并将Trigger注册到调度器中 -->
    <bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="cronTrigger"/>
            </list>
        </property>
        <!-- 添加 quartz 配置，如下两种方式均可 -->
        <!--<property name="configLocation" value="classpath:quartz.properties"></property>-->
        <property name="quartzProperties">
            <value>
                # 指定调度器名称，实际类型为：QuartzScheduler
                org.quartz.scheduler.instanceName = MyScheduler
                # 指定连接池
                org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
                # 连接池线程数量
                org.quartz.threadPool.threadCount = 11
                # 优先级
                org.quartz.threadPool.threadPriority = 5
                # 不持久化job
                org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
            </value>
        </property>
    </bean>
</beans>
```

##### 5.3 代码

```java
public class MyJob implements Job {
public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
 	System.err.println("job 执⾏"+new Date());
 }
}
```

```sql
public static void main(String[] args) throws InterruptedException, SchedulerException { 
     ApplicationContext context = new                    ClassPathXmlApplicationContext("applicationContext.xml");
     System.out.println("=============");
     StdScheduler scheduler = (StdScheduler) context.getBean("scheduler");
     System.out.println(scheduler.getClass());
     Thread.sleep(3000);
     // 如下api可以设计⼏个Controller，⽤来做job的暂停和删除
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
```

#### 六、持久化

##### 6.1、建表

```sql
create table QRTZ_CALENDARS
(
 SCHED_NAME varchar(120) not null,
 CALENDAR_NAME varchar(200) not null,
 CALENDAR blob not null,
 primary key (SCHED_NAME, CALENDAR_NAME)
);
create table QRTZ_FIRED_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 ENTRY_ID varchar(95) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 INSTANCE_NAME varchar(200) not null,
 FIRED_TIME bigint(13) not null,
 SCHED_TIME bigint(13) not null,
 PRIORITY int not null,
 STATE varchar(16) not null,
 JOB_NAME varchar(200) null,
 JOB_GROUP varchar(200) null,
 IS_NONCONCURRENT varchar(1) null,
 REQUESTS_RECOVERY varchar(1) null,
 primary key (SCHED_NAME, ENTRY_ID)
);
create table QRTZ_JOB_DETAILS
(
 SCHED_NAME varchar(120) not null,
 JOB_NAME varchar(200) not null,
 JOB_GROUP varchar(200) not null,
 DESCRIPTION varchar(250) null,
 JOB_CLASS_NAME varchar(250) not null,
 IS_DURABLE varchar(1) not null,
 IS_NONCONCURRENT varchar(1) not null,
 IS_UPDATE_DATA varchar(1) not null,
 REQUESTS_RECOVERY varchar(1) not null, 
 JOB_DATA blob null,
 primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
);
create table QRTZ_LOCKS
(
 SCHED_NAME varchar(120) not null,
 LOCK_NAME varchar(40) not null,
 primary key (SCHED_NAME, LOCK_NAME)
);
create table QRTZ_PAUSED_TRIGGER_GRPS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_GROUP varchar(200) not null,
 primary key (SCHED_NAME, TRIGGER_GROUP)
);
create table QRTZ_SCHEDULER_STATE
(
 SCHED_NAME varchar(120) not null,
 INSTANCE_NAME varchar(200) not null,
 LAST_CHECKIN_TIME bigint(13) not null,
 CHECKIN_INTERVAL bigint(13) not null,
 primary key (SCHED_NAME, INSTANCE_NAME)
);
create table QRTZ_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 JOB_NAME varchar(200) not null,
 JOB_GROUP varchar(200) not null,
 DESCRIPTION varchar(250) null,
 NEXT_FIRE_TIME bigint(13) null,
 PREV_FIRE_TIME bigint(13) null,
 PRIORITY int null,
 TRIGGER_STATE varchar(16) not null,
 TRIGGER_TYPE varchar(8) not null,
 START_TIME bigint(13) not null,
 END_TIME bigint(13) null,
 CALENDAR_NAME varchar(200) null,
 MISFIRE_INSTR smallint(2) null,
 JOB_DATA blob null,
 primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
 constraint QRTZ_TRIGGERS_ibfk_1
 foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP) references QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME,
JOB_GROUP)
);
create table QRTZ_BLOB_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 BLOB_DATA blob null,
 primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
 constraint QRTZ_BLOB_TRIGGERS_ibfk_1
 foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME,
TRIGGER_NAME, TRIGGER_GROUP)
);
create table QRTZ_CRON_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 CRON_EXPRESSION varchar(200) not null,
 TIME_ZONE_ID varchar(80) null,
 primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
 constraint QRTZ_CRON_TRIGGERS_ibfk_1
 foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME,
TRIGGER_NAME, TRIGGER_GROUP)
);
create table QRTZ_SIMPLE_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 REPEAT_COUNT bigint(7) not null,
 REPEAT_INTERVAL bigint(12) not null,
 TIMES_TRIGGERED bigint(10) not null,
 primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
 constraint QRTZ_SIMPLE_TRIGGERS_ibfk_1
 foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME,
TRIGGER_NAME, TRIGGER_GROUP)
);
create table QRTZ_SIMPROP_TRIGGERS
(
 SCHED_NAME varchar(120) not null,
 TRIGGER_NAME varchar(200) not null,
 TRIGGER_GROUP varchar(200) not null,
 STR_PROP_1 varchar(512) null,
 STR_PROP_2 varchar(512) null,
 STR_PROP_3 varchar(512) null,
 INT_PROP_1 int null,
 INT_PROP_2 int null,
 LONG_PROP_1 bigint null,
 LONG_PROP_2 bigint null,
 DEC_PROP_1 decimal(13, 4) null,
 DEC_PROP_2 decimal(13, 4) null,
 BOOL_PROP_1 varchar(1) null,
 BOOL_PROP_2 varchar(1) null,
 primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
 constraint QRTZ_SIMPROP_TRIGGERS_ibfk_1
 foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME,
TRIGGER_NAME, TRIGGER_GROUP)
);
```

##### 6.2 、配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 定义调度器，并将Trigger注册到调度器中 -->
    <bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <!-- 添加 quartz 配置，如下两种方式均可 -->
        <!--<property name="configLocation" value="classpath:quartz.properties"></property>-->
        <property name="quartzProperties">
            <value>
                # 指定调度器名称，实际类型为：QuartzScheduler
                org.quartz.scheduler.instanceName = MyScheduler
                # 指定连接池
                org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
                # 连接池线程数量
                org.quartz.threadPool.threadCount = 11
                # 优先级
                org.quartz.threadPool.threadPriority = 5
                # 不持久化job
                # org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
                #持久化
                org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX
                #quartz表的前缀
                org.quartz.jobStore.tablePrefix = QRTZ_
            </value>
        </property>
        <property name="dataSource" ref="druid"/>
    </bean>

    <!-- 导入外部参数文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!-- 连接池：druid -->
    <bean id="druid" class="com.alibaba.druid.pool.DruidDataSource" init-method="init"
          destroy-method="close">
        <!-- 基本属性 url、user、password -->
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="1"/>
        <property name="minIdle" value="1"/>
        <property name="maxActive" value="${jdbc.maxPoolSize}"/>

        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="3000"/>

        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="60000"/>

        <!-- 配置一个连接在池中最小空闲的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="300000"/>

        <property name="validationQuery" value="SELECT 'x'"/>
        <property name="testWhileIdle" value="true"/>
        <property name="testOnBorrow" value="false"/>
        <property name="testOnReturn" value="false"/>
    </bean>
</beans>
```

applciation.yml

```yml
server:
  port: 1112


spring:
  application:
    name: one-quartz

  datasource:
    type: com.alibaba.druid.pool.DruidDataSource    #当前数据源操作类型
    driver-class-name: com.mysql.cj.jdbc.Driver      #mysql驱动包
    url: jdbc:mysql://localhost:3306/cloud_learn?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
    maximum-pool-size: 3

  redis:
    host: localhost
    port: 6379
    password: 123456
```

jdbc.properties

```properties
jdbc.username=root
jdbc.password=123456
jdbc.url=jdbc:mysql://localhost:3306/cloud_learn?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
jdbc.driver=com.mysql.jdbc.Driver
jdbc.maxPoolSize=3
```

mvc.xml

```xml
<beans 	xmlns="http://www.springframework.org/schema/beans"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context
							http://www.springframework.org/schema/context/spring-context.xsd
							http://www.springframework.org/schema/mvc
							http://www.springframework.org/schema/mvc/spring-mvc.xsd
                            http://www.springframework.org/schema/aop
							http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 告知springmvc  哪些包中 存在 被注解的类
         use-default-filters="false"  遇到到 @Controller  @Service  @Repository  @Component类，都会忽略
    -->
    <context:component-scan base-package="com.hang.springcloud" use-default-filters="false">
        <!-- 只扫描  有@Controller注解的类 -->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <!-- 注册注解开发驱动 -->
    <mvc:annotation-driven/>

    <!-- 视图解析器
	     作用：1.捕获后端控制器的返回值="index"
	          2.解析： 在返回值的前后 拼接 ==> "/index.jsp"
	 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/"></property>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>
    <!-- 在项目中 自动添加一个 映射{"/**" : DefaultServletHttpRequestHandler}
         请求进入前端后，会先匹配自定义的Handler，如果没有匹配的则进入DefaultServletHttpRequestHandler。
         DefaultServletHttpRequestHandler会将请求转发给Tomcat中名为"default"的servlet。
         最终实现了静态资源的访问
    -->
    <mvc:default-servlet-handler/>


</beans>
```

addjob.jsp

```jsp

<%--
  Created by IntelliJ IDEA.
  User: zhj
  Date: 2019/10/19
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/quartz/add" method="post">
    jobName: <input type="text" name="jobName"> <br>
    jobGroup: <input type="text" name="jobGroup"> <br>
    cronExp: <input type="text" name="cronExpression"> <br>
    jobClass: <input type="text" name="jobClassName"> <br>
    <input type="submit" value="增加">
</form>
</body>
</html>

```

index.jsp

```html
<html>
<body>
<h2>Hello World!</h2>
</body>
</html>

```

controller

```java
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


```

vo

```java
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
```

