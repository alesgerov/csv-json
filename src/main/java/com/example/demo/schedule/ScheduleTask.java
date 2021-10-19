package com.example.demo.schedule;

import com.example.demo.service.CsvService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ScheduleTask {

    private final CsvService service;


    public ScheduleTask(CsvService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 30000)
    public void addToDb() throws IOException {
        service.utilForScheduling();
    }
}
