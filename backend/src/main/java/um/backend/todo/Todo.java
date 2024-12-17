package um.backend.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "todo")
@Data
public class Todo {
    @Id
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TodoStatus status;

    public Todo() {
    }

    public Todo(
            String id,
            String description,
            TodoStatus status
    ) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    Todo(
            String description,
            TodoStatus status
    ) {
        this(null, description, status);
    }


    public Todo withId(String id) {
        return new Todo(id, description, status);
    }
}
