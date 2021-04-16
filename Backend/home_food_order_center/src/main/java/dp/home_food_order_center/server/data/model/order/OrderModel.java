package dp.home_food_order_center.server.data.model.order;

import dp.home_food_order_center.server.data.model.product.ProductModel;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return neededQuantity == that.neededQuantity && Objects.equals(id, that.id) && Objects.equals(orderDate, that.orderDate) && Objects.equals(amount, that.amount) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, amount, neededQuantity, product);
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", amount=" + amount +
                ", neededQuantity=" + neededQuantity +
                ", product=" + product +
                '}';
    }

    //    @Override
//    public int compareTo(OrderModel o) {
//        private Long id;
//        private Timestamp orderDate;
//        private BigDecimal amount;
//        private int neededQuantity;
//        private ProductModel product;
//        return new CompareToBuilder()
//                .a
//    }
}
