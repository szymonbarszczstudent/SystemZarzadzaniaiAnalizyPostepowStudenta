import { useEffect, useState } from "react";

function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/users")
            .then(res => res.json())
            .then(data => setUsers(data));
    }, []);

    return (
        <div>
            <h1>Users</h1>

            {users.map(u => (
                <div key={u.id}>
                    {u.email}
                </div>
            ))}
        </div>
    );
}
export default Users;