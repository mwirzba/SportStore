package ug.ShopApi.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ug.ShopApi.domain.Category;
import ug.ShopApi.repositories.CategoryRepository;
import ug.ShopApi.services.Interfaces.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository _categoryRepository;


    @Override
    public void createCategory(Category category) {
        _categoryRepository.save(category);
    }

    @Override
    public Category readCategoryById(int categoryId) {
        return _categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category readCategoryByName(String categoryName) {
        return _categoryRepository.findCategoryByName(categoryName).orElse(null);
    }

    @Override
    public Iterable<Category> readCategories() {
        return _categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Category category) {
         _categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        _categoryRepository.deleteById(categoryId);
    }

    @Override
    public void deleteCategory(Category category) {
        _categoryRepository.delete(category);
    }
}
