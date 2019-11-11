package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import at.dhainzl.spring.wowchecklistbackend.configuration.authentication.IAuthenticationFacade;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;

@Controller
public class CharactersService {
    @Autowired
    private BattleNetBaseService baseService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    // TODO Return better object
    public Object getAllCharacters(BattleNetRegion region) throws URISyntaxException {
        String url = baseService.getBaseUrl(region) + "/wow/user/characters";
        return WebClient.create()
            .get()
            .uri(new URI(url))
            .header("Authorization", "Bearer " + authenticationFacade.getAuthenticationToken())
            .exchange()
            .block()
            .bodyToMono(Object.class)
            .block();
    }
}