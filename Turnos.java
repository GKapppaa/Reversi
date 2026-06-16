public class Turnos {
    String nombreJ1;
    String nombreJ2;
    Piezas colorJ1;
    Piezas colorJ2;
    Piezas turnoActual;
    

    public Turnos(String nombreJ1, String nombreJ2, Piezas colorJ1, Piezas colorJ2, Piezas turnoActual) {
        this.nombreJ1 = nombreJ1;
        this.nombreJ2 = nombreJ2;
        this.colorJ1 = colorJ1;
        if (colorJ1 == Piezas.NEGRA){
            colorJ2 = Piezas.BLANCA;
        } else{
            colorJ2 = Piezas.NEGRA;
        }
        this.colorJ2 = colorJ2;
        this.turnoActual = Piezas.NEGRA;
    }

    public Piezas obtenerColorActual(){
        return turnoActual;
    }
    public String obtenerJugadorActual(){
        if(turnoActual == colorJ1){
            return nombreJ1;
        }else{
            return nombreJ2;
        }
    }

    public void cambiarTurno(){
        if (turnoActual == Piezas.NEGRA){
            turnoActual = Piezas.BLANCA;
        } else {
            turnoActual = Piezas.NEGRA;
        }
    }

    public void contarFichas(Tablero tablero){
        int negras = 0;
        int blancas = 0;
        for (int i = 0; i < tablero.tablero.length; i++) {
            for (int j = 0; j < tablero.tablero.length; j++) {
                if (tablero.tablero[i][j] == Piezas.NEGRA) negras++;
                if(tablero.tablero[i][j] == Piezas.BLANCA) blancas++;              
            }
        }
        System.out.println("Cantidad de fichas negras: " + negras);
        System.out.println("Cantidad de fichas blancas: " + blancas);
    }

    public void mostrarGanador(Tablero tablero){
        int negras = 0;
        int blancas = 0;
        for (int i = 0; i < tablero.tablero.length; i++) {
            for (int j = 0; j < tablero.tablero.length; j++) {
                if (tablero.tablero[i][j] == Piezas.NEGRA) negras++;
                if(tablero.tablero[i][j] == Piezas.BLANCA) blancas++;              
            }
        }
        System.out.println("Cantidad de fichas negras: " + negras);
        System.out.println("Cantidad de fichas blancas: " + blancas);

        if (negras > blancas){
            String ganador = (colorJ1 == Piezas.NEGRA) ? nombreJ1 : nombreJ2;
            System.out.println("Ganador: " + ganador + "(Negras)");
        } else if (blancas > negras){
            String ganador = (colorJ1 == Piezas.BLANCA) ? nombreJ1 : nombreJ2;
            System.out.println("Ganador: " + ganador + "(Blancas)");
        }
    }


}
