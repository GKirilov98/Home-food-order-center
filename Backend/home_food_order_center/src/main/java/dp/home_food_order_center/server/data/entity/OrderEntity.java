package dp.home_food_order_center.server.data.entity;

import dp.home_food_order_center.server.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "orders")
public class OrderEntity extends BaseEntity {
    private ProductEntity product;
    private Integer neededQuantity;
    private BigDecimal amount;
    private ReceiptEntity receipt;
    private Timestamp orderDate;

    public OrderEntity() {
    }

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false, referencedColumnName = "id")
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getNeededQuantity() {
        return neededQuantity;
    }

    public void setNeededQuantity(Integer neededQuantity) {
        this.neededQuantity = neededQuantity;
    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name="receipt_id", nullable=false, referencedColumnName = "id")
    public ReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptEntity receipt) {
        this.receipt = receipt;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
