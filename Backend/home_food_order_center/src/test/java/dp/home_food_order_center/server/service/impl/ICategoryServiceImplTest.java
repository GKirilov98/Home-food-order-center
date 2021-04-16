package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.server.data.entity.CategoryEntity;
import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
import dp.home_food_order_center.server.data.view.category.CategorySelectView;
import dp.home_food_order_center.server.data.view.subcategory.SubcategorySelectView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.ICategoryRepository;
import dp.home_food_order_center.server.service.ICategoryService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/14/2021
 */
@ExtendWith(MockitoExtension.class)
class ICategoryServiceImplTest {
    private ICategoryServiceImpl categoryService;

    @Mock
    ICategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        categoryService = new ICategoryServiceImpl(categoryRepository, new ModelMapper());
    }

    @Test
    @DisplayName("Get valid data for Category catalog")
    void getAllForSelect() {

        List<CategorySelectView> expected = new ArrayList<>();
        List<CategoryEntity> entities = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            CategoryEntity category = new CategoryEntity();
            category.setId(i);
            category.setName("Category_" + i);
            Set<SubcategoryEntity> subcategories = new HashSet<>();

            CategorySelectView selectView = new CategorySelectView();
            selectView.setId(i);
            selectView.setName("Category_" + i);
            SubcategorySelectView[] subcategorySelectViews = new SubcategorySelectView[5];
            for (long k = 0; k < 5; k++) {
                SubcategoryEntity subcategory = new SubcategoryEntity();
                subcategory.setCategory(category);
                subcategory.setName("Subcategory_" + k);
                subcategory.setId(k);
                subcategories.add(subcategory);

                SubcategorySelectView subcategorySelect = new SubcategorySelectView();
                subcategorySelect.setId(k);
                subcategorySelect.setName("Subcategory_" + k);
                subcategorySelectViews[(int) k] = subcategorySelect;
            }
            category.setSubcategories(subcategories);
            selectView.setSubcategories(subcategorySelectViews);

            entities.add(category);
            expected.add(selectView);
        }

        Mockito.when(categoryRepository.findAll())
                .thenReturn(entities);
        try {
            List<CategorySelectView> obj = categoryService.getAllForSelect();
            obj.forEach(a -> a.setSubcategories(Arrays.stream(a.getSubcategories()).sorted().toArray(SubcategorySelectView[]::new)));
            assertEquals(expected, obj);
        } catch (GlobalServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Category catalog null pointer exception")
    void getAllForSelect_Exception() {
        List<CategoryEntity> entities = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            CategoryEntity category = new CategoryEntity();
            category.setId(i);
            category.setName("Category_" + i);
            Set<SubcategoryEntity> subcategories = new HashSet<>();

            entities.add(category);
        }

        Mockito.when(categoryRepository.findAll())
                .thenReturn(entities);

        boolean hasException = false;
        try {
            List<CategorySelectView> obj = categoryService.getAllForSelect();
        } catch (GlobalServiceException e) {
            hasException = true;
        }

        assertTrue(hasException);
    }
}