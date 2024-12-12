package um.backend;

public record AppUserRegisterDto(
        String username,
        String password,
        String imgUrl
) {
}
