package com.tinlm.snef.model;

public class Categories {
    private int categoriesId;
    private String CategoryName;
    private String imageSrc;

    public Categories() {
    }

    public Categories(int categoriesId, String categoryName, String imageSrc) {
        this.categoriesId = categoriesId;
        CategoryName = categoryName;
        this.imageSrc = imageSrc;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
