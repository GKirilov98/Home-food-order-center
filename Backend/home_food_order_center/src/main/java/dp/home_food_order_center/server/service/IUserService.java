package dp.home_food_order_center.server.service;

import dp.home_food_order_center.security.model.AuthenticateResponseModel;
import dp.home_food_order_center.server.data.model.user.UserListModel;
import dp.home_food_order_center.server.data.model.user.UserModel;
import dp.home_food_order_center.server.data.model.user.UserRegisterModel;
import dp.home_food_order_center.server.data.view.auth.RegisterUserView;
import dp.home_food_order_center.server.data.view.user.UserEditView;
import dp.home_food_order_center.server.error.GlobalServiceException;

import java.util.List;

public interface IUserService {
    /**
     * Login valid user
     * @param username
     * @param password
     * @return
     * @throws GlobalServiceException
     */
     List<AuthenticateResponseModel> login(String username, String password) throws GlobalServiceException;

    /**
     *  Logouts users by token
     * @param token
     * @return
     */
    String logout(String token);

    /**
     * insertOne - Insert new record in the table
     *
     * @param userModel - data to add
     * @return
     * @throws GlobalServiceException
     */
    String insertOne(RegisterUserView userModel) throws GlobalServiceException;

    /**
     * getUserById
     * @param username
     * @return
     * @throws GlobalServiceException
     */
    List<UserModel> getUserByUsername(String username) throws GlobalServiceException;

    /**
     *  editUser by id
     * @param username
     * @param params
     * @return
     * @throws GlobalServiceException
     */
    List<UserModel> editUser(String username, UserEditView params) throws GlobalServiceException;


    //ADMINS>>>>
    /**
     * makeAdminByUserId - Gives on user with that id role "ADMIN"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    String makeAdminByUserId(Long id) throws GlobalServiceException;

    /**
     * removeAdminByUserId - Remove from user with that id role "ADMIN"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    String removeAdminByUserId(Long id) throws GlobalServiceException;

    /**
     * getAllUsers - Filtering users
     * @param username
     * @param email
     * @param phoneNumber
     * @param orderBy - order by any of filtered field asc or desc
     * @return - List of filtered users
     * @throws GlobalServiceException
     */
    List<UserListModel> getAllUsers(String username, String email, String phoneNumber, String orderBy) throws GlobalServiceException;

    /**
     * deleteUserById - delete users with this name
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    String deleteUserById(Long id) throws GlobalServiceException;
}
