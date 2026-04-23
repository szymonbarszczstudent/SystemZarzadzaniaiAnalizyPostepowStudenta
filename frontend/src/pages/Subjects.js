import { useEffect, useState } from "react";
import "../styles/table.css";

function Subjects() {
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/subjects", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => setSubjects(data));
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Przedmioty</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Kod</th>
                    <th>Nazwa</th>
                    <th>ECTS</th>
                    <th>Opis</th>
                </tr>
                </thead>

                <tbody>
                {subjects.map(s => (
                    <tr key={s.code}>
                        <td>{s.code}</td>
                        <td>{s.name}</td>
                        <td>{s.ects}</td>
                        <td>{s.description}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Subjects;
