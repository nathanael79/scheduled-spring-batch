package com.learn.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    Logger log = LoggerFactory.getLogger(PersonController.class);

    @PostMapping(value = "/startJobOne")
    public ResponseEntity<String> startJob(){
        JobParameters params = new JobParametersBuilder()
                .addString("importUserJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        try {
            jobLauncher.run(job, params);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return ResponseEntity.accepted().body("Job successfully started.");
    }
}
