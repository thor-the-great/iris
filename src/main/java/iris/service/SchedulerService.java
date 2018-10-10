package iris.service;

import org.quartz.*;

import java.util.concurrent.CountDownLatch;

public class SchedulerService {

    public void scheduleJob(Job job, String url, int repeatInterval, int repeatCount) {
        try {
            CountDownLatch latch = new CountDownLatch(repeatCount);

            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schedFact.getScheduler();
            scheduler.start();

            JobBuilder builder = JobBuilder.newJob(job.getClass());

            JobDataMap data = new JobDataMap();
            data.put("latch", latch);
            data.put("url", url);

            JobDetail jobDetail = builder
                    .usingJobData(data)
                    .withIdentity("readData", "reading")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("readData", "reading")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(repeatCount)
                        .withIntervalInSeconds(repeatInterval))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            latch.await();
            System.out.println("All triggers executed. Shutdown scheduler");
            scheduler.shutdown();

        } catch (SchedulerException schedulerEx) {
            schedulerEx.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
