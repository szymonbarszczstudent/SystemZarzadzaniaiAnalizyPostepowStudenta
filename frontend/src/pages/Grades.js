import { useEffect, useState } from "react";
import { getCurrentUser } from "../api/auth";
import "../styles/table.css";

function Grades() {
    const [grades, setGrades] = useState([]);
    const [currentUser, setCurrentUser] = useState(null);

    const [form, setForm] = useState({
        enrollmentId: "",
        category: "",
        gradeValue: "",
        weight: "1",
        comment: ""
    });

    const isProfessor = currentUser?.role === "PROFESSOR";

    const loadGrades = () => {
        fetch("http://localhost:8080/api/grades", { credentials: "include" })
            .then(res => res.json())
            .then(data => {
                if (Array.isArray(data)) {
                    setGrades(data);
                } else if (data.content && Array.isArray(data.content)) {
                    setGrades(data.content);
                } else {
                    setGrades([]);
                }
            })
            .catch(() => setGrades([]));
    };

    useEffect(() => {
        getCurrentUser()
            .then(setCurrentUser)
            .catch(() => setCurrentUser(null));

        loadGrades();
    }, []);

    const handleChange = e => {
        setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
    };

    const handleSubmit = async e => {
        e.preventDefault();

        const payload = {
            enrollmentId: Number(form.enrollmentId),
            category: form.category,
            gradeValue: Number(form.gradeValue),
            weight: Number(form.weight),
            comment: form.comment
        };

        const res = await fetch("http://localhost:8080/api/grades", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            alert("Nie udało się dodać oceny. Sprawdź dane formularza.");
            return;
        }

        setForm({
            enrollmentId: "",
            category: "",
            gradeValue: "",
            weight: "1",
            comment: ""
        });

        loadGrades();
    };

    return (
        <div className="table-container">
            <h1 className="table-title">Oceny</h1>

            {isProfessor && (
                <form onSubmit={handleSubmit} className="form-container">
                    <h2>Dodaj ocenę</h2>

                    <input
                        name="enrollmentId"
                        type="number"
                        placeholder="ID zapisu studenta na przedmiot"
                        value={form.enrollmentId}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="category"
                        placeholder="Kategoria, np. Kolokwium 1"
                        value={form.category}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="gradeValue"
                        type="number"
                        step="0.5"
                        min="2"
                        max="5"
                        placeholder="Ocena"
                        value={form.gradeValue}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="weight"
                        type="number"
                        step="0.1"
                        min="0.1"
                        placeholder="Waga"
                        value={form.weight}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="comment"
                        placeholder="Komentarz"
                        value={form.comment}
                        onChange={handleChange}
                    />

                    <button type="submit">Dodaj ocenę</button>
                </form>
            )}

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Student</th>
                    <th>Przedmiot</th>
                    <th>Profesor</th>
                    <th>Kategoria</th>
                    <th>Ocena</th>
                    <th>Waga</th>
                    <th>Komentarz</th>
                    <th>Data</th>
                </tr>
                </thead>

                <tbody>
                {grades.map((grade, index) => (
                    <tr key={index}>
                        <td>{grade.studentNumber ?? "-"}</td>
                        <td>{grade.subjectName ?? "-"}</td>
                        <td>{grade.professorLastName ?? "-"}</td>
                        <td>{grade.category ?? "-"}</td>
                        <td>{grade.gradeValue ?? "-"}</td>
                        <td>{grade.weight ?? "-"}</td>
                        <td>{grade.comment ?? "-"}</td>
                        <td>{grade.gradedAt ?? "-"}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Grades;