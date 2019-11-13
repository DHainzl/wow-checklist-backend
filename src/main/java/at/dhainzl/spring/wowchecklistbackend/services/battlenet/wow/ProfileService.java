package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;

@Controller
public class ProfileService {
    @Autowired
    private BattleNetBaseService baseService;

    // TODO Return better object
    public Object getProfile(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.empty());
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getCharacterMedia(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("character-media"));
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getEquipment(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("equipment"));
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getAchievements(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("achievements"));
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getReputations(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("reputations"));
        return this.baseService.getFromApi(url, Object.class);
    }

    private String getProfileSubUrl(BattleNetRegion region, String realm, String name, Optional<String> resource) {
        String baseUrl = this.baseService.getBaseUrl(region);
        String profileUrl = MessageFormat.format("{0}/profile/wow/character/{1}/{2}", baseUrl, realm, name);

        if (resource.isPresent()) {
            profileUrl += "/" + resource.get();
        }

        profileUrl += MessageFormat.format("?namespace=profile-{0}&locale=en_US", region.toString().toLowerCase());

        return profileUrl;
    }
}