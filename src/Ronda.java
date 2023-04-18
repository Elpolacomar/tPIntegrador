import java.util.List;

public class Ronda {

    private Integer ronda;

    private List<Partido> partidos;

    public static Integer count = 1;
    public Ronda(String fase, Integer ronda, List<Partido> partidos) {
        this.ronda = ronda;
        this.partidos = partidos;
    }

    public Ronda() {
    }

    public Integer getRonda() {
        return ronda;
    }

    public void setRonda(Integer ronda) {
        this.ronda = ronda;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public static Integer getCount() {
        return count;
    }

    public static void setCount(Integer count) {
        Ronda.count = count;
    }

    @Override
    public String toString() {
        return "\n Ronda "+ ronda +
                " partidos=" + partidos;
    }
}
