package um.backend;

public record AppUser(
        String id,
        String username,
        String password,
        String imgUrl,
        String role
) {
}
