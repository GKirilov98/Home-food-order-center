package dp.home_food_order_center.server.data.view.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserView {
    private String imageUrl;
    private String imagePublicId;
    @JsonProperty("username")
    @Size(min = 6, max = 15, message = "Username must be between 6 and 15 symbols.")
    private String username;
    @JsonProperty("firstName")
    @NotNull
    @Size(min = 4, max = 30, message = "First Name must be between 4 and 30 symbols.")
    private String firstName;
    @JsonProperty("lastName")
    @NotNull
    @Size(min = 4, max = 30, message = "Last Name must be between 4 and 30 symbols.")
    private String lastName;
    @NotNull
    @Email
    @JsonProperty ("email")
    private String email;
    @JsonProperty("phoneNumber")
    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be 10 symbols")
    private String phoneNumber;
    @JsonProperty("password")
    @Size(min = 6, max = 20, message = "First Name must be between 4 and 20 symbols.")
    private String password;
    @JsonProperty("confirmPassword")
    @Size(min = 6, max = 20, message = "First Name must be between 4 and 20 symbols.")
    private String confirmPassword;

    public RegisterUserView() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
