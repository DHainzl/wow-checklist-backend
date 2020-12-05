package at.dhainzl.spring.wowchecklistbackend.configuration.authentication;

import at.dhainzl.spring.wowchecklistbackend.model.userinfo.UserInfoResponse;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Optional<OAuth2AuthenticationToken> getOAuth2Authentication() {
        Authentication auth = this.getAuthentication();
        if (!(auth instanceof OAuth2AuthenticationToken)) {
            return Optional.empty();
        }

        return Optional.of((OAuth2AuthenticationToken) auth);
    }

    public Optional<UserInfoResponse> getAuthAsUserInfo() {
        return this.getOAuth2Authentication()
                .map(auth -> auth.getPrincipal())
                .map(principal -> principal.getAttributes())
                .map(details -> UserInfoResponse.fromAuthDetails(details));
    }
}