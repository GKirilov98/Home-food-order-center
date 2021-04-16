package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.data.view.product.CreateProductView;
import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
import dp.home_food_order_center.server.data.view.product.ProductEditView;
import dp.home_food_order_center.server.data.view.product.ProductListView;
import dp.home_food_order_center.server.error.GlobalServiceException;

import java.util.List;


/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 9:05 PM
 */
public interface IProductService {
    /**
     * Get all product for catalog filtered by category, subcategory or both
     * @param categoryId
     * @param subcategoryId
     * @return
     * @throws GlobalServiceException
     */
    List<ProductListView> getAllByCategoryIdOrSubcategoryId(Long categoryId, Long subcategoryId) throws GlobalServiceException;

    /**
     * Returns details of product by id
     * @param productId
     * @return
     */
    List<ProductDetailsModel> getOneById(Long productId) throws GlobalServiceException;

    //ADMIN>>
    /**
     * editProductById - Edit user params with this id
     * @param id
     * @param params
     * @return
     * @throws GlobalServiceException
     */
    List<ProductDetailsModel> editProductById(Long id, ProductEditView params) throws GlobalServiceException;

    /**
     * insertOne - Insert new row in database
     *
     * @param model - data to add
     * @throws GlobalServiceException
     */
    String insertOne(CreateProductView model) throws GlobalServiceException;
}
