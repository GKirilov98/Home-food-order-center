package dp.home_food_order_center.server.data.model.user;

import dp.home_food_order_center.server.data.view.receipt.ReceiptListView;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/6/2021
 */
public class UserModel implements Serializable {
    private Long id;
    private String imageUrl;
    private String imagePublicId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Timestamp dateRegistration;
    private Set<ReceiptListView> receipts;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Set<ReceiptListView> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<ReceiptListView> receipts) {
        this.receipts = receipts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(imageUrl, userModel.imageUrl) && Objects.equals(imagePublicId, userModel.imagePublicId) && Objects.equals(username, userModel.username) && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(email, userModel.email) && Objects.equals(phoneNumber, userModel.phoneNumber) && Objects.equals(address, userModel.address) && Objects.equals(dateRegistration, userModel.dateRegistration) && Objects.equals(receipts, userModel.receipts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, imagePublicId, username, firstName, lastName, email, phoneNumber, address, dateRegistration, receipts);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateRegistration=" + dateRegistration +
                ", receipts=" + receipts +
                '}';
    }
}
