package dp.home_food_order_center.server.repository.impl;

import dp.home_food_order_center.server.data.entity.OrderEntity;
import dp.home_food_order_center.server.data.entity.ProductEntity;
import dp.home_food_order_center.server.data.entity.ReceiptEntity;
import dp.home_food_order_center.server.data.entity.ReceiptStatusType;
import dp.home_food_order_center.server.repository.IOrderRepositoryCustom;
import dp.home_food_order_center.server.repository.IProductRepository;
import dp.home_food_order_center.server.repository.IReceiptRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
@Repository
@Transactional
public class IOrderRepositoryCustomImpl implements IOrderRepositoryCustom {
    private final Logger logger = LogManager.getLogger(IOrderRepositoryCustomImpl.class);

    @Autowired
    private IReceiptRepository receiptRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void createOrderCustom(Long productId, int quantity, int newMaxQuantity,
                                  Long receiptId, BigDecimal amount) {
        logger.info("Starting createOrderCustom repository!");
        //Промяна на количката
        ReceiptEntity receiptEntity = this.receiptRepository.findById(receiptId).orElse(null);
        receiptEntity.setTotalAmount(receiptEntity.getTotalAmount().add(amount));
        this.receiptRepository.saveAndFlush(receiptEntity);

        //Промяна на продукта
        ProductEntity productEntity = this.productRepository.findById(productId).orElse(null);
        productEntity.setAvailableQuantity(newMaxQuantity);
        this.productRepository.saveAndFlush(productEntity);

        //Записване на поръчка
        OrderEntity entityForSave = new OrderEntity();
        entityForSave.setOrderDate(new Timestamp(System.currentTimeMillis()));
        entityForSave.setAmount(amount);
        entityForSave.setNeededQuantity(quantity);
        entityForSave.setReceipt(receiptEntity);
        entityForSave.setProduct(productEntity);
        this.entityManager.persist(entityForSave);
        logger.info("Finished createOrderCustom repository!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void deleteOrderCustom(OrderEntity orderEntity) {
        if (orderEntity.getReceipt().getStatusCode().equals(ReceiptStatusType.SHOPPING)) {
            //Връщане на количеството
            ProductEntity product = orderEntity.getProduct();
            product.setAvailableQuantity(product.getAvailableQuantity() + orderEntity.getNeededQuantity());
            this.productRepository.saveAndFlush(product);

            //Намаляване на стойността на количката
            ReceiptEntity receipt = orderEntity.getReceipt();
            receipt.getOrders().remove(orderEntity);
            receipt.setTotalAmount(receipt.getTotalAmount().subtract(orderEntity.getAmount()));
            this.receiptRepository.saveAndFlush(receipt);
        }

        //Изтриване на поръчката
        this.entityManager.remove(orderEntity);
    }
}
