package dp.home_food_order_center.server.repository.impl;

import dp.home_food_order_center.server.data.entity.*;
import dp.home_food_order_center.server.repository.IOrderRepository;
import dp.home_food_order_center.server.repository.IProductRepository;
import dp.home_food_order_center.server.repository.IReceiptRepository;
import dp.home_food_order_center.server.repository.IUserRepositoryCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/9/2021
 */
@Repository
@Transactional
public class IUserRepositoryCustomImpl implements IUserRepositoryCustom {
    private final Logger logger = LogManager.getLogger(IUserRepositoryCustomImpl.class);

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IReceiptRepository receiptRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void deleteUser(UserEntity userEntity) {
        logger.info("Starting deleteUserById repository!");

        for (ReceiptEntity receipt : userEntity.getReceipts()) {
            List<OrderEntity> myList = new ArrayList<>(receipt.getOrders());
            Iterator<OrderEntity> it = myList.iterator();

            while (it.hasNext()) {
                OrderEntity order = it.next();
                orderRepository.deleteOrderCustom(order);
//                this.orderRepository.delete(order);
            }

            this.receiptRepository.deleteById(receipt.getId());
        }

//        for (ReceiptEntity receipt : userEntity.getReceipts()) {
//            this.receiptRepository.deleteById(receipt.getId());
//        }


        userEntity.getRoles().clear();
        entityManager.remove(userEntity);

        logger.info("Finished deleteUserById repository!");
    }
}
