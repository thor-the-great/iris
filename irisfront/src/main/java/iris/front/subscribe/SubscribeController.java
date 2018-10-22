package iris.front.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SubscribeController {
    private static final Logger logger = LoggerFactory.getLogger(SubscribeController.class);

    @PostMapping("/subscribeNew")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<SubscribeResponse> subscribe(@Valid @RequestBody SubscribeRequest dataPullRequest) {
        logger.trace("subscribe controller, url is " + dataPullRequest.getUrl() + " email is " + dataPullRequest.getEmail());
        SubscribeResponse response = new SubscribeResponse(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
