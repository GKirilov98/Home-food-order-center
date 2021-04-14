package dp.home_food_order_center.server.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
@Component
public class JsonUtils {

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger logger = LogManager.getLogger(JsonUtils.class);

    /**
     * Creates Json from POJO
     *
     * @param object pojo
     * @return string json
     */
    public String convertObjectToJson(Object object) {
        String json;
        try {

            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error occurred while parsing error to json", e);
            json = "\"msg\": \"Error occurred while parsing error to json\"";
        }
        return json;
    }
}
