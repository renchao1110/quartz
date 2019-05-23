package com.glodon.quartz.quartzhelloworld.helloworld01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 编写任务调度类
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个 JobDetail 实例，将该实例与 HelloJob 实例绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "jobgroup1").build();
        System.out.println(""+jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());
        System.out.println(jobDetail.getJobClass().getName());

        // 创建一个 Trigger 实例，定义该 job 立即执行，并且每隔两秒重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","triggergroup1")
        .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        // 创建 Scheduler 实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 绑定 JobDetail 和 trigger
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        // 打印当前的执行时间，格式为2017-01-01 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is : " + sf.format(date));
    }
}
