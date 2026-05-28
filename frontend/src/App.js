import { BrowserRouter, Routes, Route,Navigate  } from "react-router-dom";
import Navbar from "./components/Navbar";

import Students from "./pages/Students";
import Professors from "./pages/Professors";
import Subjects from "./pages/Subjects";
import Exams from "./pages/Exams";
import Grades from "./pages/Grades";
import Users from "./pages/Users";
import Login from "./pages/Login";
import Register from "./pages/Register";

function App() {
    return (
        <BrowserRouter>
            <Navbar />

            <Routes>
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/students" element={<Students />} />
                <Route path="/professors" element={<Professors />} />
                <Route path="/subjects" element={<Subjects />} />
                <Route path="/exams" element={<Exams />} />
                <Route path="/grades" element={<Grades />} />
                <Route path="/users" element={<Users />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;