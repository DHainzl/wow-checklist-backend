package at.dhainzl.spring.wowchecklistbackend.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.configuration.BattleNetRegionEditor;
import at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow.CharactersService;

@RestController
public class CharacterListController {

    @Autowired
    private CharactersService charactersService;

    @GetMapping(path = "/characters/{region}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object characterList(@PathVariable BattleNetRegion region) throws URISyntaxException {
        return this.charactersService.getAllCharacters(region);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BattleNetRegion.class, new BattleNetRegionEditor());
    }
}