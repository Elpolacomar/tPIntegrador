public class PartidoXRonda {
    private String nombre;
    private Integer CantidadDePartidos;

    public PartidoXRonda(String nombre, Integer cantidadDePartidos) {
        this.nombre = nombre;
        CantidadDePartidos = cantidadDePartidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadDePartidos() {
        return CantidadDePartidos;
    }

    public void setCantidadDePartidos(Integer cantidadDePartidos) {
        CantidadDePartidos = cantidadDePartidos;
    }

    @Override
    public String toString() {
        return "PartidoXRonda : " + nombre + ", CantidadDePartidos = " + CantidadDePartidos;
    }
}

