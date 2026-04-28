package modelo;

import java.sql.Date;

public class Prestamo {

    private int idLibro;
    private int idLector;
    private Date fechaPrestamo;
    private String estado;

    public Prestamo(int idLibro,int idLector,
                    Date fechaPrestamo,String estado){

        this.idLibro=idLibro;
        this.idLector=idLector;
        this.fechaPrestamo=fechaPrestamo;
        this.estado=estado;
    }

    public int getIdLibro(){ return idLibro; }
    public int getIdLector(){ return idLector; }
    public Date getFechaPrestamo(){ return fechaPrestamo; }
    public String getEstado(){ return estado; }
}
