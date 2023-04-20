import java.util.List;

public class Participante {
    private String nombre;
    private Integer puntos;

    private List<Partido> partidos;

    public Participante(String nombre, Integer puntos, List<Partido> partidos) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.partidos = partidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                ", partidos=" + partidos +
                '}';
    }
}