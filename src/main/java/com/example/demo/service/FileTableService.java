package com.example.demo.service;

import com.example.demo.entity.LogFileTable;
import com.example.demo.model.LogFileDTO;
import com.example.demo.repository.FileTableRepository;
import org.springframework.stereotype.Service;

@Service
public class FileTableService {
    private final FileTableRepository repository;

    public FileTableService(FileTableRepository repository) {
        this.repository = repository;
    }

    public LogFileTable getFileByMaxUploadDate() {
        return repository.findByMaxUploadDate();
    }

    public LogFileTable saveFile(LogFileDTO dto) {
        LogFileTable table = new LogFileTable();
        if (dto.getErrorFileName() != null) table.setErrorFileName(dto.getErrorFileName());
        if (dto.getUploadedFileName() != null) table.setUploadedFileName(dto.getUploadedFileName());
        table.setUploadDate(dto.getUploadDate());
        return repository.save(table);
    }

}
