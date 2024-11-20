package um.backend;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getUserById(String id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + id + " does not exist!"));
    }

    public AppUser addUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
