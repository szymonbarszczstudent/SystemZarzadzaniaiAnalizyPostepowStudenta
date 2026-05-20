import { useEffect, useState } from "react";
import { getCurrentUser } from "../api/auth";
import "../styles/table.css";

function Grades() {
    const [grades, setGrades] = useState([]);
    const [currentUser, setCurrentUser] = useState(null);
    const [enrollments, setEnrollments] = useState([]);
    const [professors, setProfessors] = useState([]);

    useEffect(() => {
        getCurrentUser()
            .then(user => {
                setCurrentUser(user);

                if (user?.role === "PROFESSOR") {
                    setForm(prev => ({
                        ...prev,
                        professorId: user.userId
                    }));
                }
            })
            .catch(() => setCurrentUser(null));

        loadGrades();

        fetch("http://localhost:8080/api/enrollments", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => setEnrollments(Array.isArray(data) ? data : []));

        fetch("http://localhost:8080/api/professors", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => setProfessors(Array.isArray(data) ? data : []));
    }, []);
    const [form, setForm] = useState({
        enrollmentId: "",
        professorId: "",
        category: "",
        gradeValue: "",
        weight: "1",
        comment: "",
        gradedAt: ""
    });

    const isProfessor = currentUser?.role === "PROFESSOR";

    const loadGrades = () => {
        fetch("http://localhost:8080/api/grades", {
            credentials: "include"
        })
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
    };

    useEffect(() => {
        getCurrentUser()
            .then(setCurrentUser)
            .catch(() => setCurrentUser(null));

        loadGrades();
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
            category: form.category,
            gradeValue: Number(form.gradeValue),
            weight: Number(form.weight),
            comment: form.comment,
            gradedAt: form.gradedAt
                ? new Date(form.gradedAt).toISOString()
                : new Date().toISOString()
        };

        const res = await fetch("http://localhost:8080/api/grades", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            alert("Nie udało się dodać oceny. Sprawdź, czy jesteś profesorem.");
            return;
        }

        setForm({
            enrollmentId: "",
            professorId: "",
            category: "",
            gradeValue: "",
            weight: "1",
            comment: "",
            gradedAt: ""
        });

        loadGrades();
    };

    return (
        <div className="table-container">
            <h1 className="table-title">Oceny</h1>

            {isProfessor && (
                <form onSubmit={handleSubmit} className="form-container">
                    <h2>Dodaj ocenę</h2>

                    <select
                        name="enrollmentId"
                        value={form.enrollmentId}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Wybierz studenta i przedmiot</option>

                        {enrollments.map(e => (
                            <option key={e.id} value={e.id}>
                                {e.studentFirstName} {e.studentLastName} ({e.studentNumber}) — {e.subjectName}
                            </option>
                        ))}
                    </select>

                    <input
                        name="category"
                        placeholder="Kategoria, np. kolokwium"
                        value={form.category}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="gradeValue"
                        type="number"
                        step="0.01"
                        placeholder="Ocena"
                        value={form.gradeValue}
                        onChange={handleChange}
                        required
                    />

                    <input
                        name="weight"
                        type="number"
                        step="0.01"
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

                    <input
                        name="gradedAt"
                        type="datetime-local"
                        value={form.gradedAt}
                        onChange={handleChange}
                    />

                    <button type="submit">Dodaj ocenę</button>
                </form>
            )}

            <table className="custom-table">
                <thead>
                <tr>
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
                    <tr key={`${g.studentNumber}-${g.subjectCode}-${g.gradedAt}`}>
                        <td>{g.professorLastName}</td>
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