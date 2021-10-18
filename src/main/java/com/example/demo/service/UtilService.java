package com.example.demo.service;

import com.example.demo.exception.JsonToListException;
import com.example.demo.model.AlbumDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UtilService {

    private final AlbumService albumService;

    public UtilService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public List<AlbumDTO> jsonToAlbum(List<Map<?, ?>> json) throws JsonToListException {
        if (json.isEmpty()) throw new JsonToListException("Error happened while converting json");
        List<AlbumDTO> result=new ArrayList<>();
        for (Map<?, ?> map : json) {
            AlbumDTO dto = new AlbumDTO();
            String year = map.get("year").toString();
            String albumName = map.get("album").toString();
            String us = map.get("US_peak_chart_post").toString();
            if (us!=null && !us.equals("")) {
                dto.setUs_peak_chart_post(us.trim());
            }
            dto.setAlbum(albumName);
            dto.setYear(year.trim());
            albumService.saveAlbum(dto);
            result.add(dto);
        }
        return  result;
    }

}

