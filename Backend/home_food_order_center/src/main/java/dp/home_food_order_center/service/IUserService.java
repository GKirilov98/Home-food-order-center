package dp.home_food_order_center.service;

public interface IUserService {
     void register(String username, String password, String confirmPassword, String email);
     void login(String username, String password);
}
