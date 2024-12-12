package um.backend;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser getUserById(String id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + id + " does not exist!"));
    }

    public AppUser addUser(AppUserRegisterDto appUserRegisterDto) {
        AppUser appUser = new AppUser(null, appUserRegisterDto.username(), passwordEncoder.encode(appUserRegisterDto.password()), null, "USER");
        return appUserRepository.save(appUser);
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username).orElseThrow();
    }
}
