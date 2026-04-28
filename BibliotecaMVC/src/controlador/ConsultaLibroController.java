package controlador;

import java.sql.ResultSet;
import dao.LibroDAO;

public class ConsultaLibroController {

    LibroDAO dao = new LibroDAO();

    public ResultSet buscar(String texto){
        return dao.buscarLibros(texto);
    }
}