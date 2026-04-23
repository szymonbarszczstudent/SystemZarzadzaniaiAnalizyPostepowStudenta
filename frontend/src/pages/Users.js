import { useEffect, useState } from "react";
import "../styles/table.css";

function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/users", {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => setUsers(data));
    }, []);

    return (
        <div className="table-container">
            <h1 className="table-title">Użytkownicy</h1>

            <table className="custom-table">
                <thead>
                <tr>
                    <th>Email</th>
                    <th>Rola</th>
                    <th>Utworzono</th>
                </tr>
                </thead>

                <tbody>
                {users.map(u => (
                    <tr key={u.email}>
                        <td>{u.email}</td>
                        <td>{u.role}</td>
                        <td>{u.createdAt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Users;