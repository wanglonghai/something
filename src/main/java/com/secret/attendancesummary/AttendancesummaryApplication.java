package com.secret.attendancesummary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttendancesummaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendancesummaryApplication.class, args);
    }

}
