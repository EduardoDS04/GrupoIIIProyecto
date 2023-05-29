import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class ControladorReservas {
    private static final String JDBC_URL = "jdbc:sqlite:javapr.db";
    private Reservas vista;
    private Connection conexion;

    public ControladorReservas(Reservas reservas) {
        this.vista = reservas;
        conectarBaseDatos();
        crearTablaReservas();
    }

    private void conectarBaseDatos() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:javapr.db");
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un Error Conectando con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearReserva() {
        int codigoReserva = vista.getCodigoReserva();
        String email = vista.getEmail();
        Date fechaReserva = vista.getFechaReserva();
        String motivo = vista.getMotivo();
        boolean adjudicada = vista.isAdjudicada();

        String sql = "INSERT INTO Reservas (codigo_reserva, email, fecha_reserva, motivo, adjudicada) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, codigoReserva);
            pstmt.setString(2, email);
            pstmt.setDate(3, fechaReserva);
            pstmt.setString(4, motivo);
            pstmt.setBoolean(5, adjudicada);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reserva Creada Correctamente", "Reserva Creada", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Creando la Reserva", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void crearTablaReservas() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            // Crear la tabla Reservas
            String query = "CREATE TABLE IF NOT EXISTS Reservas ("
                    + "codigo_reserva INT PRIMARY KEY,"
                    + "fecha_reserva DATE,"
                    + "email VARCHAR(255),"
                    + "motivo TEXT,"
                    + "adjudicada BOOLEAN"
                    + ")";
            statement.executeUpdate(query);
            System.out.println("Tabla Reservas creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
