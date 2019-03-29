package io.apitest.example.interfaces;

import io.apitest.example.model.View;

import java.util.List;

/**
 * Created by prasantabiswas on 28/06/18.
 */
public interface ViewService {
    public List<View> getAllView();
    public View getViewById(long id);
    public View saveView(View view);
    public void removeView(View view);
}
