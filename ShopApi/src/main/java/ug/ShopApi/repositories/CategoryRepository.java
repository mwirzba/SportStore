package ug.ShopApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ug.ShopApi.domain.Category;


import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Integer> {
    Optional<Category> findCategoryByName(String categoryName);
}
