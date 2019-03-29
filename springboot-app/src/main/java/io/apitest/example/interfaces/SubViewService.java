package io.apitest.example.interfaces;

import io.apitest.example.model.SubView;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */
public interface SubViewService {
    public List<SubView> getAllSubView();
    public SubView getSubViewById(long id);
    public SubView saveSubView(SubView subView);
    public void removeSubView(SubView subView);
}
