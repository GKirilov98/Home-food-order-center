package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.OrderEntity;

import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public interface IOrderRepositoryCustom {
    void createOrderCustom(Long productId, int quantity, int newMaxQuantity, Long receiptId, BigDecimal amount);
    void deleteOrderCustom(OrderEntity orderEntity);
}
