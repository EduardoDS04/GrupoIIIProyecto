import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vista extends JFrame{
    private Controlador controlador;
    private JLabel reservasLabel;
    private JPanel cerrarLabel;
    private JPanel main;
    private JPanel mainPanel;
    public JLabel crearUsuarioLabel;
    public JLabel guardarDatosLabel;
    public JLabel eliminarUsuarioLabel;
    public JButton jButton2;
    public JComboBox usuariosComboBox;
    public JTextField nombreTextField;
    public JTextArea descripcionTextField;
    public JTextField dniTextField;
    public JCheckBox ordenadorCheckBox;
    public JCheckBox pizarraCheckBox;
    public JCheckBox proyectoCheckBox;
    public JTextField emailTextField;
    public JTextField dirTextField;
    public JTextField telTextField;
    public JPasswordField conTextField1;
    public JComboBox rol;

    public Vista(){
        controlador = new Controlador(this);
        setContentPane(mainPanel);
        setTitle("Proyecto Java");
        setSize(1070, 590);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        controlador.cargarNombresUsuarios();
        eliminarUsuarioLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardarDatosLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        crearUsuarioLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reservasLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        reservasLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);


            }
        });
        crearUsuarioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controlador.agregarUsuario();


            }
        });
        guardarDatosLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controlador.editarUsuario();


            }
        });
        eliminarUsuarioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controlador.eliminarUsuario();


            }
        });
        jButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controlador.cargarNombresUsuarios();

            }
        });
        usuariosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el elemento seleccionado del JComboBox
                Object seleccionado = getUsuariosComboBox().getSelectedItem();

                if (seleccionado != null) {
                    // Realizar la acción deseada con el elemento seleccionado
                    String nombreUsuario = seleccionado.toString();
                    System.out.println(nombreUsuario);
                    controlador.actualizarValores(nombreUsuario);
                }

            }
        });
        reservasLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Reservas pwn = new Reservas();
                pwn.setVisible(true);
                setVisible(false);

            }
        });
    }

    public String getNombre() {
        return nombreTextField.getText();
    }

    public void setNombre(String nombre) {
        nombreTextField.setText(nombre);
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public void setEmail(String email) {
        emailTextField.setText(email);
    }

    public String getDni() {
        return dniTextField.getText();
    }

    public void setDni(String dni) {
        dniTextField.setText(dni);
    }

    public String getDireccion() {
        return dirTextField.getText();
    }

    public void setDireccion(String direccion) {
        dirTextField.setText(direccion);
    }

    public String getTelefono() {
        return telTextField.getText();
    }

    public void setTelefono(String telefono) {
        telTextField.setText(telefono);
    }

    public String getContraseña() {
        return new String(conTextField1.getPassword());
    }

    public void setContraseña(String contraseña) {
        conTextField1.setText(contraseña);
    }

    public String getRol() {
        return rol.getSelectedItem().toString();
    }

    public void limpiarCampos() {
        nombreTextField.setText("");
        emailTextField.setText("");
        dniTextField.setText("");
        dirTextField.setText("");
        telTextField.setText("");
        conTextField1.setText("");
        rol.setSelectedIndex(-1);
    }

    public JComboBox<String> getUsuariosComboBox() {
        return usuariosComboBox;
    }


    public static void main(String[] args){
        Vista vista = new Vista();
        vista.setResizable(false);

    }

}
