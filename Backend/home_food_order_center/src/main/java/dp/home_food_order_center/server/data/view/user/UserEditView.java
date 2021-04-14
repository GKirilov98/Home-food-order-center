package dp.home_food_order_center.server.data.view.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/8/2021
 */
public class UserEditView implements Serializable {
    @JsonProperty("imgSrc")
    @NotNull
    private String imageUrl;
    @JsonProperty("imgPublicId")
    @NotNull
    private String imagePublicId;
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
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be 10 symbols")
    private String phoneNumber;

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
}
