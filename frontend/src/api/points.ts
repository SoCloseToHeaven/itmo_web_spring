import {create} from "zustand";
import axios, {AxiosResponse} from "axios";

const POINT_URI = "/points"

export interface Point {
    x: number,
    y: number,
    r: number
}

export interface ProcessedPoint extends Point {
    hit: boolean,
    createdAt: string,
    processingTime: number
}

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

export function sendPoint(point: Point) {
    axios(POINT_URI, { // TODO: add jwt token
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json'
        },
        data: JSON.stringify(point)
    }).then((res: AxiosResponse<ProcessedPoint>) => {
        const point = res.data;
        useProcessedPointStore.getState().add(point);
    }).catch(err => console.log(err.message));
}

export function getAllPoints(currentR: number) {
    axios(POINT_URI + `/${currentR}`, { // TODO: add jwt token
        method: "GET",
        headers: {
            'Accept': 'application/json, text/plain'
        }
    }).then((res: AxiosResponse<Array<ProcessedPoint>>) => {
        useProcessedPointStore.getState().points = res.data;
    }).catch(err => console.log(err.message));
}

export function clearPoints() {
    axios(POINT_URI, { // TODO: add jwt token
        method: "DELETE"
    }).then((res) => {
        useProcessedPointStore.getState().clear();
    }).catch(err => console.log(err.message));
}
