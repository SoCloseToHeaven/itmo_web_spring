import axios, {AxiosResponse} from "axios";
import {POINT_URI} from "./constants";
import {bearerToken} from "../data/user/store/UserDetails";
import {useProcessedPointStore} from "../data/points/store/PointStore";
import {Point, ProcessedPoint} from "../data/points/Points";

export function sendPoint(point: Point) {
    axios(POINT_URI, {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-type': 'application/json',
            Authorization: bearerToken()
        },
        data: JSON.stringify(point)
    }).then((res: AxiosResponse<ProcessedPoint>) => {
        const point = res.data;
        useProcessedPointStore.getState().add(point);
    }).catch(err => console.log(err.message));
}

export function getAllPoints(currentR: number) {
    axios(POINT_URI + `/${currentR}`, {
        method: "GET",
        headers: {
            'Accept': 'application/json, text/plain',
            Authorization: bearerToken()
        }
    }).then((res: AxiosResponse<Array<ProcessedPoint>>) => {
        useProcessedPointStore.getState().points = res.data;
    }).catch(err => console.log(err.message));
}

export function clearPoints() {
    axios(POINT_URI, {
        method: "DELETE",
        headers: {
            Authorization: bearerToken()
        }
    }).then((res) => {
        useProcessedPointStore.getState().clear();
    }).catch(err => console.log(err.message));
}
