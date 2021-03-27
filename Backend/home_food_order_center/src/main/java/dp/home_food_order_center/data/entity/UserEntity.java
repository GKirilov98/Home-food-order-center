package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class UserEntity extends BaseEntity {
    private Byte[] image;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private Timestamp dateRegistration;
    private String loggedId;
    private Set<RoleEntity> roles;
    private Set<ReceiptEntity> receipts;
    private Set<PaymentEntity> payments;

    public UserEntity() {
    }

    @Lob
    @Column
    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false)
    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "user")
    public Set<ReceiptEntity> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<ReceiptEntity> receipts) {
        this.receipts = receipts;
    }

    @OneToMany(mappedBy = "user")
    public Set<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentEntity> payments) {
        this.payments = payments;
    }

    @Column
    public String getLoggedId() {
        return loggedId;
    }

    public void setLoggedId(String loggedId) {
        this.loggedId = loggedId;
    }
}
