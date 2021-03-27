package dp.home_food_order_center.controller;

import dp.home_food_order_center.error.GlobalServiceException;
import dp.home_food_order_center.error.model.ErrorModel;
import dp.home_food_order_center.error.ErrorFormatterUtils;
import dp.home_food_order_center.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 4:52 PM
 */
@Controller
public abstract class BaseController {
    private final Logger logger = LogManager.getLogger(BaseController.class);

    protected final int INTERNAL_SERVER_ERROR_CODE = 500;

    @Autowired
    private ErrorFormatterUtils errorFormatterUtils;
    @Autowired
    private JsonUtil jsonUtils;


    @ExceptionHandler(value = GlobalServiceException.class)
    public void handle(GlobalServiceException ex, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setStatus(INTERNAL_SERVER_ERROR_CODE);
            String uuid = ex.getLogId();
            ErrorModel errorModel = errorFormatterUtils.formatExceptionToErrorModel(ex, request.getRequestURI(), uuid);
            errorModel.setMessage(ex.getBgMessage());
            String responseJson = jsonUtils.convertObjectToJson(errorModel);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().write(responseJson.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.error(String.format("Error while writing response error : %s", e.getMessage()), e);
        }
    }
}
