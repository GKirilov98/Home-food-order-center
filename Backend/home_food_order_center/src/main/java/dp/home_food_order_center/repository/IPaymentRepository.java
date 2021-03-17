package dp.home_food_order_center.repository;

import dp.home_food_order_center.data.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
