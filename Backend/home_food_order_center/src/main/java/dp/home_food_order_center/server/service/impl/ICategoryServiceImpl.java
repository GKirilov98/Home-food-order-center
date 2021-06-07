package dp.home_food_order_center.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dp.home_food_order_center.server.data.entity.CategoryEntity;
import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
import dp.home_food_order_center.server.data.model.category.CategoryModel;
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
    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ICategoryServiceImpl(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryModel> getAll() throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getAllForSelect service!", logId));
            return this.categoryRepository.findAll()
                    .stream()
                    .map(e -> this.modelMapper.map(e, CategoryModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getAllForSelect service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getAllForSelect service!", logId));
        }
    }
}
