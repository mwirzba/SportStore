package ug.ShopApi.UnitTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;
import ug.ShopApi.repositories.CategoryRepository;
import ug.ShopApi.repositories.ProductRepository;
import ug.ShopApi.services.Implementations.CategoryServiceImplementation;
import ug.ShopApi.services.Implementations.ProductServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class CategoryServiceTests {

    @InjectMocks
    private CategoryServiceImplementation _categoryService;

    @Mock
    private CategoryRepository _categoryRepository;

    @Test
    void canReadById()
    {
        Category category = new Category("name1");
        doReturn(Optional.of(category)).when(_categoryRepository).findById(1);
        Category categoryInDb = _categoryService.readCategoryById(1);
        Assert.assertEquals(category,categoryInDb);
    }

    @Test
    void canReadByName()
    {
        Category category = new Category("name1");
        doReturn(Optional.of(category)).when(_categoryRepository).findCategoryByName("name1");
        Category categoryInDb = _categoryService.readCategoryByName("name1");
        Assert.assertEquals(category,categoryInDb);
    }

    @Test
    void CanReadList()
    {
        List<Category> categories=  new ArrayList<>();
        Category category1 = new Category("name1");
        Category category2 = new Category("name2");
        Category category3 = new Category("name3");

       categories.add(category1);
       categories.add(category2);
       categories.add(category3);

        when(_categoryRepository.findAll()).thenReturn(categories);

        List<Category> categoriesInDb =  (List<Category>) _categoryService.readCategories();

        Assert.assertEquals(3,categoriesInDb.size());
        Assert.assertEquals(categories,categoriesInDb);
    }

    @Test
    void CanCreate()
    {
        Category category = new Category("name1");
        _categoryService.createCategory(category);
        verify(_categoryRepository,times(1)).save(category);
    }

    @Test
    void CanUpdate()
    {
        Category category = new Category("name1");
        _categoryService.updateCategory(category);
        verify(_categoryRepository,times(1)).save(category);
    }

    @Test
    void CanDelete()
    {
        _categoryService.deleteCategoryById(1);
        verify(_categoryRepository,times(1)).deleteById(1);
    }

    @Test
    void CanDeleteByName()
    {
        Category category = new Category("cat1");
        _categoryService.deleteCategory(category);
        verify(_categoryRepository,times(1)).delete(category);
    }

}
