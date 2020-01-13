package ug.ShopApi.domain.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ug.ShopApi.domain.Product;


import java.util.HashSet;
import java.util.Set;

public class CategoryDto {

private int categoryId;
    private String name;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto() {}

    public CategoryDto(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}
