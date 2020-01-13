package ug.ShopApi.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductFormViewModel {
    private Product product;
    private ArrayList<Category> allCategories;
    private ArrayList<Manufacturer> allManufacturers;
    private int newCategoryId;
    private int deletedCategoryId;

    public ProductFormViewModel(Product product, ArrayList<Category> allCategories, ArrayList<Manufacturer> allManufacturers) {
        this.product = product;
        this.allCategories = allCategories;
        this.allManufacturers =allManufacturers;
    }

    public ProductFormViewModel(Product product, ArrayList<Category> allCategories, Category newCategory, Category deletedCategory) {
        this.product = product;
        this.allCategories = allCategories;
    }

    public ProductFormViewModel() {}

    public ArrayList<Manufacturer> getAllManufacturers() {
        return allManufacturers;
    }

    public void setAllManufacturers(ArrayList<Manufacturer> allManufacturers) {
        this.allManufacturers = allManufacturers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(ArrayList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public int getNewCategoryId() {
        return newCategoryId;
    }

    public void setNewCategoryId(int newCategoryId) {
        this.newCategoryId = newCategoryId;
    }

    public int getDeletedCategoryId() {
        return deletedCategoryId;
    }

    public void setDeletedCategoryId(int deletedCategoryId) {
        this.deletedCategoryId = deletedCategoryId;
    }


}
