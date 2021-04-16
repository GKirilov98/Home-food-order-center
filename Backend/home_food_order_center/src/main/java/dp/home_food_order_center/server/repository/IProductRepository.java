package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllBySubcategoryIdIn(List<Long> subcategoriesId);
}
