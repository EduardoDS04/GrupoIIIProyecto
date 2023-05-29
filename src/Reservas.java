import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reservas extends JFrame{
    private JPanel main;
    private ControladorReservas controlador;
    private JPanel mainPanel;
    private JPanel cerrarLabel;
    private JLabel atrasLabel;
    private JLabel crearReservaLabel;
    private JTextField codigoTextField;
    private JTextArea motivoTextField;
    private JTextField date;

    private JCheckBox adjudicada;
    private JTextField emailTextField;
    private JPanel panel;

    private JPanel pCald;
    JDateChooser dateChooser = new JDateChooser();

    public Reservas(){
        setContentPane(mainPanel);
        setTitle("Proyecto Java");
        setSize(1070, 590);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        controlador = new ControladorReservas(this);
        panel.add(dateChooser);
        setVisible(true);
        crearReservaLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        atrasLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));


        atrasLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Vista vis = new Vista();
                vis.setVisible(true);
                setVisible(false);
            }
        });
        crearReservaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controlador.crearReserva();
                limpiarCampos();

            }
        });
    }
    public void limpiarCampos() {
        codigoTextField.setText("");
        emailTextField.setText("");
        motivoTextField.setText("");
        dateChooser.setDate(null);
        adjudicada.setSelected(false);
    }

    public int getCodigoReserva() {
        return Integer.parseInt(codigoTextField.getText());
    }

    public String getEmail() {
        return emailTextField.getText();
    }
    public java.sql.Date getFechaReserva() {
        // Obtener la fecha seleccionada del JDateChooser
        java.util.Date fecha = dateChooser.getDate();

        // Obtener los milisegundos de la fecha como un long
        long milisegundos = fecha.getTime();

        // Crear un objeto java.sql.Date a partir de los milisegundos
        return new java.sql.Date(milisegundos);
    }

    public String getMotivo() {
        return motivoTextField.getText();
    }

    public boolean isAdjudicada() {
        return adjudicada.isSelected();
    }

    public static void main(String[] args){
        Reservas reservas = new Reservas();
        reservas.setResizable(false);

    }
}
