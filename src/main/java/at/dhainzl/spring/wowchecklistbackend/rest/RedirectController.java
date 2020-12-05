package at.dhainzl.spring.wowchecklistbackend.rest;

import at.dhainzl.spring.wowchecklistbackend.configuration.authentication.IAuthenticationFacade;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @Value("${app.return-urls}")
    private List<String> returnUrls;

    @Autowired
    private IAuthenticationFacade authentication;

    @GetMapping("/redirect")
    public void redirect(@RequestParam String returnUrl, HttpServletResponse response, HttpSession session) throws IOException {
        validate(returnUrl);
        if (authentication.getOAuth2Authentication().isPresent()) {
            response.sendRedirect(returnUrl);
        } else {
            session.setAttribute("redirectReturnUrl", returnUrl);
            response.sendRedirect("/oauth2/authorization/battlenet");
        }

    }

    public void validate(String returnUrl) throws MalformedURLException {
        boolean isValid = this.returnUrls.stream().anyMatch(url -> returnUrl.startsWith(url));

        if (!isValid) {
            throw new MalformedURLException("URL " + returnUrl + " not in the list of allowed return urls!");
        }
    }
}