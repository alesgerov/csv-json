package com.example.demo.exporter;


import com.example.demo.model.AlbumDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static void exportToExcel(List<AlbumDTO> albums, String path) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("albums");
        Row header = createHeaderRow(sheet);
        writeData(sheet, albums);
        workbook.write(new FileOutputStream(path));
    }

    private static Row createHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        Cell albumName = header.createCell(0);
        albumName.setCellValue("Album name");

        Cell year = header.createCell(1);
        year.setCellValue("Year");

        Cell us_peak_chart_post = header.createCell(2);
        us_peak_chart_post.setCellValue("Us Peak Chart Post");

        return header;
    }

    private static void writeData(Sheet sheet, List<AlbumDTO> albums) {
        for (int i = 0; i < albums.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Cell albumName = row.createCell(0);
            albumName.setCellValue(albums.get(i).getAlbum());

            Cell year = row.createCell(1);
            year.setCellValue(albums.get(i).getYear());

            Cell us_peak_chart_post = row.createCell(2);
            us_peak_chart_post.setCellValue(albums.get(i).getUs_peak_chart_post());
        }
    }

}

