package at.dhainzl.spring.wowchecklistbackend.configuration.authentication;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    String getAuthenticationToken();
}