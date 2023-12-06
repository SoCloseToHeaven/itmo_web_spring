import React from "react";
import {useUserDetailsStore} from "../data/user/store/UserDetails";
import {useNavigate} from "react-router-dom";

export interface AuthorizedOnlyProps {
    child: React.ReactNode
}

export default function AuthorizedOnly({ child } : AuthorizedOnlyProps) {
    const navigate = useNavigate();

    const authState = useUserDetailsStore.getState();
    const isAuthorized = (authState.user !== undefined) && (authState.token !== undefined);

    if (!isAuthorized) {
        navigate("/auth");
    }


    return (
        <>
            {child}
        </>
    );
}