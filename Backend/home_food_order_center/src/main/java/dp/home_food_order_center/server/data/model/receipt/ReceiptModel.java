package dp.home_food_order_center.server.data.model.receipt;

import dp.home_food_order_center.server.data.model.order.OrderModel;
import dp.home_food_order_center.server.data.model.user.UserModel;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public class ReceiptModel implements Serializable{
    private Long id;
    private String deliveryAddress;
    private String statusCode;
    private UserModel user;
    private Timestamp dateAdded;
    private BigDecimal totalAmount;
    private Set<OrderModel> orders;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderModel> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptModel that = (ReceiptModel) o;
        return Objects.equals(id, that.id) && Objects.equals(deliveryAddress, that.deliveryAddress) && Objects.equals(statusCode, that.statusCode) && Objects.equals(user, that.user) && Objects.equals(dateAdded, that.dateAdded) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryAddress, statusCode, user, dateAdded, totalAmount, orders);
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "id=" + id +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", user=" + user +
                ", dateAdded=" + dateAdded +
                ", totalAmount=" + totalAmount +
                ", orders=" + orders +
                '}';
    }

    //    @Override
//    public int compareTo(ReceiptModel o) {
//        return new CompareToBuilder()
//                .append(this.id, o.id)
//                .append(this.deliveryAddress, o.deliveryAddress)
//                .append(this.statusCode, o.statusCode)
//                .append(this.dateAdded, o.dateAdded)
//                .append(this.totalAmount, o.totalAmount)
//                .append(this.orders, o.orders)
//                .append(this.user, o.user)
//                .toComparison();
//    }
}
