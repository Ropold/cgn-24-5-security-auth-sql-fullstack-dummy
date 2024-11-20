package um.backend.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    List<Todo> getAll() {
        return todoService.getAll();
    }

    @PostMapping
    Todo postTodo(@RequestBody TodoDto todo) {
        return todoService.save(new Todo(todo.description(), todo.status()));
    }

    @GetMapping("{id}")
    Todo getTodoById(@PathVariable String id) {
        return todoService.getById(id);
    }

    @PutMapping(path = {"{id}/update", "{id}"})
    Todo update(@PathVariable String id, @RequestBody TodoDto todo) {
        return todoService.update(new Todo(id, todo.description(), todo.status()));
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable String id) {
        todoService.delete(id);
    }
 }





