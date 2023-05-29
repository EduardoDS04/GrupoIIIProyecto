import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Controlador {
    private Vista vista;
    private Connection conexion;

    public Controlador(Vista vista) {
        this.vista = vista;
        conectarBaseDatos();
        crearTablaEspacios();
    }

    public void cargarNombresUsuarios() {
        String sql = "SELECT nombre FROM usuarios";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                model.addElement(nombre);
            }

            JComboBox<String> usuariosComboBox = vista.getUsuariosComboBox();
            usuariosComboBox.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void conectarBaseDatos() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:javapr.db");
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un Error Conectando con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregarUsuario() {
        String nombre = vista.getNombre();
        String email = vista.getEmail();
        String dni = vista.getDni();
        String direccion = vista.getDireccion();
        String telefono = vista.getTelefono();
        String contra = vista.getContraseña();
        String rol = vista.getRol();

        String sql = "INSERT INTO usuarios (nombre, email, dni, direccion, telefono, contra, rol) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, dni);
            pstmt.setString(4, direccion);
            pstmt.setString(5, telefono);
            pstmt.setString(6, contra);
            pstmt.setString(7, rol);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente", "Agregado Correctamente", JOptionPane.INFORMATION_MESSAGE);
            vista.nombreTextField.setText("");
            vista.emailTextField.setText("");
            vista.dniTextField.setText("");
            vista.dirTextField.setText("");
            vista.telTextField.setText("");
            vista.conTextField1.setText("");
            vista.rol.setSelectedIndex(-1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al Agregar el Usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarUsuario() {
        String nombre = vista.getNombre();
        String email = vista.getEmail();
        String dni = vista.getDni();
        String direccion = vista.getDireccion();
        String telefono = vista.getTelefono();
        String contra = vista.getContraseña();
        String rol = vista.getRol();

        String sql = "UPDATE usuarios SET email = ?, dni = ?, direccion = ?, telefono = ?, contra = ?, rol = ? " +
                "WHERE nombre = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, dni);
            pstmt.setString(3, direccion);
            pstmt.setString(4, telefono);
            pstmt.setString(5, contra);
            pstmt.setString(6, rol);
            pstmt.setString(7, nombre);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente", "Actualizado Correctamente", JOptionPane.INFORMATION_MESSAGE);
            vista.nombreTextField.setText("");
            vista.emailTextField.setText("");
            vista.dniTextField.setText("");
            vista.dirTextField.setText("");
            vista.telTextField.setText("");
            vista.conTextField1.setText("");
            vista.rol.setSelectedIndex(-1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearTablaEspacios() {
        try (Statement stmt = conexion.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "nombre VARCHAR(255) PRIMARY KEY," +
                    "email VARCHAR(255) UNIQUE NOT NULL," +
                    "dni VARCHAR(20) UNIQUE NOT NULL,"+
                    "direccion VARCHAR(255) NOT NULL,"+
                    "telefono VARCHAR(20) NOT NULL,"+
                    "contra VARCHAR(255) NOT NULL,"+
                    "rol VARCHAR(50) NOT NULL"+
                    ")";
            stmt.execute(sql);
            System.out.println("Tabla 'usuarios' creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla 'usuarios': " + e.getMessage());
        }
    }

    public void eliminarUsuario() {
        String nombre = vista.getNombre();

        String sql = "DELETE FROM usuarios WHERE nombre = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente", "Eliminado Correctamente", JOptionPane.INFORMATION_MESSAGE);
            vista.nombreTextField.setText("");
            vista.emailTextField.setText("");
            vista.dniTextField.setText("");
            vista.dirTextField.setText("");
            vista.telTextField.setText("");
            vista.conTextField1.setText("");
            vista.rol.setSelectedIndex(-1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarValores(String nombreUsuario){
        if (nombreUsuario != null) {

            // Consultar la base de datos para obtener la información del usuario seleccionado
            String sql = "SELECT * FROM usuarios WHERE nombre = ?";

            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nombreUsuario);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Obtener los valores de las columnas y actualizar los inputs en la vista
                    vista.setNombre(rs.getString("nombre"));
                    vista.setEmail(rs.getString("email"));
                    vista.setDni(rs.getString("dni"));
                    vista.setDireccion(rs.getString("direccion"));
                    vista.setTelefono(rs.getString("telefono"));
                    vista.setContraseña(rs.getString("contra"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al obtener el usuario seleccionado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void obtenerUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String dni = rs.getString("dni");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String contra = rs.getString("contra");

                // Realizar las acciones necesarias con cada usuario obtenido
                vista.nombreTextField.setText(nombre);
                vista.emailTextField.setText(email);
                vista.dniTextField.setText(dni);
                vista.dirTextField.setText(direccion);
                vista.telTextField.setText(telefono);
                vista.conTextField1.setText(contra);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }
    }
}
