package um.backend;

public record AppUserProfile(
        String id,
        String username,
        String imgUrl,
        String role
) {
}
