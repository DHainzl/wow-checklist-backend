package at.dhainzl.spring.wowchecklistbackend.services.battlenet;

import org.springframework.stereotype.Controller;

@Controller
public class BattleNetBaseService {
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
}