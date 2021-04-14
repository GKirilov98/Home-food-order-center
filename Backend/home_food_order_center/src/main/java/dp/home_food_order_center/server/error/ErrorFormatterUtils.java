package dp.home_food_order_center.server.error;

import dp.home_food_order_center.server.error.model.ErrorModel;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 5:00 PM
 */
@Component
public class ErrorFormatterUtils {
    public ErrorModel formatExceptionToErrorModel(Exception e, String path, String uuid) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(e.getMessage());
        errorModel.setSystemMessage(e.getMessage());
        errorModel.setPath(path);
        errorModel.setUuid(uuid);
        errorModel.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return errorModel;
    }
}