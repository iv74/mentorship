package com.github.javamentorship.category.domain;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="parent_id", nullable = true)
    private Integer parentId;

    public Category() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}