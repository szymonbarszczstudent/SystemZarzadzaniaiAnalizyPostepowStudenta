import { useEffect, useState } from "react";
import "../styles/table.css";
function Students() {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/students/me", {
            withCredentials: true
        })
            .then(res => res.json())
            .then(data => setStudents(data));
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Studenci</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Kierunek</th>
                    <th>Rok</th>
                    <th>Album</th>
                </tr>
                </thead>

                <tbody>
                {students.map(s => (
                    <tr key={s.studentNumber}>
                        <td>{s.firstName}</td>
                        <td>{s.lastName}</td>
                        <td>{s.programName}</td>
                        <td>{s.studyYear}</td>
                        <td>{s.studentNumber}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
export default Students;