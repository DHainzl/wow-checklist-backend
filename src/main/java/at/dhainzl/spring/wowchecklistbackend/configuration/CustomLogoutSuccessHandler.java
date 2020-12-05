package at.dhainzl.spring.wowchecklistbackend.configuration;

import at.dhainzl.spring.wowchecklistbackend.Util;
import at.dhainzl.spring.wowchecklistbackend.rest.RedirectController;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;

@Controller
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedirectController redirectController;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String returnUrl = request.getParameter("returnUrl");

        if (Util.isNullOrEmpty(returnUrl)) {
            return;
        }

        try {
            this.redirectController.validate(returnUrl);
            response.sendRedirect(returnUrl);
        } catch (MalformedURLException e) {
            // No redirect, but we can still logout after all
        }
    }
}
