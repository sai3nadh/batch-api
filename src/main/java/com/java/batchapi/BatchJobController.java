package com.java.batchapi;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchJobController {


    private final JobLauncher jobLauncher;
    private final Job batchJob;

    @Autowired
    public BatchJobController(JobLauncher jobLauncher, Job batchJob) {
        this.jobLauncher = jobLauncher;
        this.batchJob = batchJob;
    }

    @GetMapping("/runJob")
    public String runJob() throws Exception {
        jobLauncher.run(batchJob, new JobParameters());
        return "Job executed successfully.";
    }
}
