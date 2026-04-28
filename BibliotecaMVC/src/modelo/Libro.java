package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConexionBD;

public class Libro {

    private int id;
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private int cantidad;

    public Libro() {}

    public Libro(String isbn, String titulo, String autor,
                 String editorial, int anio, int cantidad) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.cantidad = cantidad;
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

    // GETTERS Y SETTERS
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
