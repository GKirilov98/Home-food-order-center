package dp.home_food_order_center.server.data.view.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import dp.home_food_order_center.server.data.model.order.OrderModel;
import dp.home_food_order_center.server.data.model.user.UserModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/7/2021
 */
public class ReceiptListView implements Serializable {
    @JsonProperty("id")
    private Long id;
//    private String deliveryAddress;
    @JsonProperty("statusCode")
    private String statusCode;
    @JsonProperty("username")
    private String userUsername;
    @JsonProperty("dateAdded")
    private Timestamp dateAdded;
    @JsonProperty("amount")
    private BigDecimal totalAmount;
//    private Set<OrderModel> orders;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
