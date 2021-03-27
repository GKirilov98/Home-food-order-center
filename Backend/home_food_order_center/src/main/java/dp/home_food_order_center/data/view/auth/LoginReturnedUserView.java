package dp.home_food_order_center.data.view.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:35 PM
 */
public class LoginReturnedUserView {
    @JsonProperty
    @NotNull
    private Long id;
    @JsonProperty
    @NotNull
    private String username;
    @JsonProperty
    @NotNull
    private String token;

    public LoginReturnedUserView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
