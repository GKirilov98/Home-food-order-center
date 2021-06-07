package dp.home_food_order_center.server.data.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import dp.home_food_order_center.server.data.model.base.BaseModel;
import dp.home_food_order_center.server.data.model.category.CategoryModel;
import dp.home_food_order_center.server.data.model.subcategory.SubcategoryModel;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:54 PM
 */
public class ProductModel extends BaseModel {
    private String imageUrl;
    private String imagePublicId;
    private String name;
    private String description;
    private Long availableQuantity;
    private BigDecimal price;
    private CategoryModel category;
    private SubcategoryModel subcategory;
//    private Long categoryId;
//    private String categoryName;
//    private Long subcategoryId;
//    private String subcategoryName;

    public ProductModel() {
    }

//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Long getSubcategoryId() {
//        return subcategoryId;
//    }
//
//    public void setSubcategoryId(Long subcategoryId) {
//        this.subcategoryId = subcategoryId;
//    }

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

//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public String getSubcategoryName() {
//        return subcategoryName;
//    }
//
//    public void setSubcategoryName(String subcategoryName) {
//        this.subcategoryName = subcategoryName;
//    }
}
