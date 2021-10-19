package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class LogFileTable {
    @Id
    @SequenceGenerator(
            name = "log_id_seq",
            sequenceName = "log_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "log_id_seq"
    )
    private long id;
    private String uploadedFileName;
    private String  errorFileName;
    private LocalDateTime uploadDate;
}
