import axios, {AxiosResponse} from "axios";
import {create} from "zustand";
import {LOGIN_URI, SIGNUP_URI} from "./constants";
import {UserDetails, useUserDetailsStore} from "../data/user/store/UserDetails";
import {User} from "../data/user/User";


export function login(user: User, onSuccess: () => void, onFail: (message: string) => void) {
    axios(LOGIN_URI, {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json',
            'Access-Control-Allow-Origin' : '*',
            'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
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
export function signup(user: User, onSuccess: () => void, onFail: (message: string) => void) {
    axios(SIGNUP_URI, {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json'
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