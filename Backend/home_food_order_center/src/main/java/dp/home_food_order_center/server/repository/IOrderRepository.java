package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long>, IOrderRepositoryCustom {
}
