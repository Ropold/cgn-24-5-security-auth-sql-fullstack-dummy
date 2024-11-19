import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    user: string | undefined
}

export default function ProtectedRoute(props: ProtectedRouteProps) {

    if (props.user === undefined) {
        return <Navigate to={"/"}/>
    } else {
        return <Outlet/>
    }
}
