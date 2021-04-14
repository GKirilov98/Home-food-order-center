package dp.home_food_order_center.server.data.view.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public class OrderCreateView {
//    @JsonProperty("userId")
//    @NotNull
//    private Long userId;
    @JsonProperty("productId")
    @NotNull
    private Long productId;
    @JsonProperty("quantity")
    @NotNull
    private int quantity;

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
