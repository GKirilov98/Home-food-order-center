package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
import dp.home_food_order_center.server.data.model.user.UserListModel;
import dp.home_food_order_center.server.data.view.product.CreateProductView;
import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
import dp.home_food_order_center.server.data.view.product.ProductEditView;
import dp.home_food_order_center.server.data.view.receipt.ReceiptListView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IProductService;
import dp.home_food_order_center.server.service.IReceiptService;
import dp.home_food_order_center.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/9/2021
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private final IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }


    /**
     * makeAdminByUserId - Gives on user with that id role "ADMIN"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/makeAdmin/{id}")
    public ResponseEntity<?> makeAdminByUserId(@PathVariable Long id) throws GlobalServiceException {
        String message = this.userService.makeAdminByUserId(id);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", message);
        }});
    }

    /**
     * removeAdminByUserId - Remove from user with that id role "ADMIN"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/removeAdmin/{id}")
    public ResponseEntity<?> removeAdminByUserId(@PathVariable Long id) throws GlobalServiceException {
        String message = this.userService.removeAdminByUserId(id);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", message);
        }});
    }

    /**
     * deleteUserById - delete users with this name
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/user/delete/{id}")
    public  ResponseEntity<?> deleteUserById(@PathVariable Long id) throws GlobalServiceException {
        String message = this.userService.deleteUserById(id);
        return ResponseEntity.ok().body(new HashMap<String, String>(){{put("message", message);}} );
    }

}
