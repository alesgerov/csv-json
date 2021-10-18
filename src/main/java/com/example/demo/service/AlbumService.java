package com.example.demo.service;

import com.example.demo.entity.Album;
import com.example.demo.model.AlbumDTO;
import com.example.demo.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository repository;

    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    public AlbumDTO getAlbumById(long id){
        Optional<Album> optional=repository.findById(id);
        if (optional.isEmpty()){
            return  null;
        }
        Album album=optional.get();
        AlbumDTO albumDTO=new AlbumDTO();
        albumDTO.setAlbum(album.getAlbum_name());
        albumDTO.setYear(album.getYear());
        albumDTO.setUs_peak_chart_post(album.getUs_peak_chart_post());
        return albumDTO;
    }

    public AlbumDTO saveAlbum(AlbumDTO dto){
        Album album=new Album();
        album.setAlbum_name(dto.getAlbum());
        album.setYear(dto.getYear());
        album.setUs_peak_chart_post(dto.getUs_peak_chart_post());
        album =repository.save(album);
        if (album==null) return  null;
        return dto;
    }
}
