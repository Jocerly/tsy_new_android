package com.tsy.tsy.entry;

import java.io.Serializable;
import java.util.List;

public class CategoriesBean implements Serializable {

    private String id;
    private String imgURL;
    private String name;
    private List<CategoriesBean> subCategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoriesBean> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoriesBean> subCategories) {
        this.subCategories = subCategories;
    }
}
