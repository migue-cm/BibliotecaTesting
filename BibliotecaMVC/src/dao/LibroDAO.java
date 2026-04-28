package dao;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modelo.Libro;
import util.ConexionBD;

public class LibroDAO {

    public boolean registrarLibro(Libro libro) {

        String sql = """
            INSERT INTO libro
            (isbn,titulo,autor,editorial,anio,cantidad)
            VALUES(?,?,?,?,?,?)
        """;

        try {

            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getAnio());
            ps.setInt(6, libro.getCantidad());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error DAO: " + e);
            return false;
        }
    }
    
    
    public boolean existeISBN(String isbn){

        String sql = "SELECT isbn FROM libro WHERE isbn=?";

        try{

            var con = ConexionBD.getConexion();
            var ps = con.prepareStatement(sql);

            ps.setString(1, isbn);

            var rs = ps.executeQuery();

            return rs.next();

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public ResultSet buscarLibros(String texto){

        try{

            Connection con = ConexionBD.getConexion();

            String sql = """
                SELECT isbn,titulo,autor,editorial,
                       anio,cantidad
                FROM libro
                WHERE LOWER(titulo) LIKE LOWER(?)
                ORDER BY titulo
            """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,"%"+texto+"%");

            return ps.executeQuery();

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public ResultSet listarDisponibles(){

        try{
            Connection con=ConexionBD.getConexion();

            String sql="""
                SELECT id,isbn,titulo,autor,cantidad
                FROM libro
                WHERE cantidad>0
            """;

            PreparedStatement ps=con.prepareStatement(sql);

            return ps.executeQuery();

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
}