package dp.home_food_order_center.data.view.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 9:07 PM
 */
public class ProductView implements Serializable {
    @JsonProperty
    @NotNull
    private Byte[] image;
    @JsonProperty
    @NotNull
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 symbols.")
    private String name;
    @JsonProperty
    @NotNull
    @Size(min = 50, max = 3999, message = "Name must be between 50 and 3999 symbols.")
    private String description;
    @JsonProperty
    @NotNull
    @Min(1)
    private Long availableQuantity;
    @JsonProperty
    @NotNull
    @DecimalMin(value = "0.1")
    @Digits(integer=3, fraction=2)
    private BigDecimal price;

    public ProductView() {
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
