import { useEffect, useState } from "react";

function Grades() {
    const [grades, setGrades] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/grades")
            .then(res => res.json())
            .then(data => setGrades(data));
    }, []);

    return (
        <div>
            <h1>Grades</h1>

            {grades.map(g => (
                <div key={g.id}>
                    {g.value}
                </div>
            ))}
        </div>
    );
}
export default Grades;