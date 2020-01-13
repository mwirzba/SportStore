package ug.ShopApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ug.ShopApi.domain.Manufacturer;
import ug.ShopApi.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

    @Query("SELECT p FROM Manufacturer p where p.name = ?1")
    Optional<Manufacturer> findByName(String name);

    Optional<Manufacturer> findFirstByOrderByNameDesc();


    Optional<Manufacturer> findBymanufacturerId(long manufacturerId);
}
