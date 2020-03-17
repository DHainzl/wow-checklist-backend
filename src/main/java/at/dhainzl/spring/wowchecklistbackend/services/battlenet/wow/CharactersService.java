package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.net.URISyntaxException;
import java.text.MessageFormat;

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
        String url = this.getBaseUrl(region);
        return this.baseService.getFromApi(url, Object.class);
    }
    
    public String getBaseUrl(BattleNetRegion region) {
        return MessageFormat.format("{0}/profile/user/wow?namespace=profile-{1}&locale=en_US", baseService.getBaseUrl(region), region.toString().toLowerCase());
    }
}