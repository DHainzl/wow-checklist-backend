package at.dhainzl.spring.wowchecklistbackend.services.battlenet.configuration;

import java.beans.PropertyEditorSupport;

import at.dhainzl.spring.wowchecklistbackend.services.battlenet.BattleNetBaseService.BattleNetRegion;

public class BattleNetRegionEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String region){
        setValue(BattleNetRegion.valueOf(region.toUpperCase()));
    }
}