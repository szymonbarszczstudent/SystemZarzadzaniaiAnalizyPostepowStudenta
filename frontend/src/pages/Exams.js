import { useEffect, useState } from "react";

function Exams() {
    const [exams, setExams] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/exams")
            .then(res => res.json())
            .then(data => setExams(data));
    }, []);

    return (
        <div>
            <h1>Exams</h1>

            {exams.map(e => (
                <div key={e.id}>
                    {e.name}
                </div>
            ))}
        </div>
    );
}
export default Exams;