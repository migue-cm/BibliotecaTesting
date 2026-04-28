package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.ListarPrestamoController;

import java.sql.ResultSet;

public class FrmListarPrestamos extends JFrame {

    JTable tabla = new JTable();
    DefaultTableModel modelo =
            new DefaultTableModel();

    JComboBox<String> cbFiltro =
            new JComboBox<>(new String[]{
                    "TODOS","ACTIVO","DEVUELTO"
            });

    ListarPrestamoController controller =
            new ListarPrestamoController();

    public FrmListarPrestamos(){

        setTitle("Historial de Prestamos");
        setSize(750,450);
        setLayout(null);

        crearComponentes();
        cargarDatos("TODOS");
    }

    private void crearComponentes(){

        JLabel lblFiltro =
                new JLabel("Filtrar:");

        lblFiltro.setBounds(20,20,80,25);
        cbFiltro.setBounds(100,20,150,25);

        add(lblFiltro);
        add(cbFiltro);

        modelo.addColumn("ID");
        modelo.addColumn("Libro");
        modelo.addColumn("Lector");
        modelo.addColumn("Fecha Prestamo");
        modelo.addColumn("Fecha Devolucion");
        modelo.addColumn("Estado");

        tabla.setModel(modelo);

        JScrollPane sp =
                new JScrollPane(tabla);

        sp.setBounds(20,70,700,320);

        add(sp);

        cbFiltro.addActionListener(e ->
            cargarDatos(cbFiltro.getSelectedItem().toString()));
    }

    private void cargarDatos(String estado){

        try{

            modelo.setRowCount(0);

            ResultSet rs =
                    controller.listar(estado);

            while(rs.next()){

                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getString("estado")
                });
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}