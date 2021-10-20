package com.example.demo.schedule;

import com.example.demo.service.CsvService;
import com.example.demo.service.UtilService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduleTask {

    private final CsvService service;
    private final UtilService utilService;

    public ScheduleTask(CsvService service, UtilService utilService) {
        this.service = service;
        this.utilService = utilService;
    }

    @Scheduled(fixedRate = 2 * 60000)
    public void addToDb() throws IOException {
        if (utilService.isSwitchedOn()) service.utilForScheduling();
    }
}
