package vista;

import util.Validaciones;
import javax.swing.*;
import controlador.LibroController;
import modelo.Libro;

public class FrmRegistrarLibro extends JFrame {
	
	

    JTextField txtIsbn = new JTextField();
    JTextField txtTitulo = new JTextField();
    JTextField txtAutor = new JTextField();
    JTextField txtEditorial = new JTextField();
    JTextField txtAnio = new JTextField();
    JTextField txtCantidad = new JTextField();

    JButton btnGuardar = new JButton("Guardar");

    LibroController controller = new LibroController();

    public FrmRegistrarLibro() {

        setTitle("Registrar Libro");
        setSize(350,400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        agregarCampos();
        
        Validaciones.soloNumeros(txtIsbn);
        Validaciones.soloLetras(txtAutor);
        Validaciones.soloNumeros(txtAnio);
        Validaciones.soloNumeros(txtCantidad);

        btnGuardar.addActionListener(e -> guardarLibro());
    }

    private void agregarCampos() {

        addLabel("ISBN",20);
        txtIsbn.setBounds(120,20,180,25); add(txtIsbn);

        addLabel("Titulo",60);
        txtTitulo.setBounds(120,60,180,25); add(txtTitulo);

        addLabel("Autor",100);
        txtAutor.setBounds(120,100,180,25); add(txtAutor);

        addLabel("Editorial",140);
        txtEditorial.setBounds(120,140,180,25); add(txtEditorial);

        addLabel("Año",180);
        txtAnio.setBounds(120,180,180,25); add(txtAnio);

        addLabel("Cantidad",220);
        txtCantidad.setBounds(120,220,180,25); add(txtCantidad);

        btnGuardar.setBounds(120,270,120,30);
        add(btnGuardar);
    }

    private void addLabel(String texto,int y){
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(20,y,100,25);
        add(lbl);
    }

    private void guardarLibro() {

        if(Validaciones.campoVacio(txtIsbn,"ISBN")) return;
        if(Validaciones.campoVacio(txtTitulo,"Titulo")) return;
        if(Validaciones.campoVacio(txtCantidad,"Cantidad")) return;

        if(controller.existeISBN(txtIsbn.getText())){
            JOptionPane.showMessageDialog(this,
                    "ISBN ya registrado");
            return;
        }

        try {

            Libro libro = new Libro(
                    txtIsbn.getText(),
                    txtTitulo.getText(),
                    txtAutor.getText(),
                    txtEditorial.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    Integer.parseInt(txtCantidad.getText())
            );

            if(controller.registrarLibro(libro)){

                JOptionPane.showMessageDialog(this,
                        "Libro registrado correctamente");

                limpiarFormulario();

            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(this,
                    "Datos inválidos");
        }
    }
    
    private void limpiarFormulario(){

        txtIsbn.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtEditorial.setText("");
        txtAnio.setText("");
        txtCantidad.setText("");

        txtIsbn.requestFocus();
    }
}
