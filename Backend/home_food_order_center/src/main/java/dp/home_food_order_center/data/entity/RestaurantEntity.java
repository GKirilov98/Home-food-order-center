package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "restaurants")
public class RestaurantEntity extends BaseEntity {
    private Byte[] image;
    private String restaurantName;
    // TODO: 3/16/2021 Трябва ли да има работно време? 
    private String address;
    private String contactPhone;
    private String contactEmail;
    private Timestamp dateRegistration;
    private boolean isAccepted;
    private CompanyEntity company;
    private Set<ProductEntity> products;

    @Lob
    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Column(nullable = false)
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Column(nullable = false)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Column(nullable = false)
    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Column
    public boolean isAccepted() {
        return isAccepted;
    }
    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false, referencedColumnName = "id")
    public CompanyEntity getCompany() {
        return company;
    }
    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "restaurant")
    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
