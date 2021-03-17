package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity(name = "products")
public class ProductEntity extends BaseEntity {
    private Byte[] image;
    private String name;
    private String description;
    private Long availableQuantity;
    private BigDecimal price;
//    private Date bestBefore;
    private Set<OrderEntity> orders;
    private RestaurantEntity restaurant;

    public ProductEntity() {
    }

    @Lob
    @Column(nullable = false)
    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Column(nullable = false, precision = 5, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false, referencedColumnName = "id")
    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

//
//    public Date getBestBefore() {
//        return bestBefore;
//    }
//
//    public void setBestBefore(Date bestBefore) {
//        this.bestBefore = bestBefore;
//    }

    @OneToMany(mappedBy = "product")
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
