package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "companies")
public class CompanyEntity extends BaseEntity {
    private Byte[] image;
    private String name;
    private String description;
    private String ownerFirstName;
    private String ownerLastName;
    private Date dateFoundation;
    private Timestamp dateRegistration;
    private Set<RestaurantEntity> restaurants;

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

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    @Column(nullable = false)
    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    @Column(nullable = false)
    public Date getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(Date dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    @Column(nullable = false)
    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @OneToMany(mappedBy="company")
    public Set<RestaurantEntity> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<RestaurantEntity> restaurants) {
        this.restaurants = restaurants;
    }
}
