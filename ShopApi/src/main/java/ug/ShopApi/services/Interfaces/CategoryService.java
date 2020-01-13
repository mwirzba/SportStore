package ug.ShopApi.services.Interfaces;


import ug.ShopApi.domain.Category;

public interface CategoryService {
    void createCategory(Category category);
    Category readCategoryById(int categoryId);
    Category readCategoryByName(String categoryName);
    Iterable<Category> readCategories();
    void updateCategory(Category  category);
    void deleteCategoryById(int categoryId);
    void deleteCategory(Category category);
}
