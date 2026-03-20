const styles = {
    card: {
        backgroundColor: "#fff",
        padding: "16px",
        borderRadius: "12px",
        boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
        marginBottom: "12px",
        transition: "transform 0.2s",
    },
    name: {
        margin: 0,
        fontSize: "20px",
    },
    program: {
        margin: "8px 0 0",
        color: "#555",
    },
};
function StudentCard({ student }) {
    return (
        <div style={styles.card}>
            <p><strong>Nazwisko:</strong> {student.lastName}</p>
            <p><strong>Imię:</strong> {student.firstName}</p>
            <p><strong>Kierunek:</strong> {student.programName}</p>
            <p><strong>Numer albumu:</strong> {student.studentNumber}</p>
            <p><strong>Rok studiów:</strong> {student.studyYear}</p>
        </div>
    );
}
export default StudentCard;
