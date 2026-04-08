import { useEffect, useState } from "react";
import "../styles/table.css";

function Exams() {
    const [exams, setExams] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/exams")
            .then(res => res.json())
            .then(data => {
                console.log(data);

                if (Array.isArray(data)) {
                    setExams(data);
                } else if (data.content && Array.isArray(data.content)) {
                    setExams(data.content);
                } else {
                    setExams([]);
                }
            });
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Egzaminy</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Profesor</th>
                    <th>Podejście</th>
                    <th>Data</th>
                    <th>Status</th>
                    <th>Ocena</th>
                </tr>
                </thead>

                <tbody>
                {exams.map(e => (
                    <tr key={`${e.examDate}-${e.attemptNumber}-${e.studentName}`}>
                        <td>{e.professorLastName}</td>
                        <td>{e.attemptNumber}</td>
                        <td>{e.examDate}</td>
                        <td>{e.status}</td>
                        <td>{e.gradeValue}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Exams;
