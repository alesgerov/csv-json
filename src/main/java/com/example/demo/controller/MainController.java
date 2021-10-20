package com.example.demo.controller;


import com.example.demo.service.CsvService;
import com.example.demo.service.UtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MainController {

    private final CsvService csvService;
    private final UtilService utilService;

    public MainController(CsvService csvService, UtilService utilService) {
        this.csvService = csvService;
        this.utilService = utilService;
    }

    @GetMapping(value = {"on/", "on"})
    public ResponseEntity<?> switchOn() {
        utilService.setSwitchedOn(true);
        return ResponseEntity.ok("Enabled");
    }

    @GetMapping(value = {"off/", "off"})
    public ResponseEntity<?> switchOf() {
        utilService.setSwitchedOn(false);
        return ResponseEntity.ok("Disable");
    }

    @GetMapping(value = {"donow/", "donow"})
    public ResponseEntity<?> doNow() {
        csvService.utilForScheduling();
        return ResponseEntity.ok("OK");
    }
}
