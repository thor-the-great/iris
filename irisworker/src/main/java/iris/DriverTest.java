package iris;

import iris.service.SchedulerService;

public class DriverTest {

    public void drive() {
        SchedulerService schedulerService = new SchedulerService();
        DataReadJob readJob = new DataReadJob();
        String url = "https://sfbay.craigslist.org/search/sss?format=rss&query=iphone";
        schedulerService.scheduleJob(readJob, url, 20, 3);
    }

    public static void main(String[] args) {
        DriverTest driver = new DriverTest();
        driver.drive();
    }
}
