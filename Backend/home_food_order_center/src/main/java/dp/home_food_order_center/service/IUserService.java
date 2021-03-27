package dp.home_food_order_center.service;

import dp.home_food_order_center.data.model.user.UserModel;
import dp.home_food_order_center.data.view.auth.LoginReturnedUserView;
import dp.home_food_order_center.error.GlobalServiceException;

import java.util.List;

public interface IUserService {
//     void register(String username, String password, String confirmPassword, String email);
     List<LoginReturnedUserView> login(String username, String password) throws GlobalServiceException;

    void insertOne(UserModel userModel) throws GlobalServiceException;

    void logout(LoginReturnedUserView params) throws GlobalServiceException;
}
