package dp.home_food_order_center.controller;

import dp.home_food_order_center.data.model.user.UserModel;
import dp.home_food_order_center.data.view.auth.LoginReturnedUserView;
import dp.home_food_order_center.data.view.auth.LoginUserView;
import dp.home_food_order_center.data.view.auth.RegisterUserView;
import dp.home_food_order_center.error.GlobalServiceException;
import dp.home_food_order_center.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * insertOne - Insert new record in the table
     * @param params - data to add
     * @throws GlobalServiceException
     */
    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@Valid @RequestBody RegisterUserView params) throws GlobalServiceException {
        UserModel userModel = this.modelMapper.map(params, UserModel.class);
        userService.insertOne(userModel);
    }

    /**
     *  Login valid user
     * @param params - username and password
     * @return - id and username
     * @throws GlobalServiceException
     */
    @GetMapping("/login")
    public ResponseEntity<List<LoginReturnedUserView>> login(@RequestBody LoginUserView params) throws GlobalServiceException {
        List<LoginReturnedUserView> login = userService.login(params.getUsername(), params.getPassword());
        return ResponseEntity.ok().body(login);
    }


    /**
     * insertOne - Insert new record in the table
     * @param params - data to add
     * @throws GlobalServiceException
     */
    @PostMapping(path="/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@Valid @RequestBody LoginReturnedUserView params) throws GlobalServiceException {
        userService.logout(params);
    }

}
