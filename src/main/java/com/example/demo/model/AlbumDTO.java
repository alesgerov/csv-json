package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AlbumDTO {

    private String album;

    private String year;

    private String us_peak_chart_post;
}
