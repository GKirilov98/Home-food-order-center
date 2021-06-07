package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.service.IReportService;
import dp.home_food_order_center.server.utils.FileToHttpEntity;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 6/4/2021
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Method for report receipt
     * @param id
     * @return
     */
    @GetMapping("/receipt/{id}")
    public HttpEntity getReceiptById(@PathVariable Long id) throws GlobalServiceException {
        File file = reportService.getReceiptById(id);
      return   FileToHttpEntity.convert(file);
    }
}
