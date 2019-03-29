package io.apitest.example.service;

import io.apitest.example.interfaces.SubViewService;
import io.apitest.example.interfaces.ViewService;
import io.apitest.example.model.SubView;
import io.apitest.example.model.View;
import io.apitest.example.repository.SubViewRepository;
import io.apitest.example.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Service("subViewService")
public class SubViewServiceImpl implements SubViewService {

    @Autowired
    private SubViewRepository subViewRepository;


    @Override
    public List<SubView> getAllSubView() {
        return subViewRepository.findAll();
    }

    @Override
    public SubView getSubViewById(long id) {
        return subViewRepository.findOne(id);
    }

    @Override
    public SubView saveSubView(SubView subView) {
        return subViewRepository.save(subView);
    }

    @Override
    public void removeSubView(SubView subView) {
        subViewRepository.delete(subView);
    }
}
