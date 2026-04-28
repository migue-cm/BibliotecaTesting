package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static Connection conexion;

    public static Connection getConexion() {

        try {

            if(conexion == null || conexion.isClosed()) {

                String url = "jdbc:postgresql://localhost:5432/bibliotecaa";
                String user = "postgres";
                String password = "root"; // cambia tu password

                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(url, user, password);

                System.out.println("Conexion exitosa");

            }

        } catch (Exception e) {
            System.out.println("Error conexion: " + e);
        }

        return conexion;
    }
}