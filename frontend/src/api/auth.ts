import axios, {AxiosResponse} from "axios";
import {create} from "zustand";

const LOGIN_URI = "/auth/login"
const SIGNUP_URI = "/auth/signup"


export interface User{
    username: string,
    password: string
}

export interface UserInfo {
    id: number,
    username: string
}


export interface UserDetails {
    token: string | undefined,
    user: UserInfo | undefined
}

export const useUserDetailsStore = create<UserDetails>((set) => ({
    token: undefined,
    user: undefined
}));


export function login(user: User, onSuccess: () => void, onFail: (message: String) => void) {
    axios(LOGIN_URI, {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json',
            withCredentials: true
        },
        data: JSON.stringify(user)
    }).then((res: AxiosResponse<UserDetails>) => {
        const userDetails = res.data;
        useUserDetailsStore.setState(userDetails);
        onSuccess();
    }).catch(error => {
        onFail(error.message);
    })
}


// yes, actually this is copy-paste function because register request is basically a login request
export function signup(user: User, onSuccess: () => void, onFail: (message: String) => void) {
    axios(SIGNUP_URI, {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json',
            withCredentials: true
        },
        data: JSON.stringify(user)
    }).then((res: AxiosResponse<UserDetails>) => {
        const userDetails = res.data;
        useUserDetailsStore.setState(userDetails);
        onSuccess();
    }).catch(error => {
        onFail(error.message);
    })
}


export function logout(after: () => void) {
    useUserDetailsStore.setState((set) => ({
        token: undefined,
        user: undefined
    }));
    after();
}