package dp.home_food_order_center.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dp.home_food_order_center.server.data.entity.CategoryEntity;
import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
import dp.home_food_order_center.server.data.view.category.CategorySelectView;
import dp.home_food_order_center.server.data.view.subcategory.SubcategorySelectView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.ICategoryRepository;
import dp.home_food_order_center.server.repository.ISubcategoryRepository;
import dp.home_food_order_center.server.service.ICategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
@Service
public class ICategoryServiceImpl implements ICategoryService {
    private final Logger logger = LogManager.getLogger(ICategoryServiceImpl.class);
    @Autowired
    private ISubcategoryRepository subcategoryRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategorySelectView> getAllForSelect() throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getAllForSelect service!", logId));
            //Fixme Попълване на данни
            this.fillData();
            //

            return this.categoryRepository.findAll()
                    .stream()
                    .map(e -> {
                        CategorySelectView map = this.modelMapper.map(e, CategorySelectView.class);
                        SubcategorySelectView[] sub = this.modelMapper.map(e.getSubcategories(), SubcategorySelectView[].class);
                        map.setSubcategories(sub);
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getAllForSelect service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getAllForSelect service!", logId));
        }
    }

    private void fillData() {
        if (this.categoryRepository.findAll().size() == 0) {
            Map<String, String[]> categorySubcategories = new HashMap<>();
            categorySubcategories.put("Напитки", new String[]{"Алкохолни", "Безалкохолни", "Кафе"});
            categorySubcategories.put("Храни", new String[]{"Пица", "Аламинут", "Риба", "Месо", "Хляб", "Скара", "Салата", "Десерт"});
            for (String category : categorySubcategories.keySet()) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setName(category);
                CategoryEntity savedCategory = this.categoryRepository.saveAndFlush(categoryEntity);

                for (String sub : categorySubcategories.get(category)) {
                    SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
                    subcategoryEntity.setName(sub);
                    subcategoryEntity.setCategory(savedCategory);
                    this.subcategoryRepository.saveAndFlush(subcategoryEntity);
                }
            }

        }
    }
}
