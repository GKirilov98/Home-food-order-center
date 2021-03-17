package dp.home_food_order_center.repository;

import dp.home_food_order_center.data.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
