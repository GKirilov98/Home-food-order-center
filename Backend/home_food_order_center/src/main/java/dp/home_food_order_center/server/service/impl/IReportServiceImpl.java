package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.server.data.entity.OrderEntity;
import dp.home_food_order_center.server.data.entity.ReceiptEntity;
import dp.home_food_order_center.server.data.entity.UserEntity;
import dp.home_food_order_center.server.data.view.order.OrderExportView;
import dp.home_food_order_center.server.data.view.receipt.ReceiptExportView;
import dp.home_food_order_center.server.data.view.user.UserExportView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IReceiptRepository;
import dp.home_food_order_center.server.service.IReportService;
import dp.home_food_order_center.server.utils.ExportToExcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
@Service
public class IReportServiceImpl implements IReportService {
    private final Logger logger = LogManager.getLogger(IReceiptServiceImpl.class);
    private final IReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;

    public IReportServiceImpl(IReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public File getReceiptById(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();


        try {
            logger.info(String.format("%s: Starting Report.getReceiptById service!", logId));
            ReceiptEntity entity = receiptRepository.findById(id).orElseThrow(() -> new GlobalServiceException(logId, "Невалидно ИД!", "Invalid id!"));
            ExportToExcel exportToExcel = new ExportToExcel(logId);

            String[] fileNames = new String[3];
            String[] titles = new String[3];
            List<List<?>> data = new ArrayList<>();

            //Бележка
            String fileNameReceipt = "OneReceipt_" + entity.getId();
            String titleReceipt = "Единична справка на касова бележка";
            List<ReceiptExportView> receipts = new ArrayList<>();
            ReceiptExportView mapReceipt = this.modelMapper.map(entity, ReceiptExportView.class);
            mapReceipt.setUserUsername(entity.getUser().getUsername());
            receipts.add(mapReceipt);
            fileNames[0] = fileNameReceipt;
            titles[0] = titleReceipt;
            data.add(receipts);

            //Продуктите от бележката
            String fileNameOrder = "OrdersReceipt_" + entity.getId();
            String titleOrder = "Направените поръчки за касова бележка с id #" + id;
            List<OrderExportView> orders = entity.getOrders().stream()
                    .map(e -> {
                        OrderExportView map = this.modelMapper.map(e, OrderExportView.class);
                        map.setProductName(e.getProduct().getName());
                        map.setProductUnitPrice(e.getProduct().getPrice());
                        return map;
                    })
                    .collect(Collectors.toList());
            fileNames[1] = fileNameOrder;
            titles[1] = titleOrder;
            data.add(orders);

            //Данни за потребителия направил бележката
            String fileNameUser = "UserReceipt_" + entity.getId();
            String titleUser = "Данни за потребителия собственик на касовата бележка с id #" + id;
            List<UserExportView> users = new ArrayList<>();
            users.add(this.modelMapper.map(entity.getUser(), UserExportView.class));
            fileNames[2] = fileNameUser;
            titles[2] = titleUser;
            data.add(users);

            return exportToExcel.export(fileNames, titles, data);
        } catch (GlobalServiceException e){
            throw e;
        }catch (Exception e) {
            logger.error(String.format("%s: Unexpected Report.getReceiptById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected Report.getReceiptById service error!");
        } finally {
            logger.info(String.format("%s: Finished Report.getReceiptById service!", logId));
        }
    }
}
