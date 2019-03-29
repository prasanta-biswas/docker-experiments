package io.apitest.example.interfaces;

import io.apitest.example.model.Field;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */
public interface FieldService {
    public List<Field> getAllField();
    public Field getFieldById(long id);
    public Field saveField(Field field);
    public void removeField(Field field);
}
