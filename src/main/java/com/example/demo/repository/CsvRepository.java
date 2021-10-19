package com.example.demo.repository;

import com.example.demo.model.AlbumDTO;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface CsvRepository {
    Map<String,List<AlbumDTO>> csvToJson(File path);
}
