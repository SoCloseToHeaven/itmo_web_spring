import {Button, ButtonGroup, Form, InputGroup} from "react-bootstrap";
import React, {useRef, useState} from "react";
import {login, signup} from "../../../api/auth";

interface AuthorizationFormProps {
    onSuccess: () => void,
    onFail: (message: string) => void
}
export default function AuthorizationForm(
    { onSuccess, onFail } : AuthorizationFormProps
){

    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    return (
        <div className="text-center container">
            <Form.Label className="fs-2 fw-bold mb-3 mt-3">Authorization</Form.Label>
            <InputGroup className="mb-3 mt-3 d-block">
                <div>
                    <Form.Label className="fs-3" htmlFor="username-input">Username</Form.Label>
                </div>
                <div className="w-100 row justify-content-center">
                    <Form.Control
                        className="w-75"
                        id="username-input"
                        onChange={e => setUsername(e.target.value)}
                        required={true}
                        placeholder="Username"
                        aria-describedby="usernameHelpBlock"
                    />
                </div>
                <div>
                    <Form.Text
                        id="usernameHelpBlock"
                        className="fs-6"
                    >
                        Username must be at least 4 symbols long
                    </Form.Text>
                </div>
            </InputGroup>
            <InputGroup className="mb-3 mt-3 d-block">
                <div>
                    <Form.Label
                        className="fs-3"
                        htmlFor="password-input"
                    >
                        Password
                    </Form.Label>
                </div>
                <div className="w-100 row justify-content-center">
                    <Form.Control
                        className="w-75"
                        onChange={e => setPassword(e.target.value)}
                        required={true}
                        type="password"
                        id="password-input"
                        aria-describedby="passwordHelpBlock"
                    />
                </div>
                <div>
                    <Form.Text
                        id="passwordHelpBlock"
                        className="fs-6"
                    >
                        Password must be at least 7-20 characters long
                    </Form.Text>
                </div>
            </InputGroup>
            <ButtonGroup className="mb-3 mt-3 d-block text-center">
                <Button
                    type="button"
                    className="m-3 d-inline btn btn-primary btn-lg rounded"
                    onClick={(e) => {
                        login({username, password}, onSuccess, onFail);
                    }
                }
                >Login
                </Button>

                <Button
                    type="button"
                    className="m-3 d-inline btn btn-primary btn-lg rounded"
                    onClick={(e) => {
                        signup({username, password}, onSuccess, onFail);
                    }
                }
                >Sign up
                </Button>
            </ButtonGroup>
        </div>
    );
}