package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.server.data.entity.*;
import dp.home_food_order_center.server.data.model.receipt.ReceiptModel;
import dp.home_food_order_center.server.data.view.receipt.ReceiptListView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IReceiptRepository;
import dp.home_food_order_center.server.repository.IUserRepository;
import dp.home_food_order_center.server.service.IReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/5/2021
 */
@Service
public class IReceiptServiceImpl implements IReceiptService {
    private final Logger logger = LogManager.getLogger(IReceiptServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IReceiptRepository receiptRepository;

    @Override
    public ReceiptModel getNotPaidReceiptByUserId(Long userId) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting insertOne service!", logId));
            UserEntity userEntity = userRepository.findById(userId).orElseThrow(() ->
                    new GlobalServiceException(logId, "Подадена е навалидно ид на потребител!", "userId is null!" ));
            List<ReceiptEntity> notPaidReceipt = userEntity.getReceipts().stream()
                    .filter(r -> r.getStatusCode().equals(ReceiptStatusType.SHOPPING))
                    .collect(Collectors.toList());

            if (notPaidReceipt.size() == 1) {
                return this.modelMapper.map(notPaidReceipt.get(0), ReceiptModel.class);
            } else if (notPaidReceipt.size() > 1) {
                logger.error(String.format("%s: There are more than one not paid receipt!", logId));
                throw new GlobalServiceException(logId, "Намерени са повече от една неплатена разписка", "There are more than one not paid receipt!");
            } else {
                ReceiptEntity entity = new ReceiptEntity();
                entity.setStatusCode(ReceiptStatusType.SHOPPING);
                entity.setUser(userEntity);
                entity.setDateAdded(new Timestamp(System.currentTimeMillis()));
                entity.setTotalAmount(BigDecimal.ZERO);

                ReceiptEntity receiptEntity = this.receiptRepository.saveAndFlush(entity);
                return this.modelMapper.map(receiptEntity, ReceiptModel.class);
            }
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected insertOne service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished insertOne service!", logId));
        }
    }

    @Override
    public List<ReceiptModel> getShoppingReceiptForCurrUser() throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getReceiptForCurrUserByStatusCode service!", logId));
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = ((UserDetailsImpl) principal).getId();
            List<ReceiptEntity> entities = this.receiptRepository.findAllByUserIdAndStatusCode(userId, ReceiptStatusType.SHOPPING);
            return entities.stream()
                    .map(e -> this.modelMapper.map(e, ReceiptModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getReceiptForCurrUserByStatusCode service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getReceiptForCurrUserByStatusCode service!", logId));
        }
    }

//    @Override
//    public List<ReceiptModel> getReceiptForCurrUser() throws GlobalServiceException {
//        String logId = UUID.randomUUID().toString();
//        try {
//            logger.info(String.format("%s: Starting getReceiptForCurrUserByStatusCode service!", logId));
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            Long userId = ((UserDetailsImpl) principal).getId();
//            if (ReceiptStatusType.SHOPPING.toString().equals(statusCode)) {
//                List<ReceiptEntity> entities = this.receiptRepository.findAllByUserIdAndStatusCode(userId, ReceiptStatusType.SHOPPING);
//                return entities.stream()
//                        .map(e -> this.modelMapper.map(e, ReceiptModel.class))
//                        .collect(Collectors.toList());
//            } else {
//                String errMessage = String.format("Invalid status code! Status code: %s", statusCode);
//                logger.error(String.format("%s: %s", logId, errMessage));
//                throw new GlobalServiceException(logId, "Невалиден статус код на количка!", errMessage);
//            }
//        } catch (GlobalServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            logger.error(String.format("%s: Unexpected getReceiptForCurrUserByStatusCode service error!", logId), e);
//            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
//        } finally {
//            logger.info(String.format("%s: Finished getReceiptForCurrUserByStatusCode service!", logId));
//        }
//    }

    @Override
    public String confirmReceipt(Long receiptId, String city, String address) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getReceiptForCurrUserByStatusCode service!", logId));
            ReceiptEntity receiptEntity = this.receiptRepository.findById(receiptId).orElse(null);
            if (receiptEntity == null) {
                logger.error(String.format("%s: Invalid receiptId! %s", logId, receiptId));
                throw new GlobalServiceException(logId, "Невалиден receiptId!", "Invalid receiptId!");
            }

            String dbAddress = String.format("%s, %s", city, address);
            receiptEntity.setDeliveryAddress(dbAddress);
            receiptEntity.setStatusCode(ReceiptStatusType.SEND);
            this.receiptRepository.saveAndFlush(receiptEntity);
            return "Confirmed cart successfully!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getReceiptForCurrUserByStatusCode service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getReceiptForCurrUserByStatusCode service!", logId));
        }
    }

    @Override
    public List<ReceiptModel> getReceiptById(Long id) throws GlobalServiceException {

        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getReceiptById service!", logId));
            ReceiptEntity receiptEntity = this.receiptRepository.findById(id).orElse(null);
            if (receiptEntity == null) {
                logger.error(String.format("%s: Invalid receiptId! %d", logId, id));
                throw new GlobalServiceException(logId, "Невалиден receiptId!", "Invalid receiptId!");
            }

            List<ReceiptModel> list = new ArrayList<>();
            ReceiptModel model = this.modelMapper.map(receiptEntity, ReceiptModel.class);
            list.add(model);
            return list;
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getReceiptById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getReceiptById service!", logId));
        }
    }

    @Override
    public List<ReceiptListView> getAllReceiptWithFilter(String status, String username, Long receiptId, String dateString, String orderBy) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting getReceiptById service!", logId));
            if (status == null) {
                status = "";
            }

            if (username == null) {
                username = "";
            }

            Timestamp searchDate = new Timestamp(System.currentTimeMillis());
            if (dateString != null && dateString.length() > 0) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dateString);
