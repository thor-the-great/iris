package iris.controller;


import iris.DataReadJob;
import iris.payload.DataPullRequest;
import iris.payload.DataPullResponse;
import iris.service.SchedulerService;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
public class PullDataJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(PullDataJobSchedulerController.class);

    @Autowired
    SchedulerService schedulerService;

    @PostMapping("/scheduleDataPull")
    public ResponseEntity<DataPullResponse> scheduleDataPull(@Valid @RequestBody DataPullRequest dataPullRequest) {
        try {
            DataReadJob readJob = new DataReadJob();
            schedulerService.scheduleJob(readJob, dataPullRequest.getUrl(), 10, 3);
            DataPullResponse response = new DataPullResponse(true);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            logger.error("Error scheduling data pull job", ex);
            DataPullResponse response = new DataPullResponse(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
