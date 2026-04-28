package vista;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.PrestamoController;
import dao.LibroDAO;
import dao.*;
import util.Validaciones;

import java.sql.ResultSet;

public class FrmPrestarLibro extends JFrame {

    JTable tabla=new JTable();
    DefaultTableModel modelo=
            new DefaultTableModel();

    JTextField txtDni=new JTextField();
    JTextField txtNombre=new JTextField();

    JButton btnPrestar=new JButton("Prestar");

    int idLibroSeleccionado=-1;

    PrestamoController controller=
            new PrestamoController();

    public FrmPrestarLibro(){

        setTitle("Prestar Libro");
        setSize(600,400);
        setLayout(null);

        crearTabla();
        cargarLibros();

        Validaciones.soloNumeros(txtDni);
        Validaciones.soloLetras(txtNombre);

        txtDni.setBounds(20,250,120,25);
        txtNombre.setBounds(160,250,200,25);
        btnPrestar.setBounds(380,250,120,30);

        add(txtDni);
        add(txtNombre);
        add(btnPrestar);

        eventos();
    }

    private void crearTabla(){

        modelo.addColumn("ID");
        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");
        modelo.addColumn("Autor");
        modelo.addColumn("Stock");

        tabla.setModel(modelo);
        JScrollPane sp=new JScrollPane(tabla);
        sp.setBounds(20,20,540,200);
        add(sp);
    }

    private void cargarLibros(){

        try{

            LibroDAO dao=new LibroDAO();
            ResultSet rs=dao.listarDisponibles();

            while(rs.next()){
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("cantidad")
                });
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void eventos(){

        tabla.getSelectionModel().addListSelectionListener(e->{
            int fila=tabla.getSelectedRow();
            if(fila!=-1)
                idLibroSeleccionado=
                  (int)modelo.getValueAt(fila,0);
        });

        btnPrestar.addActionListener(e->prestar());
    }

    private void prestar(){

        if(idLibroSeleccionado==-1){
            JOptionPane.showMessageDialog(this,
                    "Seleccione libro");
            return;
        }

        if(controller.prestarLibro(
                idLibroSeleccionado,
                txtDni.getText(),
                txtNombre.getText())){

            JOptionPane.showMessageDialog(this,
                    "Prestamo registrado");

            dispose();
        }
    }
}
