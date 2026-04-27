const API_URL = "http://localhost:8080/auth";

export async function register(email, password, confirm_password) {
    const res = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify({ email, password, confirm_password  }),
    });

    if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Register failed");
    }
}

export async function login(email, password) {
    const res = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify({ email, password }),
    });
    if (!res.ok) {
        throw new Error("Login failed");
    }

    return await res.json();
}

    export async function getCurrentUser() {
        const res = await fetch(`${API_URL}/me`, {
            method: "GET",
            credentials: "include",
        });

        if (!res.ok) {
            throw new Error("Auth check failed");
        }

        return await res.json();
    }

    export async function logout() {
        const res = await fetch(`${API_URL}/logout`, {
            method: "POST",
            credentials: "include",
        });

        if (!res.ok) {
            throw new Error("Logout failed");
        }
}