package com.github.javamentorship.category.command;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Validated
public class CategoryUpdateForm {

    @NotEmpty
    @Size(max = 64)
    private String name;

    private int id; //TODO change primitive int to Integer - it will allow us to keep null values

    @Min(1) //TODO remove this constraint - we will don't use it
    private int parentId; //TODO change primitive int to Integer

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
