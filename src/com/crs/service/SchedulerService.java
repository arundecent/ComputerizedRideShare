package com.crs.service;


import com.crs.model.*;
import com.crs.dao.*;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;

public class SchedulerService {
	

    private SchedulerFactory sf;
    private Scheduler sched;
	
	public SchedulerService(){
		dao = new CrsDAO();
	}
	
	private CrsDAO dao;
	
	/**
	 * Getter method for CrsDAO instance variable 
	 * @return CrsDAO
	 */
	public CrsDAO getDao() {
		return dao;
	}

	/**
	 * Setter method for CrsDAO
	 * @param dao
	 */
	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}
	
	//Cancelling pick up request for that day
	
	public boolean cancelCarpoolDrive(CarPoolMemberForm carPoolMember){
		System.out.println("Cancelling car pool drive");
		if(carPoolMember.getEmployee().getPoints() <= 0)
			return false;
		else{
			dao.cancelCarpoolDrive(carPoolMember);
			String message = "Car Pool Driver has cancelled his drive for the day";
			notifyUsersByEmail(message, carPoolMember);
			return true;
		}
	}
	
	public void notifyUsersByEmail(String message,CarPoolMemberForm carPoolMember){
		
	}
	
	public void createScheduler() throws Exception{
        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();
	}
	
	public void destroyScheduler() throws Exception{
		System.out.println("Scheduler Shutting down");
		sched.shutdown();
	}
		
	public void run() throws Exception{
        System.out.println("------- Initializing -------------------");
        // First we must get a reference to a scheduler
        createScheduler();
                
        System.out.println("------- Initialization Complete --------");
        System.out.println("------- Scheduling Jobs ----------------");
        // jobs can be scheduled before sched.start() has been called
        // get a "nice round" time a few seconds in the future...
        // job1 will only fire once at date/time "ts"
        JobDetail job = newJob(EmailService.class).withIdentity("Email job", "Email Service").build();
        JobDetail job1 = newJob(MainTestService.class).withIdentity("Scheduling driver", "Changing Driver for the week").build();
        
        //Will be triggered daily morning at 8am and evening at 5pm
        CronTrigger trigger =  newTrigger().withIdentity("trigger", "group1").withSchedule(cronSchedule("0 0 8,17 * * ? * MON-FRI")).build();
        //Will be triggered every Monday at 6 am 
        CronTrigger trigger1 =  newTrigger().withIdentity("trigger1", "group2").withSchedule(cronSchedule("0 0 6 * * ? * MON")).build();
            // schedule it to run!
        Date ft = sched.scheduleJob(job, trigger);
        Date ft1 = sched.scheduleJob(job1,trigger1);
            
        System.out.println("------- Starting Scheduler ----------------");

            // All of the jobs have been added to the scheduler, but none of the jobs
            // will run until the scheduler has been started
            
            System.out.println(job.getKey() + " has been scheduled to run at: " + ft
                    + " and repeat based on expression: "
                    + trigger.getCronExpression());
            
            System.out.println(job1.getKey() + " has been scheduled to run at: " + ft
                    + " and repeat based on expression: "
                    + trigger1.getCronExpression());
            
            sched.start();
            
            System.out.println("------- Started Scheduler -----------------");
            System.out.println("------- Waiting 30 seconds... --------------");

            /*try {
                // wait 30 seconds to show jobs
                Thread.sleep(30L * 1000L); 
                // executing...
            } catch (Exception e) {
            }*/


            // display some stats about the schedule that just ran
            SchedulerMetaData metaData = sched.getMetaData();
            System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	}
}
