package controlador;

import dao.LibroDAO;
import modelo.Libro;

public class LibroController {

    private LibroDAO dao = new LibroDAO();

    public boolean registrarLibro(Libro libro) {
        return dao.registrarLibro(libro);
    }
    
    public boolean existeISBN(String isbn){
        return dao.existeISBN(isbn);
    }
    
}