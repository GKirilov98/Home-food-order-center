package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.server.data.entity.CategoryEntity;
import dp.home_food_order_center.server.data.entity.ProductEntity;
import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
import dp.home_food_order_center.server.data.entity.base.BaseEntity;
import dp.home_food_order_center.server.data.view.product.CreateProductView;
import dp.home_food_order_center.server.data.view.product.ProductDetailsView;
import dp.home_food_order_center.server.data.view.product.ProductEditView;
import dp.home_food_order_center.server.data.view.product.ProductListView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.ICategoryRepository;
import dp.home_food_order_center.server.repository.IProductRepository;
import dp.home_food_order_center.server.repository.ISubcategoryRepository;
import dp.home_food_order_center.server.service.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 9:11 PM
 */
@Service
public class ProductServiceImpl implements IProductService {
    private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISubcategoryRepository subcategoryRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<ProductListView> getAllByCategoryIdOrSubcategoryId(Long categoryId, Long subcategoryId) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Записване в базата
        try {
            logger.info(String.format("%s: Starting getAllByCategoryIdOrSubcategoryId service!", logId));
            List<ProductEntity> entities;
            if (categoryId == null && subcategoryId == null) {
                entities = this.productRepository.findAll();
            } else if (subcategoryId != null) {
                List<Long> subcategoryList = new ArrayList<>();
                subcategoryList.add(subcategoryId);
                entities = this.productRepository.findAllBySubcategoryIdIn(subcategoryList);
            } else {
                List<Long> subcategoriesId = this.categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new GlobalServiceException(logId, "Невалидно ид на категория!", "Invalid category id!"))
                        .getSubcategories().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList());
                entities = this.productRepository.findAllBySubcategoryIdIn(subcategoriesId);
            }


            final int[] counter = {0};
            return entities.stream()
                    .map(e -> {
                        ProductListView view = this.modelMapper.map(e, ProductListView.class);
                        view.setSold(e.getAvailableQuantity() > 0);
                        view.setSale(counter[0] % 5 == 0);
                        counter[0]++;
                        return view;
                    })
                    .collect(Collectors.toList());

        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getAllByCategoryIdOrSubcategoryId service!", logId));
        }
    }

    @Override
    public List<ProductDetailsView> getOneById(Long productId) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Записване в базата
        try {
            logger.info(String.format("%s: Starting getOneById service!", logId));
            ProductEntity productEntity = this.productRepository.findById(productId)
                    .orElse(null);
            if (productEntity == null){
                logger.error(String.format("%s: No such product with id: %d", logId, productId));
                throw new  GlobalServiceException(logId, "Не е намерен продукт с това id" ,String.format("No such product with id: %d", productId));
            }

            SubcategoryEntity subcategory = productEntity.getSubcategory();
            CategoryEntity category = productEntity.getSubcategory().getCategory();

            ProductDetailsView detailsView = this.modelMapper.map(productEntity, ProductDetailsView.class);
            detailsView.setCategory(category.getName());
            detailsView.setCategoryId(category.getId());

            detailsView.setSubcategory(subcategory.getName());
            detailsView.setSubcategoryId(subcategory.getId());
            List<ProductDetailsView> list = new ArrayList<>();
            list.add(detailsView);
            return list;
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getOneById service!", logId));
        }
    }

    //Admin >>>
    @Override
    public String insertOne(CreateProductView model) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Записване в базата
        try {
            logger.info(String.format("%s: Starting insertOne service!", logId));
            ProductEntity entity = this.modelMapper.map(model, ProductEntity.class);
            SubcategoryEntity subcategoryEntity = subcategoryRepository.findById(model.getSubcategoryId())
                    .orElseThrow(() -> new GlobalServiceException(logId, "Невалидна подкатегория", "Invalid subcategory!"));
            entity.setSubcategory(subcategoryEntity);
            productRepository.saveAndFlush(entity);
            return "Create product successful!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished insertOne service!", logId));
        }
    }

    @Override
    public List<ProductDetailsView> editProductById(Long id, ProductEditView params) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Записване в базата
        try {
            logger.info(String.format("%s: Starting editProductById service!", logId));
            ProductEntity oldProductEntity = this.productRepository.findById(id).orElse(null);
            if (oldProductEntity == null){
                logger.error(String.format("%s: No such product with id: %d", logId, id));
                throw new  GlobalServiceException(logId, "Не е намерен продукт с това id" ,String.format("No such product with id: %d", id));
            }

            oldProductEntity.setImageUrl(params.getImageUrl());
            oldProductEntity.setImagePublicId(params.getImagePublicId());
            oldProductEntity.setName(params.getName());
            oldProductEntity.setDescription(params.getDescription());
            oldProductEntity.setAvailableQuantity(params.getAvailableQuantity());
            oldProductEntity.setPrice(params.getPrice());
            SubcategoryEntity subcategoryEntity = this.subcategoryRepository.findById(params.getSubcategoryId()).orElse(null);
            if (subcategoryEntity == null){
                logger.error(String.format("%s: No such subcategory with id: %d", logId, params.getSubcategoryId()));
                throw new  GlobalServiceException(logId, "Не е намерена под категориа с това id",
                        String.format("No such subcategory with id: %d", params.getSubcategoryId()));
            }
            oldProductEntity.setSubcategory(subcategoryEntity);
            oldProductEntity.setDateLastEdit(new Timestamp(System.currentTimeMillis()));

            ProductEntity newProductEntity = this.productRepository.saveAndFlush(oldProductEntity);

            String subcategoryName = subcategoryEntity.getName();
            String categoryName = subcategoryEntity.getCategory().getName();
            ProductDetailsView detailsView = this.modelMapper.map(newProductEntity, ProductDetailsView.class);
            detailsView.setCategory(categoryName);
            detailsView.setSubcategory(subcategoryName);
            List<ProductDetailsView> list = new ArrayList<>();
            list.add(detailsView);
            return list;
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished editProductById service!", logId));
        }
    }
}
