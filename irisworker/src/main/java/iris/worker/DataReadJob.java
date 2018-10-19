package iris.worker;

import iris.worker.service.DataReadService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataReadJob implements Job {

    DataReadService dataReadService = new DataReadService();

    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        List<DataReadService.DataItem> entries = dataReadService.readData(dataMap.getString("url"));
        for (DataReadService.DataItem item : entries) {
            System.out.println(item.getPrice() + "$ " + item.getTitle());
        }
        CountDownLatch latch = (CountDownLatch) jobExecutionContext.getJobDetail().getJobDataMap().get("latch");
        latch.countDown();
    }
}
