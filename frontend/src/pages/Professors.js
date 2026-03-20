import { useEffect, useState } from "react";

function Professors() {
    const [professors, setProfessors] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/professors")
            .then(res => res.json())
            .then(data => setProfessors(data));
    }, []);

    return (
        <div>
            <h1>Professors</h1>

            {professors.map(p => (
                <div key={p.id}>
                    {p.firstName} {p.lastName}
                </div>
            ))}
        </div>
    );
}
export default Professors;