package ug.ShopApi.services.Implementations;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;
import ug.ShopApi.repositories.CategoryRepository;
import ug.ShopApi.repositories.ManufacturerRepository;
import ug.ShopApi.repositories.ProductRepository;
import ug.ShopApi.services.Interfaces.ProductService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Service
@ComponentScan()
public class ProductServiceImplementation implements ProductService {

    @PersistenceContext
    private EntityManager _em;

    @Autowired
    private ProductRepository _productRepository;

    @Autowired
    private CategoryRepository _categoryRepository;

    @Autowired
    private ManufacturerRepository _manufacturerRepository;

    public void createProduct(Product product) {
        _productRepository.save(product);
    }

    @Override
    public Product readProductById(long productId) {
        return  _productRepository.findProductByproductId(productId).orElse(null);
    }

    @Override
    public Product readProductByName(String productName) {

        return  _em.createNamedQuery("product_findByName",Product.class)
                .setParameter("productName",productName)
                .getSingleResult();
    }

    public Iterable<Product> readProducts() {
        return _productRepository.findAll();
    }

    @Override
    public Iterable<Product> readTopOrderedByAscendingProducts(int numberOfProducts) {
        return _productRepository.findAllOrderedByNameProducts();
    }

    public void updateProduct(Product product) {
        _productRepository.save(product);
    }
    public void deleteProductById(long productId) { _productRepository.deleteById(productId); }

    @Transactional
    public  void deleteProduct(Product product) {
        Product a = _em.find(Product.class, product.getProductId());

        Query q = _em.createNativeQuery("DELETE FROM Product_Category pc WHERE pc.product_id= ?");
        q.setParameter(1, a.getProductId());
        q.executeUpdate();

        q = _em.createNativeQuery("DELETE FROM Product p WHERE p.id = ?");
        q.setParameter(1, a.getProductId());
        q.executeUpdate();
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public void changeProductManufacturerAndDelete(long productId, long newManufacturerId) throws RuntimeException{
            Product productInDb = _productRepository.findProductByproductId(productId).orElse(null);

            if (productInDb == null) {
                throw new RuntimeException();
            }

            long oldManuFacturerId;
            oldManuFacturerId = productInDb.getManufacturer().getManufacturerId();

            boolean isManufacturerUsed = false;

            List<Product> products = _productRepository.findAll();
            for (Product p : products) {
                if (p.getManufacturer().getManufacturerId() == oldManuFacturerId && p.getProductId() != productInDb.getProductId()) {
                    isManufacturerUsed = true;
                }
            }

            if (!isManufacturerUsed)
                _manufacturerRepository.deleteById(oldManuFacturerId);

            Manufacturer manufacturerInDb = _manufacturerRepository.findById(newManufacturerId).orElse(null);
            if(manufacturerInDb==null)
            {
                throw new RuntimeException();
            }

            productInDb.setManufacturer(manufacturerInDb);
    }

    @Override
    public boolean addCategory(long productId, int categoryId) {
        Product productInDb = _productRepository.findProductByproductId(productId).orElse(null);
        if(productInDb==null)
        {
            return false;
        }
        Category categoryInDb = _categoryRepository.findById(categoryId).orElse(null);
        if(categoryInDb == null)
        {
            return false;
        }
        if(!productInDb.getCategories().contains(categoryInDb))
            productInDb.addCategory(categoryInDb);
        _productRepository.save(productInDb);
        return true;
    }

    @Override
    public boolean removeCategory(long productId, int categoryId) {
        Product productInDb = _productRepository.findProductByproductId(productId).orElse(null);
        if(productInDb==null) {
            return false;
        }
        Category categoryInDb = _categoryRepository.findById(categoryId).orElse(null);
        if(categoryInDb == null) {
            return false;
        }
        if(productInDb.getCategories().contains(categoryInDb))
            productInDb.removeBCategory(categoryInDb);
        _productRepository.save(productInDb);
        return true;
    }

    @Override
    public void deleteAll() {
        _productRepository.deleteAll();
    }

}

