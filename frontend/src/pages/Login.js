import { useState } from "react";
import { login } from "../api/auth";
import "../styles/auth.css";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await login(email, password);
            alert("Zalogowano!");
            window.location.href = "/students";

        } catch {
            alert("Błąd logowania");
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>Logowanie</h2>

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Hasło"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button onClick={handleLogin}>Zaloguj</button>

                <span className="switch-link" onClick={() => navigate("/register")}>
          Nie masz konta? Zarejestruj się
        </span>
            </div>
        </div>
    );
}