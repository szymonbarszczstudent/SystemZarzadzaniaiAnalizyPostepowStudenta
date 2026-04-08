import { useEffect, useState } from "react";
import "../styles/table.css";

function Professors() {
    const [professors, setProfessors] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/professors")
            .then(res => res.json())
            .then(data => {
                if (Array.isArray(data)) {
                    setProfessors(data);
                } else if (data.content && Array.isArray(data.content)) {
                    setProfessors(data.content);
                } else {
                    setProfessors([]);
                }
            });
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Profesorowie</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Tytuł</th>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                </tr>
                </thead>

                <tbody>
                {professors.map(p => (
                    <tr key={`${p.firstName}-${p.lastName}-${p.title}`}>
                        <td>{p.title}</td>
                        <td>{p.firstName}</td>
                        <td>{p.lastName}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Professors;