package com.example.demo.validator;

import com.example.demo.exception.NotNullException;
import com.example.demo.exception.SizeException;
import com.example.demo.repository.ValidatorRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidatorUtils implements ValidatorRepository {

    @Override
    public void notBlank(String field, String fieldName) throws NotNullException {
        if (field == null || field.equals("") || field.equals(" -")) {
            throw new NotNullException(fieldName + " cannot be null or blank");
        }
    }

    @Override
    public void maxSize(String field, String fieldName, int size) throws SizeException {
        if (field.length() > size) {
            throw new SizeException(fieldName + " cannot be more than " + size);
        }
    }

    @Override
    public void minSize(String field, String fieldName, int size) throws SizeException {
        if (field.length() < size) {
            throw new SizeException(fieldName + " cannot be less than " + size);
        }
    }
}
