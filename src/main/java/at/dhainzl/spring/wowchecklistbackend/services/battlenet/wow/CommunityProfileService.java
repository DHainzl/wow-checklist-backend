package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;

@Controller
public class CommunityProfileService {
    @Autowired
    private BattleNetBaseService baseService;

    // TODO Return better object
    /**
     * @deprecated Use the methods of the ProfileService if possible.
     */
    @Deprecated
    public Object getProfileData(BattleNetRegion region, String realm, String name, List<String> fields) throws URISyntaxException {
        String baseUrl = baseService.getBaseUrl(region);
        String fieldsString = String.join(",", fields);
        String url = MessageFormat.format("{0}/wow/character/{1}/{2}?locale=en_US&fields={3}", baseUrl, realm, name, fieldsString);

        return this.baseService.getFromApi(url, Object.class);
    }
}