package com.example.demo.service;

import com.example.demo.exception.JsonToListException;
import com.example.demo.repository.CsvRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CsvService implements CsvRepository {
    private final UtilService utilService;

    public CsvService(UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public List csvToJson(String path) {
        File input=new File(path);
        File output=new File("D:\\Projects\\Java\\demo3\\data.json");
        List<Map<?, ?>> data = null;
        try {
            data = readObjectsFromCsv(input);
            return utilService.jsonToAlbum(data);
        } catch (IOException | JsonToListException e) {
            e.printStackTrace();
        }
        return null;
    }

    private  List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }

    private  void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
}
