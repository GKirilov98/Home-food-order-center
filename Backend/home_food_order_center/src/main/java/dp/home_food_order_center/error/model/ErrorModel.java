package dp.home_food_order_center.error.model;

import java.sql.Timestamp;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/26/2021 5:13 PM
 */
public class ErrorModel {

    private String uuid;
    private String message;
    private String systemMessage;
    private String path;
    private Timestamp timestamp;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}