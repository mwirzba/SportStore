package ug.ShopApi;

import org.hibernate.Session;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;
import ug.ShopApi.repositories.ManufacturerRepository;
import ug.ShopApi.repositories.ProductRepository;
import ug.ShopApi.services.Interfaces.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
public class IntegrationTests {

    @Autowired
    private ProductService _productService;

    @Autowired
    private ProductRepository _productRepository;

    @Autowired
    private ManufacturerRepository _manufacturerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void testAddTransaction() {
        boolean rolledBack = false;
        try {
            Manufacturer manufacturer = new Manufacturer("test1",(short) 1998,"rep1");
            Product product =  new Product("name1",12.00,12,manufacturer);
            _productRepository.save(product);
            _productRepository.flush();

        }
        catch (InvalidDataAccessApiUsageException e)
        {
            rolledBack = true;
        }

        Assert.assertTrue(rolledBack);
    }

    @Test
    void testManualTransaction()
    {
        Manufacturer manufacturer = _manufacturerRepository.findBymanufacturerId(4).orElse(null);
        Assert.assertNotNull(manufacturer);
        boolean rolledBack =false;
        try{

            _productService.changeProductManufacturerAndDelete(13,20);

        }
        catch(RuntimeException e) {
            rolledBack = true;
        }

        manufacturer = _manufacturerRepository.findBymanufacturerId(4).orElse(null);
        Assert.assertNotNull(manufacturer);
        Assert.assertTrue(rolledBack);
    }

    @Test
    void testFindProductsInPriceRangeQuery()
    {
        Manufacturer manufacturer = _manufacturerRepository.findBymanufacturerId(1).orElse(null);
        Assert.assertNotNull(manufacturer);
        Product product =  new Product("name1",1000.00,12,manufacturer);
        _productRepository.save(product);
        List<Product> productsInRange = (List<Product>) _productRepository.findProductsInPriceRange(900,1100);
        Assert.assertEquals(1,productsInRange.size());
    }

    @Test
    void testFindFirstByOrderByName()
    {
        Manufacturer manufacturer = new Manufacturer("a",(short) 1998,"rep1");
        _manufacturerRepository.save(manufacturer);
        Manufacturer manufacturerInDb =  _manufacturerRepository.findFirstByOrderByNameDesc().orElse(null);
        Assert.assertNotNull(manufacturerInDb);
        Assert.assertEquals(manufacturer.getName(),manufacturerInDb.getName());
    }


    @Test
    void testCanAddCategory()
    {
        Manufacturer manufacturer = _manufacturerRepository.findBymanufacturerId(1).orElse(null);
        Product product =  new Product("test",100,12,manufacturer);
        _productService.createProduct(product);
        long productId = _productService.readProductByName("test").getProductId();
        _productService.addCategory(productId,3);
        Product productWithCategory =  _productService.readProductById(productId);

        Assert.assertFalse(productWithCategory.getCategories().isEmpty());

    }

    @Test
    void testCanReadByName()
    {
        Manufacturer manufacturer = _manufacturerRepository.findBymanufacturerId(1).orElse(null);
        Assert.assertNotNull(manufacturer);
        Product product =  new Product("nameCanRead",100,12,manufacturer);
        _productRepository.save(product);
        Product productInDb = _productService.readProductByName("nameCanRead");
        Assert.assertEquals(product,productInDb);
        Assert.assertNotNull(productInDb);
    }

    @Test
    void testCanGetAverage()
    {
        double avg = 0;
        List<Product> products = _productRepository.findAll();
        for (Product product : products)
        {
            avg+= product.getPrice();
        }
        avg/=products.size();
        double avgFormDb = _productRepository.findProductsAveragePrice();
        Assert.assertEquals(avg, avgFormDb, 0);
    }

    @Test
    void testCanRepoFindProductByName()
    {
        Manufacturer manufacturer = _manufacturerRepository.findBymanufacturerId(1).orElse(null);
        Assert.assertNotNull(manufacturer);
        Product product =  new Product("canRead",100,12,manufacturer);
        _productRepository.save(product);
        Product productInDb = _productRepository.findProductByName("canRead").orElse(null);
        Assert.assertNotNull(productInDb);
    }




}
