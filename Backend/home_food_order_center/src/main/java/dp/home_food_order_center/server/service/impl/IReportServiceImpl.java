package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.server.data.entity.OrderEntity;
import dp.home_food_order_center.server.data.entity.ReceiptEntity;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IReceiptRepository;
import dp.home_food_order_center.server.service.IReportService;
import dp.home_food_order_center.server.utils.ExportToExcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
@Service
public class IReportServiceImpl implements IReportService {
    private final Logger logger = LogManager.getLogger(IReceiptServiceImpl.class);
    private final IReceiptRepository receiptRepository;

    public IReportServiceImpl(IReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }


    @Override
    public File getReceiptById(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        String fileName = "OneReceipt";
        String title = "Единична справка на касова бележка с ИД: " + id;

        try {
            // TODO: 6/4/2021 Трябва да се направи обект и да се мапне 
            logger.info(String.format("%s: Starting Report.getReceiptById service!", logId));
            ReceiptEntity entity = receiptRepository.findById(id).orElseThrow(() -> new GlobalServiceException(logId, "Невалидно ИД!", "Invalid id!"));
            ExportToExcel exportToExcel = new ExportToExcel(logId);
            List<ReceiptEntity> listEntities = new ArrayList<>();
            listEntities.add(entity);
            return exportToExcel.export(fileName, title, listEntities);
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
