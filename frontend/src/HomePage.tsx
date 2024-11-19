import {allPossibleTodos} from "./TodoStatus.ts";
import TodoColumn from "./TodoColumn.tsx";
import {Todo} from "./Todo.ts";

type HomePageProps = {
    todos: Todo[]
    fetchTodos: () => void
}

export default function HomePage(props: HomePageProps) {

    return (
        <>
            {
                allPossibleTodos.map(status => {
                    const filteredTodos = props.todos.filter(todo => todo.status === status)
                    return <TodoColumn
                        status={status}
                        todos={filteredTodos}
                        onTodoItemChange={props.fetchTodos}
                        key={status}
                    />
                })
            }
        </>
    )
}
