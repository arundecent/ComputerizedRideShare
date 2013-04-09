package com.crs.service;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerService {
	public void run() throws Exception{
		Logger log = LoggerFactory.getLogger(SchedulerService.class);
        System.out.println("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        System.out.println("------- Initialization Complete --------");

        System.out.println("------- Scheduling Jobs ----------------");

        // jobs can be scheduled before sched.start() has been called

        // get a "nice round" time a few seconds in the future...
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

        // job1 will only fire once at date/time "ts"
        JobDetail job = newJob(EmailService.class)
                .withIdentity("job1", "group1")
                .build();
            
            SimpleTrigger trigger = (SimpleTrigger) newTrigger() 
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(10)
                .withRepeatCount(10))
                .build();
            // schedule it to run!
            Date ft = sched.scheduleJob(job, trigger);
            System.out.println(job.getKey() +
                    " will run at: " + ft +  
                    " and repeat: " + trigger.getRepeatCount() + 
                    " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");

            System.out.println("------- Starting Scheduler ----------------");

            // All of the jobs have been added to the scheduler, but none of the jobs
            // will run until the scheduler has been started
            sched.start();

            System.out.println("------- Started Scheduler -----------------");
            
            System.out.println("------- Waiting 30 seconds... --------------");

            try {
                // wait 33 seconds to show jobs
                Thread.sleep(30L * 1000L); 
                // executing...
            } catch (Exception e) {
            }


            System.out.println("------- Shutting Down ---------------------");

            sched.shutdown(true);

            System.out.println("------- Shutdown Complete -----------------");

            // display some stats about the schedule that just ran
            SchedulerMetaData metaData = sched.getMetaData();
            System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");



	}
}
