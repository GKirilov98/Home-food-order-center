package dp.home_food_order_center.server.data.view.order;

import dp.home_food_order_center.server.data.entity.ProductEntity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/9/2021
 */
public class OrderExportView implements Serializable {
    private Long id;
    private String productName;
    private Integer neededQuantity;
    private BigDecimal productUnitPrice;
    private BigDecimal amount;
    private Timestamp orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNeededQuantity() {
        return neededQuantity;
    }

    public void setNeededQuantity(Integer neededQuantity) {
        this.neededQuantity = neededQuantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(BigDecimal productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }
}
