public class Partido {
    private String fase;
    private String ronda;
    private Pronostico pronostico1;
    private Equipo equipo1;
    private Equipo equipo2;
    private Integer golesEquipo1;
    private Integer golesEquipo2;

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    private Integer resultadoPartido;

    public Partido(String fase, String ronda, String equipo1, String equipo2, String golesEquipo1, String golesEquipo2) {
        this.fase = fase;
        this.ronda = ronda;
        this.equipo1 = new Equipo(equipo1);
        this.equipo2 = new Equipo(equipo2);
        this.golesEquipo1 = Integer.valueOf(golesEquipo1);
        this.golesEquipo2 = Integer.valueOf(golesEquipo2);
        this.resultadoPartido= resultado();
    }

    public Pronostico getPronostico1() {
        return pronostico1;
    }

    public void setPronostico1(Pronostico pronostico) {
        this.pronostico1 = pronostico;
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

    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public Integer getResultadoPartido() {
        return resultadoPartido;
    }

    public void setResultadoPartido(Integer resultadoPartido) {
        this.resultadoPartido = resultadoPartido;
    }
    public Integer resultado(){
        Integer gaEmPi;
        if (getGolesEquipo1() > getGolesEquipo2()){
            gaEmPi=1;
        } else if (getGolesEquipo1()< getGolesEquipo2()) {
            gaEmPi=2;
        }else {
            gaEmPi =3;
        }
        return gaEmPi;
    }
    public String resultadoPartidoString(){
        String gaEmPi;
        if (resultadoPartido == 1){
            gaEmPi="gana equipo "+ equipo1;
        } else if (resultadoPartido ==2) {
            gaEmPi="gana equipo "+equipo2;
        }else {
            gaEmPi ="Empate";
        }
        return gaEmPi;
    }

    @Override
    public String toString() {
        return "\n \n Fase: "+fase+"\n " + "Ronda: "+ronda+"\n " +"Partido entre : " + equipo1 + " y " + equipo2 + "\n finalizo " + golesEquipo1 + " a " + golesEquipo2 + " \n resultado : "+ getResultadoPartido() +" "+ resultadoPartidoString();
    }
}
