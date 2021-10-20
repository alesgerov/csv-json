package com.example.demo.repository;

import com.example.demo.entity.LogFileTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileTableRepository extends JpaRepository<LogFileTable, Long> {
    @Query(
            "select f from LogFileTable f " +
                    "where f.uploadDate=( select max(t.uploadDate) from LogFileTable t)"
    )
    LogFileTable findByMaxUploadDate();
}
