package dp.home_food_order_center.server.data.view.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/6/2021
 */
public class ReceiptConfirmView implements Serializable {
    @JsonProperty("city")
    @NotNull
    private String city;
    @JsonProperty("address")
    @NotNull
    private String address;
    @JsonProperty("receiptId")
    @NotNull
    private Long receiptId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }
}
