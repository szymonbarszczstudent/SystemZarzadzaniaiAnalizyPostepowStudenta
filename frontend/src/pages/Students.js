import { useEffect, useState } from "react";
import "../styles/table.css";

function Students() {
    const [student, setStudent] = useState(null);
    const [error, setError] = useState("");

    useEffect(() => {
        fetch("http://localhost:8080/api/students/me", {
            method: "GET",
            credentials: "include",
        })
            .then(async (res) => {
                if (res.status === 401) {
                    throw new Error("Musisz być zalogowany.");
                }

                if (!res.ok) {
                    throw new Error("Nie udało się pobrać danych studenta.");
                }

                return res.json();
            })
            .then((data) => setStudent(data))
            .catch((err) => setError(err.message));
    }, []);

    if (error) {
        return (
            <div className="table-container">
                <h1 className="table-title">Studenci</h1>
                <p>{error}</p>
            </div>
        );
    }

    if (!student) {
        return (
            <div className="table-container">
                <h1 className="table-title">Studenci</h1>
                <p>Ładowanie danych...</p>
            </div>
        );
    }

    return (
        <div className="table-container">
            <h1 className="table-title">Moje dane studenta</h1>

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
                <tr key={student.studentNumber}>
                    <td>{student.firstName}</td>
                    <td>{student.lastName}</td>
                    <td>{student.programName}</td>
                    <td>{student.studyYear}</td>
                    <td>{student.studentNumber}</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

export default Students;