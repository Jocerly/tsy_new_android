package com.tsy.tsy.entry;

import java.io.Serializable;

public class SecondCategoriesBean implements Serializable {
    private String id;//一级分类id
    private CategoriesBean subCategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CategoriesBean getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(CategoriesBean subCategories) {
        this.subCategories = subCategories;
    }
}
