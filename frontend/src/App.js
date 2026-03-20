import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";

import Students from "./pages/Students";
import Professors from "./pages/Professors";
import Subjects from "./pages/Subjects";
import Exams from "./pages/Exams";
import Grades from "./pages/Grades";
import Users from "./pages/Users";

function App() {
    return (
        <BrowserRouter>
            <Navbar />

            <Routes>
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