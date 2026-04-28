package dao;

import java.sql.*;
import modelo.Lector;
import util.ConexionBD;

public class LectorDAO {

    public int obtenerORegistrar(Lector lector){

        try{

            Connection con=ConexionBD.getConexion();

            // buscar lector
            String buscar="SELECT id FROM lector WHERE dni=?";
            PreparedStatement ps=con.prepareStatement(buscar);
            ps.setString(1, lector.getDni());

            ResultSet rs=ps.executeQuery();

            if(rs.next())
                return rs.getInt("id");

            // registrar lector
            String insertar=
              "INSERT INTO lector(dni,nombre) VALUES(?,?)";

            ps=con.prepareStatement(insertar,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, lector.getDni());
            ps.setString(2, lector.getNombre());

            ps.executeUpdate();

            rs=ps.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);

        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
}