import { useEffect, useState } from "react";
import "../styles/table.css";

function Grades() {
    const [grades, setGrades] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/grades")
            .then(res => res.json())
            .then(data => {
                if (Array.isArray(data)) {
                    setGrades(data);
                } else if (data.content && Array.isArray(data.content)) {
                    setGrades(data.content);
                } else {
                    setGrades([]);
                }
            });
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Oceny</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Enrollment</th>
                    <th>Profesor</th>
                    <th>Kategoria</th>
                    <th>Ocena</th>
                    <th>Waga</th>
                    <th>Komentarz</th>
                    <th>Data</th>
                </tr>
                </thead>

                <tbody>
                {grades.map(g => (
                    <tr key={`${e.examDate}-${e.attemptNumber}-${e.studentName}`}>
                        <td>{g.enrollmentId || g.enrollment?.enrollmentId}</td>
                        <td>{g.professorId || g.professor?.id}</td>
                        <td>{g.category}</td>
                        <td>{g.gradeValue}</td>
                        <td>{g.weight}</td>
                        <td>{g.comment}</td>
                        <td>{g.gradedAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Grades;