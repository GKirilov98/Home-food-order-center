package dp.home_food_order_center.server.data.entity;

import dp.home_food_order_center.server.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "receipts")
public class ReceiptEntity extends BaseEntity {
    private Set<OrderEntity> orders;
    private BigDecimal totalAmount;
    private Timestamp dateAdded;
    private UserEntity user;
    private ReceiptStatusType statusCode;
//    private boolean isPaid;
    private String deliveryAddress;
//    private PaymentEntity payment;

    public ReceiptEntity() {
    }

    @OneToMany(mappedBy = "receipt")
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(nullable = false)
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    @Column
//    public boolean isPaid() {
//        return isPaid;
//    }
//
//    public void setPaid(boolean paid) {
//        isPaid = paid;
//    }


    @Enumerated(EnumType.STRING)
    public ReceiptStatusType getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(ReceiptStatusType statusCode) {
        this.statusCode = statusCode;
    }

    @Column
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

//    @OneToOne(mappedBy = "receipt")
//    public PaymentEntity getPayment() {
//        return payment;
//    }
//
//    public void setPayment(PaymentEntity payment) {
//        this.payment = payment;
//    }
}
