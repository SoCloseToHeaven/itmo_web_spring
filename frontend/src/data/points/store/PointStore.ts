import {create} from "zustand";
import {ProcessedPoint} from "../Points";

export interface ProcessedPointStore {
    points: Array<ProcessedPoint>,
    clear: () => void
    add: (point: ProcessedPoint) => void
}


export const useProcessedPointStore = create<ProcessedPointStore>((set) => ({
    points: [],
    clear: () => set(() => ({ points: [] })),
    add: (point) => set(state => ({ points: [...state.points, point] }))
}));