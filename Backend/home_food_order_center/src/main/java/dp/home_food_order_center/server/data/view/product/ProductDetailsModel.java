package dp.home_food_order_center.server.data.view.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public class ProductDetailsModel implements Serializable {
    @JsonProperty("id")
    @NonNull
    private Long id;
    @JsonProperty("name")
    @NonNull
    private String name;
    @JsonProperty("description")
    @NonNull
    private String description;
    @JsonProperty("price")
    @NonNull
    private BigDecimal price;
    @JsonProperty("maxQuantity")
    @NonNull
    private Integer availableQuantity;
    @JsonProperty("imagePublicId")
    @NonNull
    private String imagePublicId;
    @JsonProperty("imageUrl")
    @NonNull
    private String imageUrl;
    @JsonProperty("categoryId")
    @NonNull
    private Long categoryId;
    @JsonProperty("category")
    @NonNull
    private String category;
    @JsonProperty("subcategoryId")
    @NonNull
    private Long subcategoryId;
    @JsonProperty("subcategory")
    @NonNull
    private String subcategory;

    public ProductDetailsModel() {
    }


    @NonNull
    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(@NonNull String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    @NonNull
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Long categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(@NonNull Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NonNull BigDecimal price) {
        this.price = price;
    }

    @NonNull
    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(@NonNull Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(@NonNull String subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailsModel model = (ProductDetailsModel) o;
        return id.equals(model.id) && name.equals(model.name) &&
                description.equals(model.description) && price.equals(model.price) &&
                availableQuantity.equals(model.availableQuantity) &&
                imagePublicId.equals(model.imagePublicId) && imageUrl.equals(model.imageUrl) &&
                categoryId.equals(model.categoryId) && category.equals(model.category) &&
                subcategoryId.equals(model.subcategoryId) && subcategory.equals(model.subcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, availableQuantity,
                imagePublicId, imageUrl, categoryId, category, subcategoryId, subcategory);
    }

    @Override
    public String toString() {
        return "ProductDetailsModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", subcategoryId=" + subcategoryId +
                ", subcategory='" + subcategory + '\'' +
                '}';
    }
}
