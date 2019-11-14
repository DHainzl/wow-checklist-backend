package at.dhainzl.spring.wowchecklistbackend.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @Value("${app.return-urls}")
    private List<String> returnUrls;

    @GetMapping("/redirect")
    public void redirect(@RequestParam String returnUrl, HttpServletResponse response) throws IOException {
        validate(returnUrl);
        response.sendRedirect(returnUrl);
    }

    private void validate(String returnUrl) throws MalformedURLException {
        boolean isValid = this.returnUrls.stream().anyMatch(url -> returnUrl.startsWith(url));

        if (!isValid) {
            throw new MalformedURLException("URL " + returnUrl + " not in the list of allowed return urls!");
        }
    }
}