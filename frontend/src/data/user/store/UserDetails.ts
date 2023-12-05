import {create} from "zustand";
import {UserInfo} from "../User";

export interface UserDetails {
    token?: string,
    user?: UserInfo
}

export const useUserDetailsStore = create<UserDetails>((set) => ({
    token: undefined,
    user: undefined
}));



export const bearerToken = () => `Bearer ${useUserDetailsStore.getState().token}`;