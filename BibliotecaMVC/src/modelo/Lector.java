package modelo;

public class Lector {

    private int id;
    private String dni;
    private String nombre;

    public Lector(){}

    public Lector(String dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }

    public String getDni(){ return dni; }
    public void setDni(String dni){ this.dni=dni; }

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre=nombre; }
}
