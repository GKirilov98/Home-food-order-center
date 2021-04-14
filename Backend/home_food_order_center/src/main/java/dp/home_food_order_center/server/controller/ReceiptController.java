package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
import dp.home_food_order_center.server.data.view.receipt.ReceiptConfirmView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IReceiptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/6/2021
 */
@RestController
@RequestMapping("/receipt")
public class ReceiptController extends BaseController {
    @Autowired
    private IReceiptService receiptService;

    /**
     * Get shopping receipt for curr logged user
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping("/currUser/shopping")
    public ResponseEntity<?> getShoppingReceiptForCurrUser() throws GlobalServiceException {
        List<ReceiptModel> list = this.receiptService.getShoppingReceiptForCurrUser();
        return ResponseEntity.ok().body(list);
    }

    /**
     * With confirm receipt, changes status from SHOPPING to SEND
     * @param params
     * @return
     * @throws GlobalServiceException
     */
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmReceipt(@Valid @RequestBody ReceiptConfirmView params) throws GlobalServiceException {
        String message = this.receiptService.confirmReceipt(params.getReceiptId(), params.getCity(), params.getAddress());
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", message);
        }});
    }

    /**
     * getReceiptById
     *
     * @param id - receipt id
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getReceiptById(@PathVariable Long id) throws GlobalServiceException {
        List<ReceiptModel> list = this.receiptService.getReceiptById(id);
        return ResponseEntity.ok().body(list);
    }

//    @GetMapping("/export/{id}")
//    public HttpEntity exportReceiptById(@PathVariable Long id) throws GlobalServiceException {
//        File zipFile = this.receiptService.exportReceiptById(id);
//        return new FileToHttpEntityUtil().convert(zipFile);
//    }

//    /**
//     * getAllReceiptOrByStatus
//     * @param status
//     * @return
//     * @throws GlobalServiceException
//     */
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllReceiptOrByStatus(@RequestParam (required = false) String status)
//            throws GlobalServiceException {
//        List<ReceiptListView> list = this.receiptService.getAllReceiptOrByStatus(status);
//        return ResponseEntity.ok().body(list);
//    }
}
