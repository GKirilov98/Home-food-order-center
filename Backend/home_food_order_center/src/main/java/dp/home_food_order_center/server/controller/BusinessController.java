package dp.home_food_order_center.server.controller;

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
 * On: 5/20/2021
 */
@RestController
@RequestMapping("/business")
public class BusinessController {
    private final IUserService userService;
    private final IReceiptService receiptService;
    private final IProductService productService;

    public BusinessController(IUserService userService, IReceiptService receiptService, IProductService productService) {
        this.userService = userService;
        this.receiptService = receiptService;
        this.productService = productService;
    }

    /**
     * getAllReceiptWithFilter - Filtering receipts
     *
     * @param status - status of receipts
     * @param username -of ordered receipt
     * @param receiptId - Receipt id
     * @param date - all receipt ordered before this data
     * @param orderBy - order by any of filtered field asc or desc
     * @return - List of filtered receipts
     * @throws GlobalServiceException
     */
    @GetMapping("/receipt/getAll")
    public ResponseEntity<?> getAllReceiptWithFilter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long receiptId,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String orderBy
    ) throws GlobalServiceException {
        List<ReceiptListView> list = this.receiptService.getAllReceiptWithFilter(status, username, receiptId, date, orderBy);
        return ResponseEntity.ok().body(list);
    }

    /**
     *  changeReceiptToPaid - Change receipt status to PAID
     * @param id
     * @return
     */
    @PostMapping("/receipt/paid/{id}")
    public ResponseEntity<?> changeReceiptToPaid(@PathVariable Long id) throws GlobalServiceException {
        List<ReceiptModel> list = this.receiptService.changeReceiptToPaid(id);
        return ResponseEntity.ok().body(list);
    }


    /**
     * getAllUsers - Filtering users
     * @param username
     * @param email
     * @param phoneNumber
     * @param orderBy - order by any of filtered field asc or desc
     * @return - List of filtered users
     * @throws GlobalServiceException
     */
    @GetMapping("/user/getAll")
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String username,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String phoneNumber,
                                         @RequestParam(required = false) String orderBy) throws GlobalServiceException {
        List<UserListModel> list = this.userService.getAllUsers(username, email, phoneNumber, orderBy);
        return ResponseEntity.ok().body(list);
    }

    /**
     * editProductById - Edit user params with this id
     * @param id
     * @param params
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/product/edit/{id}")
    public ResponseEntity<?> editProductById(@PathVariable Long id, @Valid @RequestBody ProductEditView params) throws GlobalServiceException {
        List<ProductDetailsModel> list = this.productService.editProductById(id, params);
        return ResponseEntity.ok().body(list);
    }

    /**
     * insertOne - Insert new row in database
     *
     * @param params - data to add
     * @throws GlobalServiceException
     */
    @PostMapping("/product/create")
    public ResponseEntity<?> insertOne(@Valid @RequestBody CreateProductView params) throws GlobalServiceException {
        String list = productService.insertOne(params);
        return ResponseEntity.ok().body(list);
    }

    /**
     * makeBusinessByUserId - Gives on user with that id role "BUSINESS"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/makeBusiness/{id}")
    public ResponseEntity<?> makeBusinessByUserId(@PathVariable Long id) throws GlobalServiceException {
        String message = this.userService.makeBusinessByUserId(id);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", message);
        }});
    }

    /**
     * removeBusinessByUserId - Remove from user with that id role "BUSINESS"
     * @param id - id of user
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/removeBusiness/{id}")
    public ResponseEntity<?> removeBusinessByUserId(@PathVariable Long id) throws GlobalServiceException {
        String message = this.userService.removeBusinessByUserId(id);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", message);
        }});
    }
}
