package com.example.demo.service;

import com.example.demo.config.PathConfig;
import com.example.demo.exception.JsonToListException;
import com.example.demo.exporter.ExcelExporter;
import com.example.demo.model.AlbumDTO;
import com.example.demo.model.LogFileDTO;
import com.example.demo.repository.CsvRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class CsvService implements CsvRepository {
    private final UtilService utilService;
    private final FileTableService fileService;
    private final PathConfig config;

    public CsvService(UtilService utilService, FileTableService fileService, PathConfig config) {
        this.utilService = utilService;
        this.fileService = fileService;
        this.config = config;
    }

    @Override
    public Map<String, List<AlbumDTO>> csvToJson(File input) {
        List<Map<?, ?>> data = null;
        try {
            data = utilService.readObjectsFromCsv(input);
            return utilService.jsonToAlbum(data);
        } catch (IOException | JsonToListException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void utilForScheduling() {
        try {
            File file = new File(config.getFile() + "dummy.csv");
            List<AlbumDTO> success = csvToJson(file).get("success");
            List<AlbumDTO> errors = csvToJson(file).get("error");
            LogFileDTO fileDTO = new LogFileDTO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime time = LocalDateTime.now();
            String name = time.format(formatter);
            String successPath = config.getSuccess() + name + ".xls";
            String errorPath = config.getError() + name + ".xls";
            fileDTO.setUploadDate(time);
            if (!success.isEmpty()) {
//                utilService.writeJson(success, successPath, name);
                ExcelExporter.exportToExcel(success, successPath);
                fileDTO.setUploadedFileName(successPath);
            }
            if (!errors.isEmpty()) {
//                utilService.writeJson(errors, errorPath, name);
                ExcelExporter.exportToExcel(errors, errorPath);
                fileDTO.setErrorFileName(errorPath);
            }
            fileService.saveFile(fileDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
