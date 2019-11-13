package at.dhainzl.spring.wowchecklistbackend.services.battlenet;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import at.dhainzl.spring.wowchecklistbackend.configuration.authentication.IAuthenticationFacade;

@Controller
public class BattleNetBaseService {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

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
        // A higher buffer size (here 2MB) is needed to cache the achievements result, especially
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 2048))
            .build();

        return WebClient.builder()
            .exchangeStrategies(exchangeStrategies)
            .build()
            .get()
            .uri(new URI(url))
            .header("Authorization", "Bearer " + authenticationFacade.getAuthenticationToken())
            .exchange()
            .block()
            .bodyToMono(resultClass)
            .block();
    }
}