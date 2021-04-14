package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.error.GlobalServiceException;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
public interface IOrderService {
    /**
     * insertOne - Insert new record in the table
     * @param productId
     * @param quantity
     * @return
     * @throws GlobalServiceException
     */
    String insertOne(Long productId, int quantity) throws GlobalServiceException;

    /**
     * Delete order by id
     * @param id
     * @return
     * @throws GlobalServiceException
     */
    String deleteOneById(Long id) throws GlobalServiceException;
}
