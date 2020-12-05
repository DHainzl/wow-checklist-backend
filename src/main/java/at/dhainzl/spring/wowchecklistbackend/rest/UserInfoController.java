package at.dhainzl.spring.wowchecklistbackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.dhainzl.spring.wowchecklistbackend.configuration.authentication.IAuthenticationFacade;
import at.dhainzl.spring.wowchecklistbackend.model.userinfo.UserInfoResponse;

@RestController
public class UserInfoController {
    @Autowired
    private IAuthenticationFacade authentication;
    
    @GetMapping(path = "/api/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse getUserInfo() {
        return this.authentication.getAuthAsUserInfo().orElse(null);
    }
}