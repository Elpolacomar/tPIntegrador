public class PartidoXFase {
    String nombre;
    Integer cantidadDePartidos;

    public PartidoXFase(String nombre, Integer cantidadDePartidos) {
        this.nombre = nombre;
        this.cantidadDePartidos = cantidadDePartidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadDePartidos() {
        return cantidadDePartidos;
    }

    public void setCantidadDePartidos(Integer cantidadDePartidos) {
        this.cantidadDePartidos = cantidadDePartidos;
    }

    @Override
    public String toString() {
        return "PartidoXFase :" + nombre + ", CantidadDePartidos = " + cantidadDePartidos ;

    }
}
