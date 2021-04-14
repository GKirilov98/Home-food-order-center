package dp.home_food_order_center.server.data.model.user;

import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
public class UserAuthRegistrationModel {
    private String logId;
    private String imageUrl;
    private String imagePublicIdentity;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    private Set<String> role;

    public UserAuthRegistrationModel() {
    }

    public UserAuthRegistrationModel(String idetntityLoger, String imageUrl, String imagePublicIdentity, String username, String fName, String lName, String email, String phoneNumber, String password, Set<String> role) {
        this.logId = idetntityLoger;
        this.imageUrl = imageUrl;
        this.imagePublicIdentity = imagePublicIdentity;
        this.username = username;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePublicIdentity() {
        return imagePublicIdentity;
    }

    public void setImagePublicIdentity(String imagePublicIdentity) {
        this.imagePublicIdentity = imagePublicIdentity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
