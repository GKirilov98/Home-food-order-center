//package dp.home_food_order_center.server.service.impl;
//
//import dp.home_food_order_center.server.data.entity.CategoryEntity;
//import dp.home_food_order_center.server.data.entity.ProductEntity;
//import dp.home_food_order_center.server.data.entity.SubcategoryEntity;
//import dp.home_food_order_center.server.data.view.product.CreateProductView;
//import dp.home_food_order_center.server.data.view.product.ProductDetailsModel;
//import dp.home_food_order_center.server.data.view.product.ProductEditView;
//import dp.home_food_order_center.server.data.view.product.ProductListView;
//import dp.home_food_order_center.server.error.GlobalServiceException;
//import dp.home_food_order_center.server.repository.ICategoryRepository;
//import dp.home_food_order_center.server.repository.IProductRepository;
//import dp.home_food_order_center.server.repository.ISubcategoryRepository;
//import dp.home_food_order_center.server.service.IProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Project: home_food_order_center
// * Created by: G.Kirilov
// * On: 4/15/2021
// */
//@ExtendWith(MockitoExtension.class)
//class IProductServiceImplTest {
//
//    private final ModelMapper modelMapper = new ModelMapper();
//    @Mock
//    private IProductRepository productRepository;
//    @Mock
//    private ISubcategoryRepository subcategoryRepository;
//    @Mock
//    private ICategoryRepository categoryRepository;
//
//    private IProductService productService;
//
//    @BeforeEach
//    public void init() {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        productService = new IProductServiceImpl(modelMapper, productRepository, subcategoryRepository, categoryRepository);
//    }
//
//    @Test
//    @DisplayName("getAllByCategoryIdOrSubcategoryId - No filter")
//    void getAllByCategoryIdOrSubcategoryId_NoFilter() throws GlobalServiceException {
//        List<ProductEntity> productEntityList = new ArrayList<>();
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(4L);
//        categoryEntity.setName("CategoryTest");
//        Set<SubcategoryEntity> subcategorySet = new HashSet<>();
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setCategory(categoryEntity);
//        subcategoryEntity.setId(5L);
//
//        subcategorySet.add(subcategoryEntity);
//        categoryEntity.setSubcategories(subcategorySet);
//
//        for (int i = 0; i < 3; i++) {
//            ProductEntity productEntity = new ProductEntity();
//            productEntity.setId((long) i);
//            productEntity.setImageUrl("imageUrl");
//            productEntity.setImagePublicId("imagePpublicId");
//            productEntity.setName("testName");
//            productEntity.setPrice(BigDecimal.ONE);
//            productEntity.setSubcategory(subcategoryEntity);
//            productEntityList.add(productEntity);
//        }
//        HashSet<ProductEntity> productEntitySet = new HashSet<>();
//        productEntitySet.addAll(productEntityList);
//        subcategoryEntity.setProducts(productEntitySet);
//
//        List<ProductListView> expected = productEntityList.stream()
//                .map(e -> this.modelMapper.map(e, ProductListView.class))
//                .collect(Collectors.toList());
//        Mockito.when(productRepository.findAll()).thenReturn(productEntityList);
//        List<ProductListView> actual = productService.getAllByCategoryIdOrSubcategoryId(null, null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllByCategoryIdOrSubcategoryId - Filter subcategory")
//    void getAllByCategoryIdOrSubcategoryId_FilterSubcategory() throws GlobalServiceException {
//        List<ProductEntity> productEntityList = new ArrayList<>();
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(4L);
//        categoryEntity.setName("CategoryTest");
//        Set<SubcategoryEntity> subcategorySet = new HashSet<>();
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setCategory(categoryEntity);
//        subcategoryEntity.setId(5L);
//        subcategoryEntity.setName("SubcategoryTest");
//
//        subcategorySet.add(subcategoryEntity);
//        categoryEntity.setSubcategories(subcategorySet);
//
//        for (int i = 0; i < 3; i++) {
//            ProductEntity productEntity = new ProductEntity();
//            productEntity.setId((long) i);
//            productEntity.setImageUrl("imageUrl");
//            productEntity.setImagePublicId("imagePublicId");
//            productEntity.setName("testName");
//            productEntity.setPrice(BigDecimal.ONE);
//            productEntity.setSubcategory(subcategoryEntity);
//            productEntityList.add(productEntity);
//        }
//        HashSet<ProductEntity> productEntitySet = new HashSet<>();
//        productEntitySet.addAll(productEntityList);
//        subcategoryEntity.setProducts(productEntitySet);
//
//        List<ProductListView> expected = productEntityList.stream()
//                .map(e -> this.modelMapper.map(e, ProductListView.class))
//                .collect(Collectors.toList());
//        Mockito.when(productRepository.findAllBySubcategoryIdIn(Mockito.anyList())).thenReturn(productEntityList);
//        List<ProductListView> actual = productService.getAllByCategoryIdOrSubcategoryId(null, subcategoryEntity.getId());
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllByCategoryIdOrSubcategoryId - Filter category")
//    void getAllByCategoryIdOrSubcategoryId_FilterCategory() throws GlobalServiceException {
//        List<ProductEntity> productEntityList = new ArrayList<>();
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(4L);
//        categoryEntity.setName("CategoryTest");
//
//        Set<SubcategoryEntity> subcategorySet = new HashSet<>();
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setCategory(categoryEntity);
//        subcategoryEntity.setId(5L);
//        subcategoryEntity.setName("SubcategoryTest");
//        subcategorySet.add(subcategoryEntity);
//
//        categoryEntity.setSubcategories(subcategorySet);
//
//        for (int i = 0; i < 3; i++) {
//            ProductEntity productEntity = new ProductEntity();
//            productEntity.setId((long) i);
//            productEntity.setImageUrl("imageUrl");
//            productEntity.setImagePublicId("imagePublicId");
//            productEntity.setName("testName");
//            productEntity.setPrice(BigDecimal.ONE);
//            productEntity.setSubcategory(subcategoryEntity);
//            productEntityList.add(productEntity);
//        }
//        HashSet<ProductEntity> productEntitySet = new HashSet<>();
//        productEntitySet.addAll(productEntityList);
//        subcategoryEntity.setProducts(productEntitySet);
//
//        List<ProductListView> expected = productEntityList.stream()
//                .map(e -> this.modelMapper.map(e, ProductListView.class))
//                .collect(Collectors.toList());
//        Mockito.when(categoryRepository.findById(categoryEntity.getId())).thenReturn(Optional.of(categoryEntity));
//        Mockito.when(productRepository.findAllBySubcategoryIdIn(Mockito.anyList())).thenReturn(productEntityList);
//
//        List<ProductListView> actual = productService.getAllByCategoryIdOrSubcategoryId(categoryEntity.getId(), null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getOneById - invalid id")
//    void getOneById_InvalidId() {
//        boolean isGSE = false;
//        try {
//            this.productService.getOneById(1L);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("getOneById - ValidId")
//    void getOneById_Valid() throws GlobalServiceException {
//        List<ProductEntity> productEntityList = new ArrayList<>();
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(4L);
//        categoryEntity.setName("CategoryTest");
//
//        Set<SubcategoryEntity> subcategorySet = new HashSet<>();
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setCategory(categoryEntity);
//        subcategoryEntity.setId(5L);
//        subcategoryEntity.setName("SubcategoryTest");
//        subcategorySet.add(subcategoryEntity);
//
//        categoryEntity.setSubcategories(subcategorySet);
//
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setId((long) 1L);
//        productEntity.setName("testName");
//        productEntity.setDescription("description");
//        productEntity.setPrice(BigDecimal.ONE);
//        productEntity.setAvailableQuantity(999);
//        productEntity.setImagePublicId("imagePublicId");
//        productEntity.setImageUrl("imageUrl");
//        productEntity.setSubcategory(subcategoryEntity);
//        productEntityList.add(productEntity);
//
//        HashSet<ProductEntity> productEntitySet = new HashSet<>();
//        productEntitySet.addAll(productEntityList);
//        subcategoryEntity.setProducts(productEntitySet);
//
//        List<ProductDetailsModel> expected = productEntityList.stream()
//                .map(e -> {
//                    ProductDetailsModel map = this.modelMapper.map(e, ProductDetailsModel.class);
//                    map.setCategory(categoryEntity.getName());
//                    map.setCategoryId(categoryEntity.getId());
//                    map.setSubcategory(subcategoryEntity.getName());
//                    map.setSubcategoryId(subcategoryEntity.getId());
//                    return map;
//                })
//                .collect(Collectors.toList());
//
//        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(productEntity));
//        List<ProductDetailsModel> actual = productService.getOneById(1L);
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    @DisplayName("insertOne - valid model")
//    void insertOne_validModel() throws GlobalServiceException {
//        CreateProductView productView = new CreateProductView();
//        productView.setCategoryId(1L);
//        productView.setSubcategoryId(1L);
//        productView.setImageUrl("imageUrl");
//        productView.setImagePublicId("imagePublicId");
//        productView.setName("name");
//        productView.setDescription("descriptionaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        productView.setAvailableQuantity(33L);
//        productView.setPrice(BigDecimal.ONE);
//
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setId(5L);
//        subcategoryEntity.setName("SubcategoryTest");
//
//        Mockito.when(subcategoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(subcategoryEntity));
//        Mockito.when(productRepository.saveAndFlush(Mockito.any())).thenReturn(null);
//        String expected = "Create product successful!";
//        String actual = this.productService.insertOne(productView);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("editProductById - invalid id throw GSE")
//    void editProductById_invalidId() {
//        boolean isGSE = false;
//        try {
//            productService.editProductById(1L, null);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("editProductById - invalid subcategory throw GSE")
//    void editProductById_invalidSubcategoryId() {
//        boolean isGSE = false;
//        try {
//            ProductEditView params = new ProductEditView();
//            params.setSubcategoryId(-1L);
//            Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of((new ProductEntity())));
//            productService.editProductById(1L, params);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("editProductById - validData")
//    void editProductById_validData() throws GlobalServiceException {
//        ProductEditView params = new ProductEditView();
//        params.setCategoryId(1L);
//        params.setSubcategoryId(1L);
//        params.setImageUrl("imageUrl");
//        params.setImagePublicId("imagePublicId");
//        params.setName("name");
//        params.setDescription("descriptionaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        params.setAvailableQuantity(22);
//        params.setPrice(BigDecimal.ONE);
//
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(4L);
//        categoryEntity.setName("CategoryTest");
//
//        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
//        subcategoryEntity.setId(5L);
//        subcategoryEntity.setName("SubcategoryTest");
//        subcategoryEntity.setCategory(categoryEntity);
//
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setId(1L);
//        productEntity.setImageUrl(params.getImageUrl());
//        productEntity.setImagePublicId(params.getImagePublicId());
//        productEntity.setName(params.getName());
//        productEntity.setDescription(params.getDescription());
//        productEntity.setAvailableQuantity(params.getAvailableQuantity());
//        productEntity.setPrice(params.getPrice());
//        productEntity.setSubcategory(subcategoryEntity);
//
//        Mockito.when(subcategoryRepository.findById(Mockito.any())).thenReturn(Optional.of(subcategoryEntity));
//        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of((productEntity)));
//        Mockito.when(productRepository.saveAndFlush(Mockito.any())).thenReturn(null);
//
//        ProductDetailsModel expected = modelMapper.map(productEntity, ProductDetailsModel.class);
//        expected.setId(productEntity.getId());
//        expected.setCategoryId(subcategoryEntity.getCategory().getId());
//        expected.setCategory(categoryEntity.getName());
//        expected.setSubcategory(subcategoryEntity.getName());
//        expected.setSubcategoryId(subcategoryEntity.getId());
//        List<ProductDetailsModel> actualList = productService.editProductById(1L, params);
//        assertEquals(expected, actualList.get(0));
//
//    }
//}