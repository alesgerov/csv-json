package com.example.demo.controller;


import com.example.demo.service.CsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MainController {

    private final CsvService csvService;

    public MainController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("convert")
    public ResponseEntity<?> toJson(){
        return ResponseEntity.ok(csvService.csvToJson("D:\\Projects\\Java\\demo3\\dummy.csv"));
    }
}
