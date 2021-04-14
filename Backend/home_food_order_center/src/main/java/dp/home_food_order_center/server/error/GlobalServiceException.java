package dp.home_food_order_center.server.error;

public class GlobalServiceException extends Exception{
    private String logId;
    private String bgMessage;

    public GlobalServiceException(String logId, String bgMessage, String message, Throwable cause) {
        super(message, cause);
        this.bgMessage = bgMessage;
        this.logId = logId;
    }

    public GlobalServiceException(String logId, String bgMessage, String message) {
        super(message);
        this.bgMessage = bgMessage;
        this.logId = logId;
    }

    public String getBgMessage() {
        return bgMessage;
    }

    public String getLogId() {
        return logId;
    }
}
