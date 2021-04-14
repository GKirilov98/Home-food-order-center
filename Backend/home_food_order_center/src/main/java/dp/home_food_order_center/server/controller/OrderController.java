package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.view.order.OrderCreateView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    /**
     * insertOne - Insert new record in the table
     * @param params - data to add
     * @throws GlobalServiceException
     * @return
     */
    @PostMapping(path="/add")
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderCreateView params) throws GlobalServiceException {
        String message = orderService.insertOne(params.getProductId(), params.getQuantity());
        String finalMessage = message;
        return ResponseEntity.ok().body(new HashMap<String, String>(){{put("message", finalMessage);}} );
    }

    /**
     * deleteOrder - Delete order
     * @param id
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) throws GlobalServiceException {
        String message = this.orderService.deleteOneById(id);
        String finalMessage = message;
        return ResponseEntity.ok().body(new HashMap<String, String>(){{put("message", finalMessage);}} );
    }
}
