package dp.home_food_order_center.server.data.view.receipt;

import dp.home_food_order_center.server.data.entity.ReceiptStatusType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/9/2021
 */
public class ReceiptExportView implements Serializable {
    private Long id;
    private BigDecimal totalAmount;
    private Timestamp dateAdded;
    private String userUsername;
    private ReceiptStatusType statusCode;
    private String deliveryAddress;

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

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public ReceiptStatusType getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(ReceiptStatusType statusCode) {
        this.statusCode = statusCode;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
