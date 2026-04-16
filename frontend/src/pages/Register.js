import { useState } from "react";
import { register } from "../api/auth";
import "../styles/auth.css";
import { useNavigate } from "react-router-dom";

export default function Register() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            await register(email, password);
            alert("Zarejestrowano!");
            navigate("/login");
        } catch {
            alert("Błąd rejestracji");
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>Rejestracja</h2>

                <input
                    type="email"
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Hasło"
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button onClick={handleRegister}>Zarejestruj</button>

                <span className="switch-link" onClick={() => navigate("/login")}>
          Masz konto? Zaloguj się
        </span>
            </div>
        </div>
    );
}