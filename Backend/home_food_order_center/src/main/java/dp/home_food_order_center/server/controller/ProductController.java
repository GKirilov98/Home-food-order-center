package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
import dp.home_food_order_center.server.data.view.product.ProductListView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 8:51 PM
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    private final IProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(IProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

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
        List<ProductListView> list = this.productService
                .getAllByCategoryIdOrSubcategoryId(categoryId, subcategoryId)
                .stream().map(e -> modelMapper.map(e, ProductListView.class))
                .sorted(Comparator.comparing(ProductListView::getName))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    /**
     * Returns details of product by id
     * @param productId
     * @return
     */
    @GetMapping("/details/{productId}")
    public ResponseEntity<?> getOneById (@PathVariable Long productId) throws GlobalServiceException {
        List<ProductDetailsModel> list = this.productService.getOneById(productId);
        return ResponseEntity.ok().body(list);
    }
}
