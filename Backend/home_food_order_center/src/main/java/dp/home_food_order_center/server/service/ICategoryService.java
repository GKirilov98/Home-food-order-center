package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.data.view.category.CategorySelectView;
import dp.home_food_order_center.server.error.GlobalServiceException;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
public interface ICategoryService {
    /**
     * Returns all categories for catalog
     * @return
     * @throws GlobalServiceException
     */
    List<CategorySelectView> getAllForSelect() throws GlobalServiceException;
}
