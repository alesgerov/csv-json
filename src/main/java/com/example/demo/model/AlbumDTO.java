package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AlbumDTO {

    @NotBlank(message = "Albom name cannot be blank")
    private String album;

    @NotBlank(message = "Year cannot be blank")
    @Size(max = 4)
    private String year;

    @NotBlank(message = "Year cannot be blank")
    private String us_peak_chart_post;
}
