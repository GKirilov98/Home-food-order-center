package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IOrderRepository;
import dp.home_food_order_center.server.service.IOrderService;
import dp.home_food_order_center.server.service.IProductService;
import dp.home_food_order_center.server.service.IReceiptService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/15/2021
 */
@ExtendWith(MockitoExtension.class)
class IOrderServiceImplTest {
    @Mock
    private  IOrderRepository orderRepository;
    @Mock
    private  IReceiptService receiptService;
    @Mock
    private  IProductService productService;


//    @Test
//    @DisplayName("Insert new order with valid data")
//    void insertOne() {
//        String expected = "Successful create an order!";
//        int quantity = 999;
//
//        Long productId = -1L;
//        Long userId = -1L;
//        ProductDetailsModel productModel = new ProductDetailsModel();
//        productModel.setId(productId);
//        productModel.setName("TestName");
//        productModel.setCategory("TestCategory");
//        productModel.setCategoryId(-1L);
//        productModel.setSubcategory("TestSubcategory");
//        productModel.setSubcategoryId(-1L);
//        productModel.setDescription("TestDescription");
//        productModel.setAvailableQuantity(999);
//        productModel.setPrice(BigDecimal.ONE);
//        productModel.setImageUrl("cloudinaryURL");
//        productModel.setImageUrl("cloudinaryPublicId");
//        List<ProductDetailsModel> productDetailsModels = new ArrayList<>();
//        productDetailsModels.add(productModel);
//
//        UserDetailsImpl details = new UserDetailsImpl(userId, null, null, null, null);
//
//        ReceiptModel receiptModel = new ReceiptModel();
//        receiptModel.setId(-1L);
//        try {
//            Mockito.when(productService.getOneById(productId)).thenReturn( productDetailsModels);
//            Mockito.when(receiptService.getNotPaidReceiptByUserId(userId)).thenReturn(receiptModel);
//            Mockito.doNothing().when(orderRepository).createOrderCustom(productId, quantity, 0, -1L, BigDecimal.ONE);
//
//
//            IOrderService service = new IOrderServiceImpl(orderRepository, receiptService, productService);
//            String actual = service.insertOne(productId, quantity);
//            assertEquals(expected, actual);
//        } catch (GlobalServiceException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    @DisplayName("Order quantity more than available throw GlobalServiceException")
    void insertOne_OrderMoreThanAvailable() {
        int quantity = 999;

        Long productId = -1L;
        ProductDetailsModel productModel = new ProductDetailsModel();
        productModel.setId(productId);
        productModel.setName("TestName");
        productModel.setCategory("TestCategory");
        productModel.setCategoryId(-1L);
        productModel.setSubcategory("TestSubcategory");
        productModel.setSubcategoryId(-1L);
        productModel.setDescription("TestDescription");
        productModel.setAvailableQuantity(998);
        productModel.setPrice(BigDecimal.ONE);
        productModel.setImageUrl("cloudinaryURL");
        productModel.setImageUrl("cloudinaryPublicId");
        List<ProductDetailsModel> productDetailsModels = new ArrayList<>();
        productDetailsModels.add(productModel);
        boolean isThrowGlobal = false;
        try {
            Mockito.when(productService.getOneById(productId)).thenReturn( productDetailsModels);
            IOrderService service = new IOrderServiceImpl(orderRepository, receiptService, productService);
            String actual = service.insertOne(productId, quantity);
        } catch (GlobalServiceException e) {
           isThrowGlobal = true;
        }

        assertTrue(isThrowGlobal);
    }

    @Test
    @DisplayName("Not found productId catch NullPointerException and throw GlobalServiceException")
    void insertOne_NotFoundProduct() {
        int quantity = 999;
        Long productId = -1L;
        boolean isThrowGlobal = false;
        try {
            Mockito.when(productService.getOneById(productId)).thenReturn( null);
            IOrderService service = new IOrderServiceImpl(orderRepository, receiptService, productService);
            String actual = service.insertOne(productId, quantity);
        } catch (GlobalServiceException e) {
            isThrowGlobal = true;
        }

        assertTrue(isThrowGlobal);
    }

    @Test
    @DisplayName("Not found order throw GlobalServiceException")
    void deleteOneById() {
        boolean isThrowGlobal = false;
        try {
            Mockito.when(orderRepository.findById(-1L)).thenReturn(null);
            IOrderService service = new IOrderServiceImpl(orderRepository, receiptService, productService);
            service.deleteOneById(-1L);
        } catch (GlobalServiceException e) {
          isThrowGlobal = true;
        }

        assertTrue(isThrowGlobal);
    }
}