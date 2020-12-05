package at.dhainzl.spring.wowchecklistbackend.services.battlenet;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import at.dhainzl.spring.wowchecklistbackend.configuration.authentication.IAuthenticationFacade;

@Controller
public class BattleNetBaseService {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    public enum BattleNetRegion {
        US, EU, KR, TW, CN
    }

    public String getBaseUrl(BattleNetRegion region) {
        if (BattleNetRegion.CN.equals(region)) {
            return "https://gateway.battlenet.com.cn";
        } else {
            return "https://" + region.toString().toLowerCase() + ".api.blizzard.com";
        }
    }

    public <T> T getFromApi(String url, Class<T> resultClass) throws URISyntaxException {
        OAuth2AuthenticationToken authToken = authenticationFacade.getOAuth2Authentication().orElseThrow();
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
        String token = client.getAccessToken().getTokenValue();

        // A higher buffer size (here 2MB) is needed to cache the achievements result, especially
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 2048))
            .build();

        return WebClient.builder()
            .exchangeStrategies(exchangeStrategies)
            .build()
            .get()
            .uri(new URI(url))
            .header("Authorization", "Bearer " + token)
            .exchange()
            .block()
            .bodyToMono(resultClass)
            .block();
    }
}