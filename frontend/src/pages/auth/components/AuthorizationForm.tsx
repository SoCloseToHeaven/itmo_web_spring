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
        <div className="text-center">
            <InputGroup className="mb-3">
                <div>
                    <Form.Label>Username</Form.Label>
                </div>
                <div>
                    <Form.Control
                        onChange={e => setUsername(e.target.value)}
                        required={true}
                        placeholder="Username"
                        aria-describedby="usernameHelpBlock"
                    />
                </div>
                <div>
                    <Form.Text id="usernameHelpBlock">Username must be at least 4 symbols long</Form.Text>
                </div>
            </InputGroup>
            <InputGroup className="mb-3">
                <div>
                    <Form.Label
                        htmlFor="password-input"
                    >
                        Password
                    </Form.Label>
                </div>
                <div>
                    <Form.Control
                        onChange={e => setPassword(e.target.value)}
                        required={true}
                        type="password"
                        id="password-input"
                        aria-describedby="passwordHelpBlock"
                    />
                </div>
                <div>
                    <Form.Text id="passwordHelpBlock" >
                        Password must be at least 7-20 characters long
                    </Form.Text>
                </div>
            </InputGroup>
            <ButtonGroup className="text-center">
                <Button
                    type="button"
                    className="m-3 d-inline"
                    onClick={(e) => {
                        login({username, password}, onSuccess, onFail);
                    }
                }
                >Login
                </Button>

                <Button
                    type="button"
                    className="m-3 d-inline"
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