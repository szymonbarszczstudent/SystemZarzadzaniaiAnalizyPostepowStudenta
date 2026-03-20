import { useEffect, useState } from "react";

function Subjects() {
    const [subjects, setSubjects] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/subjects")
            .then(res => res.json())
            .then(data => setSubjects(data));
    }, []);

    return (
        <div>
            <h1>Subjects</h1>

            {subjects.map(s => (
                <div key={s.id}>
                    {s.name}
                </div>
            ))}
        </div>
    );
}
export default Subjects;