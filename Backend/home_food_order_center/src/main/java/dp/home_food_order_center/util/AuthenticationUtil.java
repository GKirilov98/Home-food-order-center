package dp.home_food_order_center.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationUtil {
    public String getLogId() {
        return UUID.randomUUID().toString();
    }
}
