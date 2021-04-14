package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.view.product.ProductDetailsView;
import dp.home_food_order_center.server.data.view.product.ProductListView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:51 PM
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all product for catalog filtered by category, subcategory or both
     * @param categoryId
     * @param subcategoryId
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllByCategoryIdOrSubcategoryIdOrBoth(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subcategoryId) throws GlobalServiceException {
        List<ProductListView> list = this.productService.getAllByCategoryIdOrSubcategoryId(categoryId, subcategoryId);
        return ResponseEntity.ok().body(list);
    }

    /**
     * Returns details of product by id
     * @param productId
     * @return
     */
    @GetMapping("/details/{productId}")
    public ResponseEntity<?> getOneById (@PathVariable Long productId) throws GlobalServiceException {
        List<ProductDetailsView> list = this.productService.getOneById(productId);
        return ResponseEntity.ok().body(list);
    }
}
