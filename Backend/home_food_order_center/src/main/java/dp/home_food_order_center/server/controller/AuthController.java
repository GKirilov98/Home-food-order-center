package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.security.model.AuthenticateResponseModel;
import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.view.auth.LoginUserView;
import dp.home_food_order_center.server.data.view.auth.RegisterUserView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * insertOne - Insert new record in the table
     *
     * @param params - data to add
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserView params) throws GlobalServiceException {
        String message = userService.insertOne(params);
        String finalMessage = message;
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", finalMessage);
        }});
    }

    /**
     * Login valid user
     *
     * @param params - username and password
     * @return - id and username
     * @throws GlobalServiceException
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserView params) throws GlobalServiceException {
        List<AuthenticateResponseModel> login = userService.login(params.getUsername(), params.getPassword());
        return ResponseEntity.ok().body(login);
    }


    /**
     *  Logouts curr user
     * @param request - check header Authorization
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping(path = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws GlobalServiceException {
        String message = null;
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null) {
            String token = headerAuth.substring(7, headerAuth.length());
            message = userService.logout(token);
        } else {
            message = "You have already logout!";
        }

        String finalMessage = message;
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", finalMessage);
        }});
    }

}
