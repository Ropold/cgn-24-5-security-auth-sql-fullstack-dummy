package um.backend.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import um.backend.AppUser;
import um.backend.AppUserRegisterDto;
import um.backend.AppUserProfile;
import um.backend.AppUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("me")
    public AppUserProfile getCurrentUser(@AuthenticationPrincipal User user) {
        System.out.println(user);
        AppUser appUser = appUserService.getUserByUsername(user.getUsername());
        return new AppUserProfile(appUser.id(), appUser.username(), appUser.imgUrl(), appUser.role());
    }

    @PostMapping
    public AppUserProfile register(@RequestBody AppUserRegisterDto appUserRegisterDto) {
        AppUser appUser = appUserService.addUser(appUserRegisterDto);
        return new AppUserProfile(appUser.id(), appUser.username(), appUser.imgUrl(), appUser.role());
    }
}
