package iris.worker.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
public class SchedulerService {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    Scheduler scheduler;

    @PostConstruct
    private void init() {
        scheduler = schedulerFactory.getScheduler();
    }

    public void scheduleJob(Job job, String url, int repeatInterval, int repeatCount) {
        try {
            CountDownLatch latch = new CountDownLatch(repeatCount);
            scheduler.start();
            JobBuilder builder = JobBuilder.newJob(job.getClass());
            JobDataMap data = new JobDataMap();
            data.put("latch", latch);
            data.put("url", url);

            JobDetail jobDetail = builder
                    .usingJobData(data)
                    .withIdentity(UUID.randomUUID().toString(), "data pull")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobDetail.getKey().getName(), "data pull")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(repeatCount)
                        .withIntervalInSeconds(repeatInterval))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            //latch.await();
            //System.out.println("All triggers executed. Shutdown scheduler");
            //scheduler.shutdown();

        } catch (SchedulerException schedulerEx) {
            schedulerEx.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
