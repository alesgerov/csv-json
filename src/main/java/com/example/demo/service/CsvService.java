package com.example.demo.service;

import com.example.demo.exception.JsonToListException;
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

    public CsvService(UtilService utilService, FileTableService fileService) {
        this.utilService = utilService;
        this.fileService = fileService;
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
            File file = new File("D:\\Projects\\Java\\demo3\\files\\dummy.csv");
            //TODO or integration with sap
            List<AlbumDTO> success = csvToJson(file).get("success");
            List<AlbumDTO> errors = csvToJson(file).get("error");
            LogFileDTO fileDTO=new LogFileDTO();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime time = LocalDateTime.now();
            String name = time.format(formatter);
            String successPath = "D:\\Projects\\Java\\demo3\\success\\" + name;
            String errorPath = "D:\\Projects\\Java\\demo3\\errors\\" + name;
            fileDTO.setUploadDate(time);
            if (!success.isEmpty()) {
                utilService.writeJson(success, successPath, name);
                fileDTO.setUploadedFileName(successPath);
            }
            if (!errors.isEmpty()) {
                utilService.writeJson(errors, errorPath, name);
                fileDTO.setErrorFileName(errorPath);
            }
            fileService.saveFile(fileDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}