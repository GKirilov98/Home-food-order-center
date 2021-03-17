package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "payments")
public class PaymentEntity extends BaseEntity {
    // TODO: 3/17/2021 Ако е дебитна карта какво се прави?
    private ReceiptEntity receipt;
    private UserEntity user;
    private TypePaymentEntity typePayment;
//    private BigDecimal receiptAmount;
    private BigDecimal websiteAmount;
    private BigDecimal restaurantAmount;

    public PaymentEntity() {
    }

    @OneToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    public ReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptEntity receipt) {
        this.receipt = receipt;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    public TypePaymentEntity getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePaymentEntity typePayment) {
        this.typePayment = typePayment;
    }

//    public BigDecimal getReceiptAmount() {
//        return receiptAmount;
//    }
//
//    public void setReceiptAmount(BigDecimal receiptAmount) {
//        this.receiptAmount = receiptAmount;
//    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getWebsiteAmount() {
        return websiteAmount;
    }

    public void setWebsiteAmount(BigDecimal websiteAmount) {
        this.websiteAmount = websiteAmount;
    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getRestaurantAmount() {
        return restaurantAmount;
    }

    public void setRestaurantAmount(BigDecimal restaurantAmount) {
        this.restaurantAmount = restaurantAmount;
    }
}
