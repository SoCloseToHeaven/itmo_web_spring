import {create} from "zustand";
import {UserInfo} from "../User";

export interface UserDetails {
    token?: string,
    user?: UserInfo
    isAuthorized: () => boolean
}

export const useUserDetailsStore = create<UserDetails>((set) => ({
    token: undefined,
    user: undefined,
    isAuthorized: function () {
        return (this.user !== undefined) && (this.token !== undefined);
    }
}));



export const bearerToken = () => `Bearer ${useUserDetailsStore.getState().token}`;