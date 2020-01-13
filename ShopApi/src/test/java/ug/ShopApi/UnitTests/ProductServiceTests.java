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
import ug.ShopApi.services.Implementations.ProductServiceImplementation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProductServiceTests {

    @InjectMocks
    private ProductServiceImplementation _productService;

    @Mock
    private ProductRepository _productRepository;

    @Mock
    private CategoryRepository _categoryRepository;

    @Test
    void canReadById()
    {
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
        Product product =  new Product("name1",12.00,12,manufacturer);

        doReturn(Optional.of(product)).when(_productRepository).findProductByproductId(1);
        Product productInDb = _productService.readProductById(1);
        Assert.assertEquals(product,productInDb);
    }


    @Test
    void canReadOrderedByNameProducts()
    {
        List<Product> products =  new ArrayList<>();
        Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
        Product product1 =  new Product("name1",12.00,12,manufacturer);
        Product product2 =  new Product("name2",12.00,12,manufacturer);
        Product product3 =  new Product("name3",12.00,12,manufacturer);
        Product product4 =  new Product("name4",12.00,12,manufacturer);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        when(_productRepository.findAllOrderedByNameProducts()).thenReturn(products);

        List<Product> productList =  (List<Product>) _productService.readTopOrderedByAscendingProducts(3);

        Assert.assertEquals(4,productList.size());
        verify(_productRepository, times(1)).findAllOrderedByNameProducts();
    }

     @Test
     void CanReadList()
     {
         List<Product> products =  new ArrayList<>();
         Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
         Product product1 =  new Product("name1",12.00,12,manufacturer);
         Product product2 =  new Product("name2",12.00,12,manufacturer);
         Product product3 =  new Product("name3",12.00,12,manufacturer);
         Product product4 =  new Product("name4",12.00,12,manufacturer);

         products.add(product1);
         products.add(product2);
         products.add(product3);
         products.add(product4);

         when(_productRepository.findAll()).thenReturn(products);

         List<Product> productList =  (List<Product>) _productService.readProducts();

         Assert.assertEquals(4,productList.size());
         Assert.assertEquals(products,productList);
     }

     @Test
     void CanCreate()
     {
         Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
         Product product = new Product("Whey Extra",120.99,4,manufacturer);
        _productService.createProduct(product);
         verify(_productRepository, times(1)).save(product);
     }

     @Test
     void CanUpdate()
     {
         Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
         Product product = new Product("Whey Extra",120.99,4,manufacturer);
         _productService.updateProduct(product);
         verify(_productRepository, times(1)).save(product);
     }

     @Test
     void CanDelete()
     {
         _productService.deleteProductById(1);
         verify(_productRepository,times(1)).deleteById(1L);
     }

    @Test
    void CanDeleteAll()
    {
        _productService.deleteAll();
        verify(_productRepository,times(1)).deleteAll();
    }


    @Test void CanAddCategory()
     {
         Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
         Product product1 =  new Product("name1",12.00,12,manufacturer);
         Category category1 =  new Category("cat1");
         doReturn(Optional.of(product1)).when(_productRepository).findProductByproductId(1);
         doReturn(Optional.of(category1)).when(_categoryRepository).findById(1);

         _productService.addCategory(1,1);
         verify(_productRepository,times(1)).save(product1);
     }
}
