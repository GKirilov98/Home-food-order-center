package dp.home_food_order_center.server.service;

import dp.home_food_order_center.server.error.GlobalServiceException;

import java.io.File;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
public interface IReportService {
    File getReceiptById(Long id) throws GlobalServiceException;
}
