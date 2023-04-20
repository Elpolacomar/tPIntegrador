public class Pronostico {

    private String nombre;

    private String fase;

    private String nroRonda;

    private Equipo equipo1;
    private Equipo equipo2;
    private Integer resultado;

    private int aciertos;


    public Pronostico(String nombre, String fase, String nroRonda, String equipo1, String equipo2, String resultado) {
        this.nombre = nombre;
        this.fase = fase;
        this.nroRonda = nroRonda;
        this.equipo1 = new Equipo(equipo1);
        this.equipo2 = new Equipo(equipo2);
        this.resultado = Integer.valueOf(resultado);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public String getNroRonda() {
        return nroRonda;
    }

    public void setNroRonda(String nroRonda) {
        this.nroRonda = nroRonda;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public int getAciertos() {
        return aciertos;
    }

    public String resultadoPronosticosString(){
        String gaEmPi;
        if (resultado == 1){
            gaEmPi="gana "+ equipo1;
        } else if (resultado == 2) {
            gaEmPi="gana "+equipo2;
        }else {
            gaEmPi ="es Empate";
        }
        return gaEmPi;
    }

    @Override
    public String toString() {
        return "El pronóstico de "+nombre+
                " en la fase " + fase +
                " de la ronda " + nroRonda +
                " entre " + equipo1 +
                " vs. " + equipo2 +
                ", " + resultado ;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

}
