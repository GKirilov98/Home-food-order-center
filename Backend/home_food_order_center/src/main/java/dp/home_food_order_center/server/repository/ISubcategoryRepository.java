package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.CategoryEntity;
import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
@Repository
public interface ISubcategoryRepository extends JpaRepository<SubcategoryEntity, Long> {
}
