import { useEffect, useState } from "react";
import { getCurrentUser } from "../api/auth";
import "../styles/table.css";

function Exams() {
    const [exams, setExams] = useState([]);
    const [currentUser, setCurrentUser] = useState(null);

    const [form, setForm] = useState({
        enrollmentId: "",
        professorId: "",
        attemptNumber: "1",
        examDate: "",
        status: "ZAPLANOWANY",
        gradeValue: "",
        comment: ""
    });

    const isProfessor = currentUser?.role === "PROFESSOR";

    const loadExams = () => {
        fetch("http://localhost:8080/api/exams", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => {
                if (Array.isArray(data)) {
                    setExams(data);
                } else if (data.content && Array.isArray(data.content)) {
                    setExams(data.content);
                } else {
                    setExams([]);
                }
            });
    };

    useEffect(() => {
        getCurrentUser()
            .then(setCurrentUser)
            .catch(() => setCurrentUser(null));

        loadExams();
    }, []);

    const handleChange = (e) => {
        setForm(prev => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = {
            enrollmentId: Number(form.enrollmentId),
            professorId: Number(form.professorId),
            attemptNumber: Number(form.attemptNumber),
            examDate: form.examDate,
            status: form.status,
            gradeValue: form.gradeValue ? Number(form.gradeValue) : null,
            comment: form.comment
        };

        const res = await fetch("http://localhost:8080/api/exams", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            alert("Nie udało się dodać egzaminu. Sprawdź, czy jesteś profesorem.");
            return;
        }

        setForm({
            enrollmentId: "",
            professorId: "",
            attemptNumber: "1",
            examDate: "",
            status: "ZAPLANOWANY",
            gradeValue: "",
            comment: ""
        });

        loadExams();
    };

    return (
        <div className="table-container">
            <h1 className="table-title">Egzaminy</h1>

            {isProfessor && (
                <form onSubmit={handleSubmit} className="form-container">
                    <h2>Dodaj egzamin</h2>

                    <input
                        name="enrollmentId"
                        type="number"
                        placeholder="ID zapisu studenta na przedmiot"
                        value={form.enrollmentId}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="professorId"
                        type="number"
                        placeholder="ID profesora"
                        value={form.professorId}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="attemptNumber"
                        type="number"
                        min="1"
                        placeholder="Podejście"
                        value={form.attemptNumber}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="examDate"
                        type="date"
                        value={form.examDate}
                        onChange={handleChange}
                        required
                    />

                    <select
                        name="status"
                        value={form.status}
                        onChange={handleChange}
                    >
                        <option value="ZAPLANOWANY">ZAPLANOWANY</option>
                        <option value="ZDANY">ZDANY</option>
                        <option value="NIEZDANY">NIEZDANY</option>
                    </select>

                    <input
                        name="gradeValue"
                        type="number"
                        step="0.01"
                        placeholder="Ocena"
                        value={form.gradeValue}
                        onChange={handleChange}
                    />

                    <input
                        name="comment"
                        placeholder="Komentarz"
                        value={form.comment}
                        onChange={handleChange}
                    />

                    <button type="submit">Dodaj egzamin</button>
                </form>
            )}

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