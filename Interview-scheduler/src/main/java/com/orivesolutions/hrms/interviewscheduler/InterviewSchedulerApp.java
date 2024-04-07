package com.orivesolutions.hrms.interviewscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InterviewSchedulerApp {

    public static void main(String[] args) {
        SpringApplication.run(InterviewSchedulerApp.class, args);
    }
}
