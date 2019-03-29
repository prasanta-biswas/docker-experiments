package io.apitest.example.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by prasantabiswas on 26/06/18.
 */

@Entity
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @NotEmpty
    @ManyToMany(targetEntity=Field.class)
    private List<Field> fields;

    @ManyToMany(targetEntity=SubView.class)
    private List<SubView> subViews;

    public View() {
        super();
    }

    public View(long id, String name, String description, List<Field> fields, List<SubView> subViews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fields = fields;
        this.subViews = subViews;
    }

    public View(long id, String name, String description, List<Field> fields) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public View(String name, String description, List<Field> fields, List<SubView> subViews) {
        this.name = name;
        this.description = description;
        this.fields = fields;
        this.subViews = subViews;
    }

    public View(String name, String description, List<Field> fields) {
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<SubView> getSubViews() {
        return subViews;
    }

    public void setSubViews(List<SubView> subViews) {
        this.subViews = subViews;
    }

    @Override
    public String toString() {
        return "View{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                ", subViews=" + subViews +
                '}';
    }
}
