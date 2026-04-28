package controlador;

import java.sql.ResultSet;
import dao.PrestamoDAO;

public class ListarPrestamoController {

    PrestamoDAO dao = new PrestamoDAO();

    public ResultSet listar(String estado){
        return dao.listarPrestamos(estado);
    }
}
