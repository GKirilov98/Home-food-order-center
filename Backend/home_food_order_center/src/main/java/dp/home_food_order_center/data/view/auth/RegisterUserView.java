package dp.home_food_order_center.data.view.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegisterUserView {
    @JsonProperty
    @Size(min = 6, max = 15, message = "Username must be between 6 and 15 symbols.")
    private String username;
    @JsonProperty
    @Size(min = 4, max = 30, message = "First Name must be between 4 and 30 symbols.")
    private String fName;
    @JsonProperty
    @Size(min = 4, max = 30, message = "Last Name must be between 4 and 30 symbols.")
    private String lName;
    @Email
    @JsonProperty
    private String email;
    @JsonProperty
    @Size(min = 10, max = 10, message = "Mobile phone must be 10 symbols")
    private String phoneNumber;
    @JsonProperty
    @Size(min = 6, max = 20, message = "First Name must be between 4 and 20 symbols.")
    private String password;
    @JsonProperty
    @Size(min = 6, max = 20, message = "First Name must be between 4 and 20 symbols.")
    private String confirmPassword;

    public RegisterUserView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
