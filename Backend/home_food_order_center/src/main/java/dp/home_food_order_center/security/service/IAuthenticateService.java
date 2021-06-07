package dp.home_food_order_center.security.service;

import dp.home_food_order_center.security.model.AuthenticateResponseModel;
import dp.home_food_order_center.server.data.model.user.UserAuthRegistrationModel;
import dp.home_food_order_center.server.error.GlobalServiceException;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/15/2021
 */
public interface IAuthenticateService {
    /**
     * Log user, returns jwt token
     * @param username
     * @param password
     * @return
     */
    AuthenticateResponseModel loginUser(String username, String password) throws GlobalServiceException;

    /**
     * Register user, with roles
     * @param model
     * @return
     * @throws GlobalServiceException
     */
    String registerUser(UserAuthRegistrationModel model) throws GlobalServiceException;

    /**
     * Adding jwt in black list
     * @param token
     * @return
     */
    String logoutUser(String token);
}
