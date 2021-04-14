package dp.home_food_order_center.server.data.model.order;

import dp.home_food_order_center.server.data.model.product.ProductModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/6/2021
 */
public class OrderModel implements Serializable {
    private Long id;
    private Timestamp orderDate;
    private BigDecimal amount;
    private int neededQuantity;
    private ProductModel product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getNeededQuantity() {
        return neededQuantity;
    }

    public void setNeededQuantity(int neededQuantity) {
        this.neededQuantity = neededQuantity;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
