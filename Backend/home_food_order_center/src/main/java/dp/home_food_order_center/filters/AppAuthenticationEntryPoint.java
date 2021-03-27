package dp.home_food_order_center.filters;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/27/2021 7:48 PM
 */
@Component
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /**
     * The method looks into the exception thrown from Login into the system and throws the correct exception
     * @param httpServletRequest request
     * @param httpServletResponse response
     * @param ex instance of the login exception
     * @throws IOException connection exception
     * @throws ServletException the correct exception
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException ex) throws IOException, ServletException {

        if (ex instanceof BadCredentialsException) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "BadCredentialsException");
            return;
        } else if (ex instanceof CredentialsExpiredException) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "CredentialsExpiredException");
            return;
        } else if (ex instanceof DisabledException) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "DisabledException");
            return;
        } else if (ex instanceof LockedException) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "LockedException");
            return;
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
