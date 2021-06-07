package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.server.data.entity.OrderEntity;
import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IOrderRepository;
import dp.home_food_order_center.server.service.IOrderService;
import dp.home_food_order_center.server.service.IProductService;
import dp.home_food_order_center.server.service.IReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
@Service
public class IOrderServiceImpl implements IOrderService {
    private final Logger logger = LogManager.getLogger(IOrderServiceImpl.class);

    private final IOrderRepository orderRepository;
    private final IReceiptService receiptService;
    private final IProductService productService;

    public IOrderServiceImpl(IOrderRepository orderRepository, IReceiptService receiptService, IProductService productService) {
        this.orderRepository = orderRepository;
        this.receiptService = receiptService;
        this.productService = productService;
    }

    @Override
    public String insertOne(Long productId, int quantity) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting insertOne service!", logId));
            //Взимане на параметрите на продукта
            ProductDetailsModel productDetails = this.productService.getOneById(productId).get(0);
            int newMaxQuantity = productDetails.getAvailableQuantity() - quantity;
            if (newMaxQuantity < 0) {
                String message = String.format("Not available quantity! Max quantity: %d", productDetails.getAvailableQuantity());
                logger.error(String.format("%s: %s", logId, message));
                throw new GlobalServiceException(logId, "Няма достатъчно количество от продукта!", message);
            }

            //Взимане на userId
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = ((UserDetailsImpl) principal).getId();
            //Взимане на количка
            Long receiptId = this.receiptService.getNotPaidReceiptByUserId(userId).getId();
            //Изчисляване на сумата
            BigDecimal amount = this.calculateAmount(productDetails.getPrice(), quantity);

            this.orderRepository.createOrderCustom(productId, quantity, newMaxQuantity, receiptId, amount);
            return "Successful create an order!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected insertOne service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished insertOne service!", logId));
        }
    }

    @Override
    public String deleteOneById(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting deleteOneById service!", logId));
            OrderEntity orderEntity = this.orderRepository.findById(id).orElse(null);
            if (orderEntity == null) {
                logger.error(String.format("%s: Couldn't fount order with id: %d", logId, id));
                throw new GlobalServiceException(logId, "Невалидно ид на поръчка", String.format("Couldn't fount order with id: %d", id));
            }

            this.orderRepository.deleteOrderCustom(orderEntity);
            return "Successfully delete an order!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected deleteOneById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished deleteOneById service!", logId));
        }

    }


    private BigDecimal calculateAmount(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
