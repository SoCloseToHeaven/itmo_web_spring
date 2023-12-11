import AuthorizationForm from "./components/AuthorizationForm";
import {PersonalCredentials} from "./components/PersonalCredentials";
import React, {useState} from "react";
import {Alert} from "react-bootstrap";
import {useNavigate} from 'react-router-dom';


export default function AuthorizationPage() {
    const [failText, setFailText] = useState<string>("");

    const navigate = useNavigate();
    const onSuccess = () => navigate("/");

    const onFail = (message: string) => setFailText(message);

    return (
        <div className="text-center">
            <div className="mb-3 mt-3" id="credits-container">
                <PersonalCredentials />
            </div>
            <div
                className="mb-3 mt-3 w-50 rounded border border-3 shadow-lg container gap-3 text-center"
                id="auth-container"
            >
                <AuthorizationForm onSuccess={onSuccess} onFail={onFail} />
            </div>
            <div
                className="text-center container mt-3 mb-3"
            >
                <Alert
                    variant="danger"
                    key="danger"
                    hidden={failText.length === 0}
                >
                    <Alert.Heading>
                        Unable to auth user!
                    </Alert.Heading>
                    {failText}
                </Alert>
            </div>
        </div>
    );
}