//                Long twentyFourHours = 86399999L; // 23hours and 59 minutes and 59sec
                Long twentyFourHours = 86400000L; // 24hours
                searchDate = new Timestamp(date.getTime() + twentyFourHours);
            }

            if (orderBy == null) {
                orderBy = "";
            }

            String[] orderToken = orderBy.split(" ");
            Comparator<ReceiptEntity> comparator = Comparator.comparing(ReceiptEntity::getId);
            if (orderToken.length == 2) {
                if (orderToken[0].equalsIgnoreCase("STATUS")) {
                    comparator = Comparator.comparing(ReceiptEntity::getStatusCode);
                } else if (orderToken[0].equalsIgnoreCase("USERNAME")) {
                    comparator = Comparator.comparing(e -> e.getUser().getUsername());
                } else if (orderToken[0].equalsIgnoreCase("DATE")) {
                    comparator = Comparator.comparing(ReceiptEntity::getDateAdded);
                } else if (orderToken[0].equalsIgnoreCase("AMOUNT")) {
                    comparator = Comparator.comparing(ReceiptEntity::getTotalAmount);
                }
            }


            List<ReceiptEntity> list = null;
            if (receiptId != null && receiptId > 0) {
                ReceiptEntity receiptEntity = this.receiptRepository.findById(receiptId).orElse(null);
                list = new ArrayList<>();
                list.add(receiptEntity);
            } else if (status.length() == 0 || status.equalsIgnoreCase("ALL")) {
                list = this.receiptRepository.findAll();
            } else if (status.equals(ReceiptStatusType.SHOPPING.toString())) {
                list = this.receiptRepository.findAllByStatusCode(ReceiptStatusType.SHOPPING);
            } else if (status.equals(ReceiptStatusType.SEND.toString())) {
                list = this.receiptRepository.findAllByStatusCode(ReceiptStatusType.SEND);
            } else if (status.equals(ReceiptStatusType.PAID.toString())) {
                list = this.receiptRepository.findAllByStatusCode(ReceiptStatusType.PAID);
            } else {
                logger.error(String.format("%s: Invalid receipt status! %s", logId, status));
                throw new GlobalServiceException(logId, "Невалиден статус на касова!", "Invalid receipt status!");
            }

            String finalUsername = username;
            Timestamp finalSearchDate = searchDate;
            List<ReceiptListView> views = list.stream()
                    .filter(e -> e.getUser().getUsername().contains(finalUsername) && e.getDateAdded().before(finalSearchDate))
                    .sorted(comparator)
                    .map(e -> {
                        ReceiptListView model = this.modelMapper.map(e, ReceiptListView.class);
                        model.setUserUsername(e.getUser().getUsername());
                        return model;
                    })
                    .collect(Collectors.toList());
            if (orderToken.length == 2) {
                if (orderToken[1].equalsIgnoreCase("desc")) {
                    Collections.reverse(views);
                }
            }

            return views;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getReceiptById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getReceiptById service!", logId));
        }
    }

    @Override
    public List<ReceiptModel> changeReceiptToPaid(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting changeReceiptToPaid service!", logId));
            ReceiptEntity receiptEntity = this.receiptRepository.findById(id).orElse(null);
            if (receiptEntity == null) {
                logger.error(String.format("%s: Invalid receiptId! %s", logId, id));
                throw new GlobalServiceException(logId, "Невалиден receiptId!", "Invalid receiptId!");
            }

            receiptEntity.setStatusCode(ReceiptStatusType.PAID);
            List<ReceiptModel> models = new ArrayList<>();
            ReceiptModel model = this.modelMapper.map(receiptRepository.saveAndFlush(receiptEntity), ReceiptModel.class);
            models.add(model);
            return models;

        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected changeReceiptToPaid service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected changeReceiptToPaid service error!");
        } finally {
            logger.info(String.format("%s: Finished changeReceiptToPaid service!", logId));
        }
    }

//    @Override
//    public File exportReceiptById(Long id) throws GlobalServiceException {
//        String logId = UUID.randomUUID().toString();
//        try {
//            logger.info(String.format("%s: Starting exportReceiptById service!", logId));
//            ReceiptEntity receiptEntity = this.receiptRepository.findById(id).orElse(null);
//            if (receiptEntity == null) {
//                logger.error(String.format("%s: Invalid receiptId! %s", logId, id));
//                throw new GlobalServiceException(logId, "Невалиден receiptId!", "Invalid receiptId!");
//            }
//
//            List<ReceiptEntity> list = new ArrayList<>();
//            list.add(receiptEntity);
//            String fileName = "RECEIPT_" + id;
//            String zipName = fileName + "_" + logId;
//            String title = "Касова номер: " + id;
//           return excelService.exportToExcelAndDownloadStream(logId, fileName, zipName,title, list);
//
//        } catch (GlobalServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            logger.error(String.format("%s: Unexpected exportReceiptById service error!", logId), e);
//            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected exportReceiptById service error!");
//        } finally {
//            logger.info(String.format("%s: Finished exportReceiptById service!", logId));
//        }
//
//
//
//    }
}
