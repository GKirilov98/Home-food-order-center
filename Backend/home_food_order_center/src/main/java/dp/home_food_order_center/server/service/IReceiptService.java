package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
import dp.home_food_order_center.server.data.view.receipt.ReceiptListView;
import dp.home_food_order_center.server.error.GlobalServiceException;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public interface IReceiptService {
    /**
     *  Return not paid receipt by user id
     * @param userId
     * @return
     * @throws GlobalServiceException
     */
    ReceiptModel getNotPaidReceiptByUserId(Long userId) throws GlobalServiceException;

    /**
     *  Get all receipts for curr logged user
     * @return
     * @throws GlobalServiceException
     */
    List<ReceiptModel> getShoppingReceiptForCurrUser() throws GlobalServiceException;

    /**
     * With confirm receipt, changes status from SHOPPING to SEND
     * @param receiptId
     * @param city
     * @param address
     * @return
     * @throws GlobalServiceException
     */
    String confirmReceipt(Long receiptId, String city, String address) throws GlobalServiceException;

    /**
     * getReceiptById
     * @param id - receipt id
     * @return
     * @throws GlobalServiceException
     */
    List<ReceiptModel> getReceiptById(Long id) throws GlobalServiceException;

    //ADMIN
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
    List<ReceiptListView> getAllReceiptWithFilter(String status, String username, Long receiptId, String date, String orderBy) throws GlobalServiceException;

    /**
     *  changeReceiptToPaid - Change receipt status to PAID
     * @param id
     * @return
     */
    List<ReceiptModel> changeReceiptToPaid(Long id) throws GlobalServiceException;


    /**
     * Delete receipt
     * @param id
     * @return
     */
    String deleteReceiptById(Long id) throws GlobalServiceException;
}
