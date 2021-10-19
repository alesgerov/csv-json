package com.example.demo.controller;


import com.example.demo.service.CsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class MainController {

    private final CsvService csvService;

    public MainController(CsvService csvService) {
        this.csvService = csvService;
    }

//    @GetMapping("convert")
//    public ResponseEntity<?> toJson(@RequestParam("file")MultipartFile file){
//        return ResponseEntity.ok(csvService.csvToJson("D:\\Projects\\Java\\demo3\\files\\dummy.csv"));
//    }
}
