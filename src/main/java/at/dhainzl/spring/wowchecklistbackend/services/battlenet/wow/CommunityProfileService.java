package at.dhainzl.spring.wowchecklistbackend.services.battlenet.wow;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.codec.Charsets;
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
    public Object getProfileData(BattleNetRegion region, String realm, String name, List<String> fields)
            throws URISyntaxException {
        String baseUrl = baseService.getBaseUrl(region);
        String fieldsString = String.join(",", fields);
        String url;
        try {
            url = MessageFormat.format("{0}/wow/character/{1}/{2}?locale=en_US&fields={3}", baseUrl,
                    URLEncoder.encode(realm, Charsets.UTF_8.toString()),
                    URLEncoder.encode(name, Charsets.UTF_8.toString()), fieldsString);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 Charset not supported", e);
        }

        return this.baseService.getFromApi(url, Object.class);
    }
}