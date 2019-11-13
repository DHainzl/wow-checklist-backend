package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;

@Controller
public class CharactersService {
    @Autowired
    private BattleNetBaseService baseService;

    // TODO Return better object
    public Object getAllCharacters(BattleNetRegion region) throws URISyntaxException {
        String url = baseService.getBaseUrl(region) + "/wow/user/characters";
        return this.baseService.getFromApi(url, Object.class);
    }
}