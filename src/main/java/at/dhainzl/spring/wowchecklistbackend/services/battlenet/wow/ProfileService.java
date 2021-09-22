package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    // TODO Return better object
    public Object getCompletedQuests(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("quests/completed"));
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getProfessions(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("professions"));
        return this.baseService.getFromApi(url, Object.class);
    }

    // TODO Return better object
    public Object getPets(BattleNetRegion region, String realm, String name) throws URISyntaxException {
        String url = this.getProfileSubUrl(region, realm, name, Optional.of("collections/pets"));
        return this.baseService.getFromApi(url, Object.class);
    }

    private String getProfileSubUrl(BattleNetRegion region, String realm, String name, Optional<String> resource) {
        String baseUrl = this.baseService.getBaseUrl(region);
        String profileUrl;
        try {
            profileUrl = MessageFormat.format("{0}/profile/wow/character/{1}/{2}", baseUrl,
                    URLEncoder.encode(realm, StandardCharsets.UTF_8.toString()),
                    URLEncoder.encode(name, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 Charset not supported", e);
        }

        if (resource.isPresent()) {
            profileUrl += "/" + resource.get();
        }

        profileUrl += MessageFormat.format("?namespace=profile-{0}&locale=en_US", region.toString().toLowerCase());

        return profileUrl;
    }
}