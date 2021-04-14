package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.model.user.UserModel;
import dp.home_food_order_center.server.data.view.user.UserEditView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/7/2021
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     *  Get user by id
     * @param id
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable Long id) throws GlobalServiceException {
        List<UserModel> list = this.userService.getUserById(id);
        return ResponseEntity.ok().body(list);
    }

    /**
     * Edit user by id
     * @param id
     * @param params
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editUserById(@PathVariable Long id, @Valid @RequestBody UserEditView params) throws GlobalServiceException {
        List<UserModel> list = this.userService.editUser(id, params);
        return ResponseEntity.ok().body(list);
    }
}
