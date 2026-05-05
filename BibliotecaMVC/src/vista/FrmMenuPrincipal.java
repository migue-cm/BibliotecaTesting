package vista;

import javax.swing.*;

public class FrmMenuPrincipal extends JFrame {

    JMenuBar menuBar = new JMenuBar();

    JMenu menuLibro = new JMenu("Libros");
    JMenu menuPrestamo = new JMenu("Prestamos");

    JMenuItem itemRegistrar = new JMenuItem("Registrar Libro");
    JMenuItem itemPrestar = new JMenuItem("Prestar Libro");
    JMenuItem itemDevolver = new JMenuItem("Devolver Libro");
    JMenuItem itemListar = new JMenuItem("Listar Prestamos");
    
    JMenu menuConsulta = new JMenu("Consultas");
    JMenuItem itemDisponibilidad = new JMenuItem("Disponibilidad Libros");

    public FrmMenuPrincipal(){

        setTitle("Sistema Biblioteca");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(menuBar);

        menuBar.add(menuLibro);
        menuBar.add(menuPrestamo);

        menuLibro.add(itemRegistrar);

        menuPrestamo.add(itemPrestar);
        menuPrestamo.add(itemDevolver);
        menuPrestamo.add(itemListar);
        
        menuBar.add(menuConsulta);
        menuConsulta.add(itemDisponibilidad);

        eventos();
    }

    private void eventos(){

        itemRegistrar.addActionListener(e ->
                new FrmRegistrarLibro().setVisible(true));

       
        itemPrestar.addActionListener(e ->
        new FrmPrestarLibro().setVisible(true));

        itemDevolver.addActionListener(e ->
        new FrmDevolverLibro().setVisible(true));

        itemListar.addActionListener(e ->
        new FrmListarPrestamos().setVisible(true));
        
        itemDisponibilidad.addActionListener(e ->
        new FrmConsultarDisponibilidad().setVisible(true));
    }
}
