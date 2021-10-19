package com.example.demo.service;

import com.example.demo.exception.JsonToListException;
import com.example.demo.exception.NotNullException;
import com.example.demo.model.AlbumDTO;
import com.example.demo.validator.ValidatorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilService {

    private final AlbumService albumService;
    private final ValidatorUtils validationService;
    private boolean switchedOn=false;

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public void setSwitchedOn(boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    public UtilService(AlbumService albumService, ValidatorUtils validationService) {
        this.albumService = albumService;
        this.validationService = validationService;
    }


    public Map<String,List<AlbumDTO>> jsonToAlbum(List<Map<?, ?>> json) throws JsonToListException, IOException {
        if (json.isEmpty()) throw new JsonToListException("Error happened while converting json");
        Map<String,List<AlbumDTO>> resultMap= new HashMap<>();
        List<AlbumDTO> result = new ArrayList<>();
        List<AlbumDTO> errors=new ArrayList<>();
        for (Map<?, ?> map : json) {
            AlbumDTO dto = new AlbumDTO();
//            System.out.println(map);
            String year = map.get("year").toString();
            String albumName = map.get("album").toString();
            String us = map.get("US_peak_chart_post").toString();

            try {
                validationService.notBlank(us, "US_peak_chart_post");
                validationService.notBlank(year, "year");
                validationService.notBlank(albumName, "album");
                validationService.maxSize(year.trim(),"year",4);
                validationService.minSize(year.trim(),"year",4);
                dto.setAlbum(albumName);
                dto.setYear(year.trim());
                dto.setUs_peak_chart_post(us.trim());
                albumService.saveAlbum(dto);
                result.add(dto);
            }catch (Exception e){
                AlbumDTO error=new AlbumDTO();
                error.setYear(year);
                error.setAlbum(albumName);
                error.setUs_peak_chart_post(us);
                errors.add(error);
            }
        }
        resultMap.put("success",result);
        resultMap.put("error",errors);
        return resultMap;
    }



    public   void writeJson(List<AlbumDTO> data,String folder,String  name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file=new File(folder);
        mapper.writeValue(file, data);
    }

    private    String objectToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }


    public   List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }



}
