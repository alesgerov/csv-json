package com.example.demo.repository;

import com.example.demo.exception.NotNullException;
import com.example.demo.exception.SizeException;

public interface ValidatorRepository {
    void notBlank(String field,String fieldName) throws NotNullException;
    void maxSize(String field,String fieldName,int size) throws SizeException;
    void minSize(String field,String fieldName,int size) throws SizeException;
}
