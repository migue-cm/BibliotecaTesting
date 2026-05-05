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
    
    
    
    JLabel lblDni = new JLabel("DNI:"); 
    JLabel lblNombre = new JLabel("Nombre:");
    
    
    JTextField txtDni=new JTextField();
    JTextField txtNombre=new JTextField();

    JButton btnPrestar=new JButton("Prestar");

    int idLibroSeleccionado=-1;

    PrestamoController controller=
            new PrestamoController();

    public FrmPrestarLibro(){

        setTitle("Prestar Libro");
        setSize(700,400);
        setLayout(null);

        crearTabla();
        cargarLibros();

        Validaciones.limitarDNI(txtDni);
        Validaciones.soloLetras(txtNombre);

        lblDni.setBounds(20,250,100,25);
        txtDni.setBounds(50,250,120,25);
        lblNombre.setBounds(180,250,80,25);
        txtNombre.setBounds(240,250,200,25);
        btnPrestar.setBounds(530,250,120,30);
       
        add(txtDni);
        add(txtNombre);
        add(btnPrestar);
        add(lblDni);
        add(lblNombre);

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
        sp.setBounds(20,20,640,200);
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

        String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();

        if(dni.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese el DNI del lector",
                    "Campo obligatorio",
                    JOptionPane.WARNING_MESSAGE);
            txtDni.requestFocus();
            return;
        }

        if(dni.length() != 8){
            JOptionPane.showMessageDialog(this,
                    "El DNI debe contener exactamente 8 digitos",
                    "DNI invalido",
                    JOptionPane.WARNING_MESSAGE);
            txtDni.requestFocus();
            return;
        }

        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese el nombre del lector",
                    "Campo obligatorio",
                    JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }

        if(idLibroSeleccionado == -1){
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un libro para prestar",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean exito = controller.prestarLibro(
                idLibroSeleccionado,
                dni,
                nombre
        );

        if(exito){

            JOptionPane.showMessageDialog(this,
                    "Prestamo registrado correctamente");

            
            txtDni.setText("");
            txtNombre.setText("");
            tabla.clearSelection();
            idLibroSeleccionado = -1;

        }else{

            JOptionPane.showMessageDialog(this,
                    "No se pudo registrar el prestamo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}