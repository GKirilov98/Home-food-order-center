//package dp.home_food_order_center.server.service.impl;
//
//import dp.home_food_order_center.server.data.entity.ReceiptEntity;
//import dp.home_food_order_center.server.data.entity.ReceiptStatusType;
//import dp.home_food_order_center.server.data.entity.UserEntity;
//import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
//import dp.home_food_order_center.server.data.model.user.UserModel;
//import dp.home_food_order_center.server.data.view.receipt.ReceiptListView;
//import dp.home_food_order_center.server.error.GlobalServiceException;
//import dp.home_food_order_center.server.repository.IReceiptRepository;
//import dp.home_food_order_center.server.repository.IUserRepository;
//import dp.home_food_order_center.server.service.IReceiptService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.TestComponent;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Project: home_food_order_center
// * Created by: G.Kirilov
// * On: 4/15/2021
// */
//@ExtendWith(MockitoExtension.class)
//@TestComponent
//class IReceiptServiceImplTest {
//    private ModelMapper modelMapper = new ModelMapper();
//    @Mock
//    private IUserRepository userRepository;
//    @Mock
//    private IReceiptRepository receiptRepository;
//
//    @Test
//    @DisplayName("getNotPaidReceiptByUserId - Not found user must throw GlobalServiceException")
//    void getNotPaidReceiptByUserId_NotFoundUser_ThrowGlobalServiceException() {
//        Long userId = -1L;
//        Mockito.when(userRepository.findById(userId)).thenReturn(null);
//        boolean isThrowGlobalService = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            receiptService.getNotPaidReceiptByUserId(userId);
//        } catch (GlobalServiceException e) {
//            isThrowGlobalService = true;
//        }
//
//        assertTrue(isThrowGlobalService);
//    }
//
//    @Test
//    @DisplayName("getNotPaidReceiptByUserId - Already exist not paid receipt")
//    void getNotPaidReceiptByUserId_NotFoundUser_AlreadyExistNotPaidReceipt() throws GlobalServiceException {
//        Set<ReceiptEntity> receiptEntities = new HashSet<>();
//        UserEntity userEntity = new UserEntity();
//        ReceiptModel expected = null;
//        for (int i = 0; i < 1; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setDeliveryAddress("TestAddress");
//            receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setId((long) i);
//            receiptEntity.setUser(userEntity);
//            receiptEntities.add(receiptEntity);
//            expected = modelMapper.map(receiptEntity, ReceiptModel.class);
//        }
//        userEntity.setReceipts(receiptEntities);
//        expected.setUser(modelMapper.map(userEntity, UserModel.class));
//
//        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);
//
//
//        Long userId = -1L;
//        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUserEntity);
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        ReceiptModel actual = receiptService.getNotPaidReceiptByUserId(userId);
//        boolean result = expected.toString().equalsIgnoreCase(actual.toString());
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("getNotPaidReceiptByUserId - Exist more than one not paid receipt")
//    void getNotPaidReceiptByUserId_NotFoundUser_ExistMoreThanOneNotPaidReceipt() {
//        Set<ReceiptEntity> receiptEntities = new HashSet<>();
//        for (int i = 0; i < 2; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setDeliveryAddress("TestAdress");
//            receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setId((long) i);
//            receiptEntities.add(receiptEntity);
//        }
//
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setReceipts(receiptEntities);
//        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);
//
//        Long userId = -1L;
//        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUserEntity);
//
//        boolean isThrowGlobalService = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            receiptService.getNotPaidReceiptByUserId(userId);
//        } catch (GlobalServiceException e) {
//            isThrowGlobalService = true;
//        }
//
//
//        assertTrue(isThrowGlobalService);
//    }
//
//    @Test
//    @DisplayName("getNotPaidReceiptByUserId - Create New one")
//    void getNotPaidReceiptByUserId_NotFoundUser_CreateNewOne() throws GlobalServiceException {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setReceipts(new HashSet<>());
//
//        ReceiptEntity receiptEntity = new ReceiptEntity();
//        receiptEntity.setDeliveryAddress("TestAddress");
//        receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//        receiptEntity.setTotalAmount(BigDecimal.TEN);
//        receiptEntity.setId(-1L);
//        receiptEntity.setUser(userEntity);
//
//        ReceiptModel expected = modelMapper.map(receiptEntity, ReceiptModel.class);
//
//        Optional<UserEntity> optionalUserEntity = Optional.of(userEntity);
//
//        Long userId = -1L;
//        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUserEntity);
//        Mockito.when(receiptRepository.saveAndFlush(Mockito.any())).thenReturn(receiptEntity);
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        ReceiptModel actual = receiptService.getNotPaidReceiptByUserId(userId);
//        boolean result = expected.toString().equalsIgnoreCase(actual.toString());
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("confirmReceipt - Not found receipt throw GlobalServiceException")
//    void confirmReceipt_NotFoundReceipt() {
//        Long receiptId = -1L;
//        String city = "testCity";
//        String address = "address";
//        Mockito.when(receiptRepository.findById(receiptId)).thenReturn(null);
//        boolean isGSE = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            receiptService.confirmReceipt(receiptId, city, address);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("confirmReceipt - Confirm Success")
//    void confirmReceipt_ConfirmSuccess() throws GlobalServiceException {
//        Long receiptId = -1L;
//        String city = "testCity";
//        String address = "address";
//        Mockito.when(receiptRepository.findById(receiptId)).thenReturn(Optional.of(new ReceiptEntity()));
//        Mockito.when(receiptRepository.saveAndFlush(Mockito.any())).thenReturn(null);
//
//        String expected = "Confirmed cart successfully!";
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        String actual = receiptService.confirmReceipt(receiptId, city, address);
//
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getReceiptById - Not found receipt throw GlobalServiceException")
//    void getReceiptById_NotFoundReceipt() {
//        Long receiptId = -1L;
//        Mockito.when(receiptRepository.findById(receiptId)).thenReturn(null);
//        boolean isGSE = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            receiptService.getReceiptById(receiptId);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("getReceiptById - Not found receipt throw GlobalServiceException")
//    void getReceiptById_ReturnReceiptId() throws GlobalServiceException {
//        Long receiptId = -1L;
//        UserEntity userEntity = new UserEntity();
//        userEntity.setReceipts(new HashSet<>());
//
//        ReceiptEntity receiptEntity = new ReceiptEntity();
//        receiptEntity.setDeliveryAddress("TestAddress");
//        receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//        receiptEntity.setTotalAmount(BigDecimal.TEN);
//        receiptEntity.setId(-1L);
//        receiptEntity.setUser(userEntity);
//
//        ReceiptModel expected = modelMapper.map(receiptEntity, ReceiptModel.class);
//        Mockito.when(receiptRepository.findById(receiptId)).thenReturn(Optional.of(receiptEntity));
//
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptModel> actualList = receiptService.getReceiptById(receiptId);
//        assertEquals(expected, actualList.get(0));
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - Get not filtered receipt")
//    void getAllReceiptWithFilter_NotFiltered() throws GlobalServiceException {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptListView> expected = new ArrayList<>();
//        List<ReceiptEntity> mockEntityList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setId((long) i);
//            receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//            receiptEntity.setUser(userEntity);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//            mockEntityList.add(receiptEntity);
//            expected.add(this.modelMapper.map(receiptEntity, ReceiptListView.class));
//        }
//
//        Mockito.when(receiptRepository.findAll()).thenReturn(mockEntityList);
////        String status = null;
////        String username = null;
////        Long receiptId = null;
////        String dateString = null;
////        String orderBy = null;
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter("", "", null, "", "");
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - Get all filtered")
//    void getAllReceiptWithFilter_AllFilter() throws GlobalServiceException {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptListView> expected = new ArrayList<>();
//        List<ReceiptEntity> mockEntityList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setId((long) i);
//            receiptEntity.setStatusCode(ReceiptStatusType.SEND);
//            receiptEntity.setUser(userEntity);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//            mockEntityList.add(receiptEntity);
//            expected.add(this.modelMapper.map(receiptEntity, ReceiptListView.class));
//        }
//
//        Mockito.when(this.receiptRepository.findAllByStatusCode(ReceiptStatusType.SEND)).thenReturn(mockEntityList);
//        String status = "SEND";
//        String username = "TestUsername";
//        Long receiptId = null;
//        String dateString = new Timestamp(System.currentTimeMillis()).toString();
//        String orderBy = "STATUS desc";
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter(status, username, receiptId, dateString, orderBy);
//        expected.sort(Comparator.comparing(ReceiptListView::getStatusCode));
//        Collections.reverse(expected);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - Get all by receipId")
//    void getAllReceiptWithFilter_AllFilterByReceiptId() throws GlobalServiceException {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptListView> expected = new ArrayList<>();
//
//        ReceiptEntity receiptEntity = new ReceiptEntity();
//        receiptEntity.setId((long) 3);
//        receiptEntity.setStatusCode(ReceiptStatusType.SEND);
//        receiptEntity.setUser(userEntity);
//        receiptEntity.setTotalAmount(BigDecimal.TEN);
//        receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//
//        expected.add(this.modelMapper.map(receiptEntity, ReceiptListView.class));
//
//
//        Long receiptId = 3L;
//        Mockito.when(this.receiptRepository.findById(receiptId)).thenReturn(Optional.of(receiptEntity));
//        String status = "SEND";
//        String username = "TestUsername";
//        String dateString = new Timestamp(System.currentTimeMillis()).toString();
//        String orderBy = "USERNAME desc";
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter(status, username, receiptId, dateString, orderBy);
//        expected.sort(Comparator.comparing(ReceiptListView::getUserUsername));
//        Collections.reverse(expected);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - throw invalidStatus")
//    void getAllReceiptWithFilter_InvalidStatus() {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//
//        ReceiptEntity receiptEntity = new ReceiptEntity();
//        receiptEntity.setId((long) 3);
//        receiptEntity.setStatusCode(ReceiptStatusType.SEND);
//        receiptEntity.setUser(userEntity);
//        receiptEntity.setTotalAmount(BigDecimal.TEN);
//        receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//
//        Long receiptId = null;
//        String status = "SENDasdasd";
//        String username = "TestUsername";
//        String dateString = new Timestamp(System.currentTimeMillis()).toString();
//        String orderBy = "AMOUNT desc";
//        boolean isGSE = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter(status, username, receiptId, dateString, orderBy);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//        assertTrue(isGSE);
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - Get all filtered by Status PAID")
//    void getAllReceiptWithFilter_AllFilterByFilterPaid() throws GlobalServiceException {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptListView> expected = new ArrayList<>();
//        List<ReceiptEntity> mockEntityList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setId((long) i);
//            receiptEntity.setStatusCode(ReceiptStatusType.PAID);
//            receiptEntity.setUser(userEntity);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//            mockEntityList.add(receiptEntity);
//            expected.add(this.modelMapper.map(receiptEntity, ReceiptListView.class));
//        }
//
//        Mockito.when(this.receiptRepository.findAllByStatusCode(ReceiptStatusType.PAID)).thenReturn(mockEntityList);
//        String status = "PAID";
//        String username = "TestUsername";
//        Long receiptId = null;
//        String dateString = new Timestamp(System.currentTimeMillis()).toString();
//        String orderBy = "DATE desc";
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter(status, username, receiptId, dateString, orderBy);
//        expected.sort(Comparator.comparing(ReceiptListView::getDateAdded));
//        Collections.reverse(expected);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("getAllReceiptWithFilter - Get all filtered by Status SHOPPING")
//    void getAllReceiptWithFilter_AllFilterByFilterSHOPPING() throws GlobalServiceException {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptListView> expected = new ArrayList<>();
//        List<ReceiptEntity> mockEntityList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ReceiptEntity receiptEntity = new ReceiptEntity();
//            receiptEntity.setId((long) i);
//            receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//            receiptEntity.setUser(userEntity);
//            receiptEntity.setTotalAmount(BigDecimal.TEN);
//            receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//            mockEntityList.add(receiptEntity);
//            expected.add(this.modelMapper.map(receiptEntity, ReceiptListView.class));
//        }
//
//        Mockito.when(this.receiptRepository.findAllByStatusCode(ReceiptStatusType.SHOPPING)).thenReturn(mockEntityList);
//        String status = "SHOPPING";
//        String username = "TestUsername";
//        Long receiptId = null;
//        String dateString = new Timestamp(System.currentTimeMillis()).toString();
//        String orderBy = "DATE desc";
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptListView> actual = receiptService.getAllReceiptWithFilter(status, username, receiptId, dateString, orderBy);
//        expected.sort(Comparator.comparing(ReceiptListView::getDateAdded));
//        Collections.reverse(expected);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("changeReceiptToPaid - Change receipt status to paid")
//    void changeReceiptToPaid_ChangeSuccess() throws GlobalServiceException {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("TestUsername");
//        List<ReceiptModel> expected = new ArrayList<>();
//        Long receiptId = 1L;
//        ReceiptEntity receiptEntity = new ReceiptEntity();
//        receiptEntity.setId(receiptId);
//        receiptEntity.setStatusCode(ReceiptStatusType.SHOPPING);
//        receiptEntity.setUser(userEntity);
//        receiptEntity.setTotalAmount(BigDecimal.TEN);
//        receiptEntity.setDateAdded(new Timestamp(System.currentTimeMillis()));
//
//        expected.add(this.modelMapper.map(receiptEntity, ReceiptModel.class));
//        expected.get(0).setStatusCode(ReceiptStatusType.PAID.toString());
//
//        Mockito.when(this.receiptRepository.findById(receiptId)).thenReturn(Optional.of(receiptEntity));
//        Mockito.when(receiptRepository.saveAndFlush(Mockito.any())).thenReturn(null);
//
//        IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//        List<ReceiptModel> actual = receiptService.changeReceiptToPaid(receiptId);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("changeReceiptToPaid - throwError for invalid id")
//    void changeReceiptToPaid_ThrowError() {
//        Mockito.when(this.receiptRepository.findById(1L)).thenReturn(null);
//        boolean isGSE = false;
//        try {
//            IReceiptService receiptService = new IReceiptServiceImpl(modelMapper, userRepository, orderRepository, receiptRepository);
//            List<ReceiptModel> actual = receiptService.changeReceiptToPaid(1L);
//        } catch (GlobalServiceException e) {
//            isGSE = true;
//        }
//
//        assertTrue(isGSE);
//    }
//}