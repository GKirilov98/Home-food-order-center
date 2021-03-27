package dp.home_food_order_center.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 4:23 PM
 */
@Component
public class JsonUtil {
    private final Logger logger = LogManager.getLogger(JsonUtil.class);
    @Autowired
    private ObjectMapper objectMapper;


    public String convertObjectToJson(Object object){
        String json;
        try{
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error occurred while parsing error to json", e);
            json = "\"msg\": \"Error occurred while parsing error to json\"";
        }
        return json;
    }
}
