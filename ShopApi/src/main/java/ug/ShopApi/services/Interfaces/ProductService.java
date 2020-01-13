package ug.ShopApi.services.Interfaces;


import ug.ShopApi.domain.Category;
import ug.ShopApi.domain.Product;

public interface ProductService {
     void createProduct(Product product);
     Product readProductById(long productId);
     Product readProductByName(String productName);
     Iterable<Product> readProducts();
     Iterable<Product> readTopOrderedByAscendingProducts(int numberOfProducts);
     void updateProduct(Product product);
     void deleteProductById(long productId);
     void deleteProduct(Product product);
     void changeProductManufacturerAndDelete(long productId,long newManufacturerId);
     boolean addCategory(long productId,int categoryId);
     boolean removeCategory(long productId,int categoryId);
     void deleteAll();
}
