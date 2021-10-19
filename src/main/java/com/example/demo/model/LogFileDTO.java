package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogFileDTO {
    private String uploadedFileName;
    private String  errorFileName;
    private LocalDateTime uploadDate;
}
