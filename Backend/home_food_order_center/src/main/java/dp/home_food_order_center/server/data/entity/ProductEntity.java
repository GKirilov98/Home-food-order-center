package dp.home_food_order_center.server.data.entity;

import dp.home_food_order_center.server.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "products")
public class ProductEntity extends BaseEntity {
    private String imageUrl;
    private String imagePublicId;
    private String name;
    private String description;
    private Integer availableQuantity;
    private BigDecimal price;
    private Set<OrderEntity> orders;
    private SubcategoryEntity subcategory;
    private Timestamp dateLastEdit;
//    private RestaurantEntity restaurant;

    public ProductEntity() {
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(nullable = false)
    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 3999)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    @Column(nullable = false)
    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
//    @ManyToOne
//    @JoinColumn(name="restaurant_id", nullable=false, referencedColumnName = "id")
//    public RestaurantEntity getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(RestaurantEntity restaurant) {
//        this.restaurant = restaurant;
//    }

    @OneToMany(mappedBy = "product" )
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    @ManyToOne
    @JoinColumn(name="subcategory_id", nullable=false, referencedColumnName = "id")
    public SubcategoryEntity getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEntity subcategory) {
        this.subcategory = subcategory;
    }

    public Timestamp getDateLastEdit() {
        return dateLastEdit;
    }

    public void setDateLastEdit(Timestamp dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }
}
