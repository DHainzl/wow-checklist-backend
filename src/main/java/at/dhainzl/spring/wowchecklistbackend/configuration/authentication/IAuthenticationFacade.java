package at.dhainzl.spring.wowchecklistbackend.configuration.authentication;

import at.dhainzl.spring.wowchecklistbackend.model.userinfo.UserInfoResponse;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    Optional<OAuth2AuthenticationToken> getOAuth2Authentication();
    Optional<UserInfoResponse> getAuthAsUserInfo();
}