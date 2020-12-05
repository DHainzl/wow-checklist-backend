package at.dhainzl.spring.wowchecklistbackend.model.userinfo;

import java.util.Map;

public class UserInfoResponse {
    public String battletag = "";

    public static UserInfoResponse fromAuthDetails(Object authDetails) {
        UserInfoResponse userInfo = new UserInfoResponse();

        if (!(authDetails instanceof Map)) {
            return userInfo;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> authDetailsMap = (Map<String, Object>) authDetails;
        userInfo.battletag = (String) authDetailsMap.getOrDefault("battle_tag", "");

        return userInfo;
    }
}