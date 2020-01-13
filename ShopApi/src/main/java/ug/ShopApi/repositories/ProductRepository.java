package ug.ShopApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ug.ShopApi.domain.Product;

import javax.persistence.NamedQuery;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Component
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p where p.name = ?1")
    Optional<Product>findProductByName(String name);

    @Query("select p from Product p order by p.name")
    Iterable<Product> findAllOrderedByNameProducts();

    @Query("select p from Product p where p.price between ?1 and ?2 ")
    Iterable<Product> findProductsInPriceRange(double start,double end);

    @Query("SELECT p FROM Product p JOIN fetch p.manufacturer WHERE p.name = ?1 ")
    Optional<Product> findFullProductByName(String name);

    @Query("SELECT Avg(p.price) FROM Product p")
    double findProductsAveragePrice();

    @Query("SELECT p FROM Product p JOIN fetch p.manufacturer WHERE p.id = ?1 ")
    Optional<Product> findFullProductById(long productId);

    Optional<Product> findTopByOrderByNameDesc();

    Optional<Product> findProductByproductId(long productId);

}
