package io.apitest.example.service;

import io.apitest.example.interfaces.FieldService;
import io.apitest.example.model.Field;
import io.apitest.example.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Service("fieldService")
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;


    @Override
    public List<Field> getAllField() {
        return fieldRepository.findAll();
    }

    @Override
    public Field getFieldById(long id) {
        return fieldRepository.findOne(id);
    }

    @Override
    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public void removeField(Field field) {
        fieldRepository.delete(field);
    }
}
