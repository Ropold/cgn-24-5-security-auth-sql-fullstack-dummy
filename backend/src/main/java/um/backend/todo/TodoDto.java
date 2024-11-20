package um.backend.todo;

public record TodoDto(
        String description,
        TodoStatus status
) {
}
