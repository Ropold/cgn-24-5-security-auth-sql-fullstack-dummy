package um.backend.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.backend.AppUser;
import um.backend.AppUserService;

@RestController
@RequestMapping("/api/users/me")
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public AppUser getCurrentUser(@AuthenticationPrincipal OAuth2User user) {
        System.out.println(user);
        return appUserService.getUserById(user.getName());
    }
}
