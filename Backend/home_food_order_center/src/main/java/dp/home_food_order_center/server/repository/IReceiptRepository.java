package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.ReceiptEntity;
import dp.home_food_order_center.server.data.entity.ReceiptStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
    List<ReceiptEntity> findAllByUserIdAndStatusCode (Long id, ReceiptStatusType statusCode);
    List<ReceiptEntity> findAllByStatusCode (ReceiptStatusType statusCode);
}
