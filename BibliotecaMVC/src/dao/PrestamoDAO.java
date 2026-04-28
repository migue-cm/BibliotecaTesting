package dao;

import java.sql.*;
import modelo.Prestamo;
import util.ConexionBD;

public class PrestamoDAO {

    public boolean registrarPrestamo(Prestamo p){

        String sql="""
        INSERT INTO prestamo
        (id_libro,id_lector,fecha_prestamo,estado)
        VALUES(?,?,?,?)
        """;

        try{

            Connection con=ConexionBD.getConexion();
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,p.getIdLibro());
            ps.setInt(2,p.getIdLector());
            ps.setDate(3,p.getFechaPrestamo());
            ps.setString(4,p.getEstado());

            ps.executeUpdate();

            // DESCONTAR STOCK
            PreparedStatement update=
            con.prepareStatement(
            "UPDATE libro SET cantidad=cantidad-1 WHERE id=?");

            update.setInt(1,p.getIdLibro());
            update.executeUpdate();

            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public ResultSet listarPrestamosActivos(){

        try{

            Connection con = ConexionBD.getConexion();

            String sql = """
                SELECT p.id,
                       l.titulo,
                       lec.nombre,
                       p.fecha_prestamo,
                       p.id_libro
                FROM prestamo p
                JOIN libro l ON l.id=p.id_libro
                JOIN lector lec ON lec.id=p.id_lector
                WHERE p.estado='ACTIVO'
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
    public boolean devolverLibro(int idPrestamo,int idLibro){

        try{

            Connection con = ConexionBD.getConexion();

            // actualizar préstamo
            String updatePrestamo = """
                UPDATE prestamo
                SET estado='DEVUELTO',
                    fecha_devolucion=?
                WHERE id=?
            """;

            PreparedStatement ps =
                con.prepareStatement(updatePrestamo);

            ps.setDate(1,
                new Date(System.currentTimeMillis()));

            ps.setInt(2,idPrestamo);

            ps.executeUpdate();

            // aumentar stock
            PreparedStatement updateLibro =
                con.prepareStatement(
                "UPDATE libro SET cantidad=cantidad+1 WHERE id=?");

            updateLibro.setInt(1,idLibro);
            updateLibro.executeUpdate();

            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public ResultSet listarPrestamos(String estado){

        try{

            Connection con = ConexionBD.getConexion();

            String sql = """
                SELECT p.id,
                       l.titulo,
                       lec.nombre,
                       p.fecha_prestamo,
                       p.fecha_devolucion,
                       p.estado
                FROM prestamo p
                JOIN libro l ON l.id=p.id_libro
                JOIN lector lec ON lec.id=p.id_lector
            """;

            if(!estado.equals("TODOS")){
                sql += " WHERE p.estado=?";
            }

            sql += " ORDER BY p.id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            if(!estado.equals("TODOS"))
                ps.setString(1,estado);

            return ps.executeQuery();

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
    
    
    
    
    
    
}
