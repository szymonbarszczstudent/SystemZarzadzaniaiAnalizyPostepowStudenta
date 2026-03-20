import { NavLink } from "react-router-dom";
import "../styles/navbar.css";
function Navbar() {
    return (
        <nav className="navbar">
            <div className="navbar-logo">System zarządzania i analizy postępów studenta</div>
            <div className="navbar-links">
                <NavLink className="navbar-link" to="/students">Studenci</NavLink>
                <NavLink className="navbar-link" to="/professors">Nauczyciele akademiccy</NavLink>
                <NavLink className="navbar-link" to="/subjects">Przedmioty</NavLink>
                <NavLink className="navbar-link" to="/exams">Egzaminy</NavLink>
                <NavLink className="navbar-link" to="/grades">Oceny</NavLink>
                <NavLink className="navbar-link" to="/users">Użytkownicy</NavLink>
            </div>
        </nav>
);
}

export default Navbar;