package um.backend;

public record AppUser(
        String id,
        String username,
        String imgUrl,
        String role
) {
}
