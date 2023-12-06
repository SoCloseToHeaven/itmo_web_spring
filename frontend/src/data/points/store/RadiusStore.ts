import {create} from "zustand";

export interface RadiusStore {
    radius: number,
}

export const useRadiusStore = create<RadiusStore>((set) => ({
    radius: 1
}));