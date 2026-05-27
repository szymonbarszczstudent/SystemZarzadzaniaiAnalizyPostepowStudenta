import { NavLink, useNavigate } from "react-router-dom";
import "../styles/navbar.css";
import { getCurrentUser, logout } from "../api/auth";
import { useEffect, useState } from "react";

function Navbar() {
    const [user, setUser] = useState({ loggedIn: false });
    const navigate = useNavigate();

    useEffect(() => {
        getCurrentUser()
            .then(setUser)
            .catch(() => setUser({ loggedIn: false }));
    }, []);

    const handleLogout = async () => {
        await logout();
        setUser({ loggedIn: false });
        navigate("/login");
    };

    const fullName = [user.firstName, user.lastName]
        .filter(Boolean)
        .join(" ");

    return (
        <nav className="navbar">
            <div className="navbar-logo">
                System zarządzania i analizy postępów studenta
            </div>

            <div className="navbar-links">
                {user.loggedIn && (
                    <>
                        <NavLink className="navbar-link" to="/students">
                            Studenci
                        </NavLink>

                        <NavLink className="navbar-link" to="/professors">
                            Nauczyciele akademiccy
                        </NavLink>

                        <NavLink className="navbar-link" to="/subjects">
                            Przedmioty
                        </NavLink>

                        <NavLink className="navbar-link" to="/exams">
                            Egzaminy
                        </NavLink>

                        <NavLink className="navbar-link" to="/grades">
                            Oceny
                        </NavLink>

                        {user.role === "ADMIN" && (
                            <NavLink className="navbar-link" to="/users">
                                Użytkownicy
                            </NavLink>
                        )}

                        <span className="navbar-user">
                            {user.role}
                            {fullName && ` | ${fullName}`}
                        </span>

                        <button className="navbar-logout" onClick={handleLogout}>
                            Wyloguj
                        </button>
                    </>
                )}

                {!user.loggedIn && (
                    <>
                        <NavLink className="navbar-link" to="/login">
                            Logowanie
                        </NavLink>

                        <NavLink className="navbar-link" to="/register">
                            Rejestracja
                        </NavLink>
                    </>
                )}
            </div>
        </nav>
    );
}

export default Navbar;