import {Todo} from "./Todo.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import TodoColumn from "./TodoColumn.tsx";
import {allPossibleTodos} from "./TodoStatus.ts";

function App() {

    const [todos, setTodos] = useState<Todo[]>()
    const [user, setUser] = useState<string>()

    function fetchTodos() {
        axios.get("/api/todo")
            .then(response => {
                setTodos(response.data)
            })
    }

    const login = () => {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    const logout = () => {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/logout', '_self')
    }

    const loadCurrentUser = () => {
        axios.get("/api/users/me")
            .then((response) => {
                setUser(response.data)
            })
    }

    useEffect(fetchTodos, [])
    useEffect(() => {
        loadCurrentUser()
    }, []);

    if (!todos) {
        return "Lade..."
    }

    return (
        <>
        {!user && <button onClick={login}>Login</button>}
            <p>{user}</p>
        {user && <button onClick={logout}>Logout</button>}
            <div className="page">
                <h1>My TODO App</h1>
                {
                    allPossibleTodos.map(status => {
                        const filteredTodos = todos.filter(todo => todo.status === status)
                        return <TodoColumn
                            status={status}
                            todos={filteredTodos}
                            onTodoItemChange={fetchTodos}
                            key={status}
                        />
                    })
                }
            </div>
        </>
    )
}

export default App
