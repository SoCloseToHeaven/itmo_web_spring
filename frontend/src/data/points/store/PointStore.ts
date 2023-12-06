import {create} from "zustand";
import {ProcessedPoint} from "../Points";

export interface ProcessedPointStore {
    points: Array<ProcessedPoint>
}


export const useProcessedPointStore = create<ProcessedPointStore>((set) => ({
    points: []
}));