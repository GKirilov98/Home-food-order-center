package dp.home_food_order_center.server.data.view.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:53 PM
 */
public class CreateProductView implements Serializable {
    @JsonProperty("category")
    @NotNull
    private Long categoryId;
    @JsonProperty("subcategory")
    @NotNull
    private Long subcategoryId;
    @JsonProperty("imgUrl")
    @NotNull
    private String imageUrl;
    @JsonProperty("imgPublicId")
    @NotNull
    private String imagePublicId;
    @JsonProperty("name")
    @NotNull
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 symbols.")
    private String name;
    @JsonProperty("description")
    @NotNull
    @Size(min = 50, max = 3999, message = "Name must be between 50 and 3999 symbols.")
    private String description;
    @JsonProperty("availableQuantity")
    @NotNull
    @Min(1)
    private Long availableQuantity;
    @JsonProperty("unitPrice")
    @NotNull
    @DecimalMin(value = "0.1")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;


    public CreateProductView() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
