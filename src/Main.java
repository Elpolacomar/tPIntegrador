import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Partido> resultados = leerResultados();
        List<Pronostico> pronosticos = leerPronosticos();

        compararResultados(pronosticos, resultados);
    }

    public static List<Partido> leerResultados() {
        List<Partido> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        List<Partido> partidosRonda =new ArrayList<>();
        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();
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
                Partido partido = new Partido(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5]);
                resultados.add(partido);

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }
        return resultados;
    }


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
                Pronostico pronostico = new Pronostico(fila[0], fila[1], fila[2], fila[3], fila[4], fila[5]);
                pronosticos.add(pronostico);

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }
        return pronosticos;

    }

    public static Participante[] compararResultados(List<Pronostico> pronosticos, List<Partido> resultados){

        Integer cantidadParticipantes= pronosticos.size() / resultados.size();
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                       Cantidad de Participantes : "+ cantidadParticipantes + "                         |");
        System.out.println("-------------------------------------------------------------------------------");

        List<Participante> participantes = new ArrayList<>();
        List<Partido> partidos= new ArrayList<>();
        for (int i = 0; i < cantidadParticipantes; i++){
            Integer count= 0;
            String nombre = pronosticos.get(resultados.size() * i).getNombre();
            for (int j=0; j< resultados.size(); j++ ){
                int resultadoParticipante = pronosticos.get((resultados.size() * i)+j).getResultado();
                int resultadopartido = resultados.get(j).getResultadoPartido();
                if(resultadoParticipante == resultadopartido){
                    Partido partido= new Partido(resultados.get(j).getFase(), resultados.get(j).getRonda(), resultados.get(j).getResultadoPartido());
                    partidos.add(partido);
                    count++;
                }
            }
            Participante participante= new Participante(nombre, count, partidos);
            participantes.add(participante);
        }
        System.out.println(participantes);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                                 ordenados por puntaje                       |");
        System.out.println("-------------------------------------------------------------------------------");

        Participante[] arrayOrdenado= new Participante[cantidadParticipantes];
        for (int i =0; i < cantidadParticipantes; i++){
            arrayOrdenado[i]=participantes.get(i);
        }
        for (int j = 0; j < (arrayOrdenado.length-1); j++) {
            for (int i = 0; i < (arrayOrdenado.length -1); i++) {
                if (arrayOrdenado[i].getPuntos() < arrayOrdenado[i + 1].getPuntos()) {
                    Participante auxiliar = arrayOrdenado[i];
                    arrayOrdenado[i] = arrayOrdenado[i + 1];
                    arrayOrdenado[i + 1] = auxiliar;
                }
            }
        }
        for (Participante item: arrayOrdenado
        ) {
            System.out.println(item);
        }
        System.out.println(" ---------------------------------------- ");
        /* ver por la fase*/
        List<PartidoXFase> comparar= List.of(puntosExtraPorFase(pronosticos, resultados));

        for(int i=0; i< arrayOrdenado.length; i++) {
            int contador = 0;
            for (int j = 0; j < comparar.size(); j++) {
                contador = 0;
                for (int h = 0; h < arrayOrdenado[i].getPuntos(); h++) {
                    if (arrayOrdenado[i].getPartidos().get(h).getFase().equals(comparar.get(j).getNombre())) {
                        contador++;
                    }
                }
                System.out.println( "Aciertos por Fase : "+ comparar.get(j).getNombre() + " de " + arrayOrdenado[i].getNombre() + "  " +contador);
                if (contador >= comparar.get(j).getCantidadDePartidos()){
                    System.out.println("suma 1 punto");
                }else{
                    System.out.println("no alcanza para recibir puntos extra necesita " + comparar.get(j).getCantidadDePartidos() + " aciertos para hacer una Fase perfecta");
                }
                System.out.println(" ---------------------------------------- ");
            }
        }
        System.out.println(" ---------------------------------------- ");





        puntosExtraPorRonda(pronosticos, resultados);






        return arrayOrdenado;
    }

    public static PartidoXFase[] puntosExtraPorFase(List<Pronostico> pronosticos, List<Partido> resultados) {

        /*busco cuantas fases hay*/

        List<String> cantidadDeFases = new ArrayList<>();
        for (int i = 0; i < resultados.size()-1; i++) {
            if (i == 0) {
                cantidadDeFases.add(resultados.get(i).getFase());
            } else if (!resultados.get(i).getFase().equals(resultados.get(i + 1).getFase())) {
                cantidadDeFases.add(resultados.get(i+1).getFase());
            }
        }

        /*busco cuantos partidos por fase tengo*/

        List<Integer> cantidadDePartidosEnFase = new ArrayList<>();
        for(int i=0; i < cantidadDeFases.size(); i++){
            Integer count = 0;
            for (int j=0; j < resultados.size(); j++){
                if (cantidadDeFases.get(i).equals(resultados.get(j).getFase())){
                    count++;
                }
            }
            cantidadDePartidosEnFase.add(count);
        }
        PartidoXFase[] partidosXFase= new PartidoXFase[cantidadDeFases.size()];
        for (int i=0; i< cantidadDeFases.size(); i++){
            PartidoXFase partidoXFase= new PartidoXFase(cantidadDeFases.get(i), cantidadDePartidosEnFase.get(i));
            partidosXFase[i]= partidoXFase;
        }

        for (PartidoXFase item:partidosXFase
        ) {
            System.out.println(item);
        }
        System.out.println(" ---------------------------------------- ");
        return partidosXFase;
    }

    public static void puntosExtraPorRonda(List<Pronostico> pronosticos, List<Partido> resultados){
        /*cantidad de partidos por ronda*/

        List<String> cantidadDeRondas = new ArrayList<>();
        for (int i = 0; i < resultados.size()-1; i++) {
            if (i == 0) {
                cantidadDeRondas.add(resultados.get(i).getRonda());
            } else if (!resultados.get(i).getRonda().equals(resultados.get(i + 1).getRonda())) {
                cantidadDeRondas.add(resultados.get(i+1).getRonda());
            }
        }

        /*busco cuantos partidos por Ronda tengo*/

        List<Integer> cantidadDePartidosPorRonda = new ArrayList<>();

        for(int i=0; i < cantidadDeRondas.size(); i++){
            Integer count = 0;
            for (int j=0; j < resultados.size(); j++){
                if (cantidadDeRondas.get(i).equals(resultados.get(j).getRonda())){
                    count++;
                }
            }
            cantidadDePartidosPorRonda.add(count);
        }

        PartidoXRonda[] partidosXRonda= new PartidoXRonda[cantidadDeRondas.size()];
        for (int i=0; i< cantidadDeRondas.size(); i++){
            PartidoXRonda partidoXRonda= new PartidoXRonda(cantidadDeRondas.get(i), cantidadDePartidosPorRonda.get(i));
            partidosXRonda[i]= partidoXRonda;
        }

        for (PartidoXRonda item:partidosXRonda
        ) {
            System.out.println(item);
        }

    }

    /*cierre del main*/
}
