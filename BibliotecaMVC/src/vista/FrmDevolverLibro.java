package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.DevolucionController;

import java.sql.ResultSet;

public class FrmDevolverLibro extends JFrame {

    JTable tabla = new JTable();
    DefaultTableModel modelo =
            new DefaultTableModel();

    JButton btnDevolver =
            new JButton("Devolver Libro");

    int idPrestamo=-1;
    int idLibro=-1;

    DevolucionController controller =
            new DevolucionController();

    public FrmDevolverLibro(){

        setTitle("Devolver Libro");
        setSize(600,400);
        setLayout(null);

        crearTabla();
        cargarPrestamos();

        btnDevolver.setBounds(200,300,180,30);
        add(btnDevolver);

        eventos();
    }

    private void crearTabla(){

        modelo.addColumn("ID Prestamo");
        modelo.addColumn("Libro");
        modelo.addColumn("Lector");
        modelo.addColumn("Fecha");
        modelo.addColumn("ID Libro");

        tabla.setModel(modelo);

        JScrollPane sp =
                new JScrollPane(tabla);

        sp.setBounds(20,20,540,250);

        add(sp);
    }

    private void cargarPrestamos(){

        try{

            ResultSet rs =
                controller.listarActivos();

            while(rs.next()){

                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_prestamo"),
                        rs.getInt("id_libro")
                });
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void eventos(){

        tabla.getSelectionModel()
        .addListSelectionListener(e->{

            int fila = tabla.getSelectedRow();

            if(fila!=-1){
                idPrestamo =
                 (int)modelo.getValueAt(fila,0);

                idLibro =
                 (int)modelo.getValueAt(fila,4);
            }
        });

        btnDevolver.addActionListener(e->devolver());
    }

    private void devolver(){

        if(idPrestamo==-1){
            JOptionPane.showMessageDialog(this,
                    "Seleccione un préstamo");
            return;
        }

        if(controller.devolver(idPrestamo,idLibro)){

            JOptionPane.showMessageDialog(this,
                    "Libro devuelto correctamente");

            dispose();
        }
    }
}
