package dp.home_food_order_center.server.data.view.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/2/2021
 */

public class ProductListView implements Serializable {
    @JsonProperty("id")
    @NotNull
    private Long id;
    @JsonProperty("imageUrl")
    @NotNull
    private String imageUrl;
    @JsonProperty("imagePublicId")
    @NotNull
    private String imagePublicId;
    @JsonProperty("name")
    @NotNull
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 symbols.")
    private String name;
    @JsonProperty("price")
    @NotNull
    @DecimalMin(value = "0.1")
    @Digits(integer=3, fraction=2)
    private BigDecimal price;
    @JsonProperty("isSold")
    private boolean isSold;
    @JsonProperty("isSale")
    private boolean isSale;
    @JsonProperty("isNew")
    private boolean isNew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
