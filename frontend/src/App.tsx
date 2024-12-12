import {Todo} from "./Todo.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import {Link, Route, Routes} from "react-router-dom";
import HomePage from "./HomePage.tsx";
import NewTodoCard from "./NewTodoCard.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";

function App() {

    const [todos, setTodos] = useState<Todo[]>([])
    const [user, setUser] = useState<{ id: string } | undefined>()
    const [loginData, setLoginData] = useState<{ username: string, password: string }>({username: "", password: ""})

    function fetchTodos() {
        axios.get("/api/todo")
            .then(response => {
                setTodos(response.data)
            })
    }

    const login = () => {
        axios.get("/api/users/me", {
            auth: {
                username: loginData.username,
                password: loginData.password
            }
        })
            .then((response) => {
                setUser(response.data)
            })
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
            <input value={loginData.username}
                   placeholder={"Username"}
                   onChange={(e) => setLoginData(prevState => ({...prevState, username: e.target.value}))}/>
            <input value={loginData.password}
                   placeholder={"Password"}
                   onChange={(e) => setLoginData(prevState => ({...prevState, password: e.target.value}))}/>
            {!user && <button onClick={login}>Login</button>}
            <p>{user?.id}</p>
            {user && <button onClick={logout}>Logout</button>}
            <Link to={"/"}>Home</Link>
            <Link to={"/new"}>New</Link>
            <Link to={"/admin"}>Admin</Link>
            <div className="page">
                <h1>My TODO App</h1>
                <Routes>
                    <Route path="/"
                           element={<HomePage todos={todos}
                                              fetchTodos={fetchTodos}/>}/>

                    <Route element={<ProtectedRoute user={user}/>}>

                        <Route path={"/new"}
                               element={<NewTodoCard onTodoItemChange={fetchTodos}/>}/>

                        <Route path={"/admin"}
                               element={<NewTodoCard onTodoItemChange={fetchTodos}/>}/>

                    </Route>

                </Routes>
            </div>
        </>
    )
}

export default App
