package dp.home_food_order_center.controller;

import dp.home_food_order_center.data.model.product.ProductModel;
import dp.home_food_order_center.data.view.product.CreateProductView;
import dp.home_food_order_center.data.view.product.ProductView;
import dp.home_food_order_center.error.GlobalServiceException;
import dp.home_food_order_center.service.IProductService;
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
@RequestMapping ("/product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * insertOne - Insert new record in the table
     * @param params - data to add
     * @throws GlobalServiceException
     */
    @PostMapping("/create")
    public ResponseEntity<?> insertOne(@RequestBody CreateProductView params) {
        ProductModel model = this.modelMapper.map(params, ProductModel.class);
       List<ProductView> list = productService.insertOne(model);
       return ResponseEntity.ok().body(list);
    }

    @GetMapping("/sayHi")
    public ResponseEntity<?> sayHi (){
        System.out.println("HI");
        return ResponseEntity.ok().body("Hi");
    }
}
