package dp.home_food_order_center.service;

import dp.home_food_order_center.data.model.product.ProductModel;
import dp.home_food_order_center.data.view.product.ProductView;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 9:05 PM
 */
public interface IProductService {
    List<ProductView> insertOne(ProductModel model);
}
