import { useEffect, useState } from "react";
import { getCurrentUser } from "../api/auth";
import "../styles/table.css";

function Exams() {
    const [exams, setExams] = useState([]);
    const [currentUser, setCurrentUser] = useState(null);
    const [enrollments, setEnrollments] = useState([]);
    const [form, setForm] = useState({
        enrollmentId: "",
        attemptNumber: "",
        examDate: "",
        status: "PASSED",
        gradeValue: "",
        comment: ""
    });
    const loadEnrollments = () => {
        fetch("http://localhost:8080/api/enrollments/options", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => {
                if (Array.isArray(data)) {
                    setEnrollments(data);
                } else {
                    setEnrollments([]);
                }
            })
            .catch(() => setEnrollments([]));
    };
    const isProfessor = currentUser?.role === "PROFESSOR";

    const statusLabels = {
        PASSED: "Zaliczony",
        FAILED: "Niezaliczony",
        ABSENT: "Nieobecny",
        CANCELLED: "Anulowany"
    };

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
            })
            .catch(() => setExams([]));
    };

    useEffect(() => {
        getCurrentUser()
            .then(user => {
                setCurrentUser(user);

                if (user?.role === "PROFESSOR") {
                    loadEnrollments();
                }
            })
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
            alert("Nie udało się dodać egzaminu. Sprawdź dane formularza.");
            return;
        }

        setForm({
            enrollmentId: "",
            attemptNumber: "",
            examDate: "",
            status: "PASSED",
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

                    <select
                        name="enrollmentId"
                        value={form.enrollmentId}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Wybierz studenta</option>

                        {enrollments.map(enrollment => (
                            <option
                                key={enrollment.enrollmentId}
                                value={enrollment.enrollmentId}
                            >
                                {enrollment.studentNumber} - {enrollment.firstName} {enrollment.lastName} - {enrollment.programName}
                            </option>
                        ))}
                    </select>

                    <input
                        name="attemptNumber"
                        type="number"
                        min="1"
                        max="3"
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

                    <select name="status" value={form.status} onChange={handleChange}>
                        <option value="PASSED">Zaliczony</option>
                        <option value="FAILED">Niezaliczony</option>
                        <option value="ABSENT">Nieobecny</option>
                        <option value="CANCELLED">Anulowany</option>
                    </select>

                    <input
                        name="gradeValue"
                        type="number"
                        min="2.0"
                        max="5.0"
                        step="0.5"
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
                    <th>Student</th>
                    <th>Przedmiot</th>
                    <th>Profesor</th>
                    <th>Podejście</th>
                    <th>Data</th>
                    <th>Status</th>
                    <th>Ocena</th>
                    <th>Komentarz</th>
                </tr>
                </thead>

                <tbody>
                {exams.map((exam, index) => (
                    <tr key={exam.id ?? `${exam.examDate}-${exam.attemptNumber}-${index}`}>
                        <td>{exam.studentNumber ?? "-"}</td>
                        <td>{exam.subjectName ?? "-"}</td>
                        <td>{exam.professorLastName ?? "-"}</td>
                        <td>{exam.attemptNumber ?? "-"}</td>
                        <td>{exam.examDate ?? "-"}</td>
                        <td>{statusLabels[exam.status] ?? exam.status ?? "-"}</td>
                        <td>{exam.gradeValue ?? "-"}</td>
                        <td>{exam.comment ?? "-"}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Exams;