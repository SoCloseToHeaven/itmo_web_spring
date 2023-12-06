import React, {useEffect, useState} from "react";
import Form from 'react-bootstrap/Form';
import {Button, FormCheck} from "react-bootstrap";
import {useRadiusStore} from "../../../data/points/store/RadiusStore";
import {clearPoints, getAllPoints, sendPoint} from "../../../api/points";

const X_VALUES = [-3, -2, -1, 0, 1, 2, 3, 4, 5];
const Y_VALUES = [-3, -2, -1, 0, 1, 2, 3, 4, 5];
const R_VALUES = [1, 2, 3, 4, 5];

export default function PointsForm() {
    const radius = useRadiusStore(state => state.radius);
    const setRadius = (value: number) => useRadiusStore.setState((state) => ({ radius: value }));

    const [x, setX] = useState<number>(0);
    const [y, setY] = useState<number>(0);


    useEffect(() => getAllPoints(radius), [radius]);

    return (
        <Form
            className="container text-center"
            onSubmit={event => {
                    event.preventDefault();
                    const pointAttempt = {
                        x: x,
                        y: y,
                        r: radius
                    };
                    sendPoint(pointAttempt);
                }
            }
        >
            <Form.Label className="fs-2 fw-bold">Enter point data</Form.Label>
            <Form.Group id="x-group">
                <Form.Label className="fs-3">Choose X value</Form.Label>
                <div className={"row justify-content-center"}>
                    {
                        X_VALUES.map((value) => {
                            return (
                                <div
                                    key={'default-checkbox'}
                                    className="m-3 col-sm"
                                >
                                    <FormCheck
                                        id={`x-check-${value}`}
                                        type="checkbox"
                                        className="m-3"
                                        onClick={(e) => setX(value)}
                                        value={value}
                                        checked={value === x}
                                        label={value}
                                    />
                                </div>
                            );
                        })
                    }
                </div>
            </Form.Group>
            <Form.Group
                id="y-group"
                className="w-100"
            >
                <Form.Label className="fs-3">Select Y value</Form.Label>
                <Form.Select
                    onChange={(e) => setY(parseFloat(e.target.value))}
                >
                    {
                        Y_VALUES.map((value) => {
                            return (
                                <option
                                    value={value}
                                >
                                    {value}
                                </option>
                            );
                        })
                    }
                </Form.Select>
            </Form.Group>
            <Form.Group
                id="r-group"
            >
                <Form.Label className="fs-3">Choose R value</Form.Label>
                <div className={"row justify-content-center"}>
                    {
                        R_VALUES.map((value) => {
                            return (
                                <div
                                    key={'default-checkbox'}
                                    className="m-3 col-sm"
                                >
                                    <FormCheck
                                        id={`r-check-${value}`}
                                        onClick={() => setRadius(value)}
                                        type="checkbox"
                                        value={value}
                                        label={value}
                                        checked={value === radius}
                                    />
                                </div>
                            );
                        })
                    }
                </div>
            </Form.Group>
            <Form.Group
                id="submit-clear-group"
            >
                <Button
                    type="submit"
                    className="m-3 btn btn-primary btn-lg"
                >
                    Submit
                </Button>
                <Button
                    type="button"
                    className="m-3 btn btn-primary btn-lg"
                    onClick={() => clearPoints()}
                >
                    Clear
                </Button>
            </Form.Group>
        </Form>
    );
}