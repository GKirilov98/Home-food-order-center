package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.controller.base.BaseController;
import dp.home_food_order_center.server.data.view.category.CategorySelectView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private final ICategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(ICategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    /**
     * Returns all categories for catalog
     * @return
     * @throws GlobalServiceException
     */
    @GetMapping("/getAllForSelect")
    public ResponseEntity<?> getAllForSelect() throws GlobalServiceException {
       List<CategorySelectView> list = this.categoryService.getAll()
               .stream().map( e ->  this.modelMapper.map(e, CategorySelectView.class))
               .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
}
