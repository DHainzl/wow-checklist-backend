package at.dhainzl.spring.oauthbattlenet.configuration.authentication;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    String getAuthenticationToken();
}