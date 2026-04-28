package controlador;

import java.sql.ResultSet;
import dao.PrestamoDAO;

public class DevolucionController {

    PrestamoDAO dao = new PrestamoDAO();

    public ResultSet listarActivos(){
        return dao.listarPrestamosActivos();
    }

    public boolean devolver(int idPrestamo,int idLibro){
        return dao.devolverLibro(idPrestamo,idLibro);
    }
}
