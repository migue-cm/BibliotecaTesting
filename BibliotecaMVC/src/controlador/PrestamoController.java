package controlador;


import java.sql.Date;

import dao.*;
import modelo.*;

public class PrestamoController {

    LectorDAO lectorDAO=new LectorDAO();
    PrestamoDAO prestamoDAO=new PrestamoDAO();

    public boolean prestarLibro(int idLibro,
                                String dni,
                                String nombre){

        int idLector=
            lectorDAO.obtenerORegistrar(
                new Lector(dni,nombre));

        Prestamo p=new Prestamo(
                idLibro,
                idLector,
                new Date(System.currentTimeMillis()),
                "ACTIVO");

        return prestamoDAO.registrarPrestamo(p);
    }
}
