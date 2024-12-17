package um.backend.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import um.backend.AppUser;
import um.backend.AppUserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AppUserService appUserService;

    public CustomOAuth2UserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User user = super.loadUser(userRequest);

        AppUser appUser;
        try {
            appUser = appUserService.getUserById(user.getName());
        } catch (NoSuchElementException exception) {
            appUser = appUserService.addUser(new AppUser(user.getName(),
                    user.getAttributes().get("login").toString(),
                    user.getAttributes().get("avatar_url").toString(),
                    "USER",  List.of()));
        }

        return new DefaultOAuth2User(AuthorityUtils.createAuthorityList(appUser.getRole()), user.getAttributes(),"id");
    }
}
