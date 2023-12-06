import {useProcessedPointStore} from "../../../data/points/store/PointStore";
import React from "react";
import Table from 'react-bootstrap/Table';


export default function ResultsTable() {
    const points = useProcessedPointStore(state => state.points);

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>hit</th>
                    <th>Creation date</th>
                    <th>Processing time (ns)</th>
                </tr>
            </thead>
            <tbody>
            {
                points.map((point) => {
                    return (
                        <tr>
                            <td>{point.x}</td>
                            <td>{point.y}</td>
                            <td>{point.r}</td>
                            <td>{point.hit}</td>
                            <td>{point.createdAt}</td>
                            <td>{point.processingTime}</td>
                        </tr>
                    );
                })
            }
            </tbody>
        </Table>
    );
}