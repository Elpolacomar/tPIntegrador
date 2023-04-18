import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {

        List<Ronda> resultados = leerResultados();
        System.out.println("---------------------------------------------");
        List<Pronostico> pronosticos = leerPronosticos();

        /*int[] puntos = new int[pronosticos.size()];
        int i = 0;
        for (List<Pronostico> pronosticosUsuario : pronosticos.indexOf(args[5])) {
            int puntosUsuario = 0;
            boolean aciertosCompletos = true;
            for (int j = 0; j < resultados.size(); j++) {
                Partido partido = resultados.get(j);
                Pronostico pronostico = pronosticosUsuario.get(j);
                if (pronostico.aciertaResultado(partido)) {
                    puntosUsuario++;
                } else {
                    aciertosCompletos = false;
                }
            }
            if (aciertosCompletos) {
                puntosUsuario += 5; // puntos extra por acertar todos los resultados de la ronda
            }
            puntos[i++] = puntosUsuario;
        }*/

    }

    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Ronda
    // Posicion 1: Fase
    // Posicion 2: Nombre equipo 1
    // Posicion 3: Nombre equipo 2
    // Posicion 4: Goles equipo 1
    // Posicion 5: Goles equipo 2
    public static List<Ronda> leerResultados() {
        List<Ronda> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();
            List<Partido> partidosRonda = new ArrayList<>();
            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GOLES_1, GOLES_2 FROM RESULTADOS R JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("FASE");
                fila[1] = rs.getString("RONDA");
                fila[2] = rs.getString("EQUIPO_1");
                fila[3] = rs.getString("EQUIPO_2");
                fila[4] = rs.getString("GOLES_1");
                fila[5] = rs.getString("GOLES_2");
                Partido partido = new Partido(fila[0],fila[1],fila[2],fila[3],fila[4],fila[5]);
                partidosRonda.add(partido);
                System.out.println(partido);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }
        return resultados;
    }


    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Nombre de la persona
    // Posicion 1: Fase
    // Posicion 2: Ronda
    // Posicion 3: Nombre equipo 1
    // Posicion 4: Nombre equipo 2
    // Posicion 5: Ganador
    public static List<Pronostico> leerPronosticos() {
        List<Pronostico> pronosticos = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();
            List<Pronostico> pronosticoPartido = new ArrayList<>();
            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GANADOR FROM PRONOSTICOS P JOIN RESULTADOS R on P.ID_RESULTADO = R.ID_RESULTADO JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("NOMBRE");
                fila[1] = rs.getString("FASE");
                fila[2] = rs.getString("RONDA");
                fila[3] = rs.getString("EQUIPO_1");
                fila[4] = rs.getString("EQUIPO_2");
                fila[5] = rs.getString("GANADOR");
                Pronostico pronostico;
                pronostico = new Pronostico(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5]);
                pronosticoPartido.add(pronostico);
                System.out.println(pronostico);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }
        return pronosticos;
    }

}