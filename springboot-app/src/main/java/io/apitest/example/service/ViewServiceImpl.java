package io.apitest.example.service;

import io.apitest.example.interfaces.ViewService;
import io.apitest.example.model.View;
import io.apitest.example.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Service("viewService")
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewRepository viewRepository;


    @Override
    public List<View> getAllView() {
        return viewRepository.findAll();
    }

    @Override
    public View getViewById(long id) {
        return viewRepository.findOne(id);
    }

    @Override
    public View saveView(View view) {
        return viewRepository.save(view);
    }

    @Override
    public void removeView(View view) {
        viewRepository.delete(view);
    }
}
