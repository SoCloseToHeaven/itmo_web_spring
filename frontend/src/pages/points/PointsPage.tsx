import React from "react";
import {Graph} from "./components/Graph";
import {useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";
import {logout} from "../../api/auth";


export default function PointsPage() {
    const navigate = useNavigate();
    const afterLogout = () => navigate("/auth");

    return (
        <div>
            <div>
                <Button
                    onClick={() => logout(afterLogout)}
                >
                    Logout
                </Button>
            </div>
            <div>
                <Graph />
            </div>
        </div>
    );
}