package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.ConsultaLibroController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

public class FrmConsultarDisponibilidad extends JFrame {

    JTextField txtBuscar = new JTextField();

    JTable tabla = new JTable();
    DefaultTableModel modelo =
            new DefaultTableModel();

    ConsultaLibroController controller =
            new ConsultaLibroController();

    public FrmConsultarDisponibilidad(){

        setTitle("Consultar Disponibilidad");
        setSize(700,450);
        setLayout(null);

        crearComponentes();
        cargarTabla("");

    }

    private void crearComponentes(){

        JLabel lblBuscar =
                new JLabel("Buscar Libro:");

        lblBuscar.setBounds(20,20,100,25);
        txtBuscar.setBounds(120,20,300,25);

        add(lblBuscar);
        add(txtBuscar);

        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");
        modelo.addColumn("Autor");
        modelo.addColumn("Editorial");
        modelo.addColumn("Año");
        modelo.addColumn("Stock");

        tabla.setModel(modelo);

        JScrollPane sp =
                new JScrollPane(tabla);

        sp.setBounds(20,70,640,300);
        add(sp);

        // BUSQUEDA DINAMICA
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                cargarTabla(txtBuscar.getText());
            }
        });
    }

    private void cargarTabla(String texto){

        try{

            modelo.setRowCount(0);

            ResultSet rs =
                    controller.buscar(texto);

            while(rs.next()){

                modelo.addRow(new Object[]{
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio"),
                        rs.getInt("cantidad")
                });
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
