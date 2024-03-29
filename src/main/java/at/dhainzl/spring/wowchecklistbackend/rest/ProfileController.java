package at.dhainzl.spring.wowchecklistbackend.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.configuration.BattleNetRegionEditor;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow.ProfileService;

@RestController
@RequestMapping(path = "/api/{region}/{realm}/{name}/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping(path = "")
    public Object getProfile(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getProfile(region, realm, name);
    }

    @GetMapping(path = "character-media")
    public Object getCharacterMedia(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getCharacterMedia(region, realm, name);
    }

    @GetMapping(path = "equipment")
    public Object getEquipment(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getEquipment(region, realm, name);
    }

    @GetMapping(path = "achievements")
    public Object getAchievements(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getAchievements(region, realm, name);
    }

    @GetMapping(path = "reputations")
    public Object getReputations(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getReputations(region, realm, name);
    }

    @GetMapping(path = "quests/completed")
    public Object getCompletedQuests(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getCompletedQuests(region, realm, name);
    }

    @GetMapping(path = "professions")
    public Object getProfessions(
        @PathVariable BattleNetRegion region,
        @PathVariable String realm,
        @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getProfessions(region, realm, name);
    }

    @GetMapping(path = "collections/pets")
    public Object getPets(
            @PathVariable BattleNetRegion region,
            @PathVariable String realm,
            @PathVariable String name
    ) throws URISyntaxException {
        return this.profileService.getPets(region, realm, name);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BattleNetRegion.class, new BattleNetRegionEditor());
    }
}