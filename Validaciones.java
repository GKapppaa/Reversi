import java.util.ArrayList;

public class Validaciones {
    public boolean esMovValido(Tablero tablero, int fila, int columna, Piezas color){
        return !obtenerCapturas(tablero, fila, columna, color).isEmpty();
    }

    public ArrayList<int[]> obtenerCapturas(Tablero tablero, int fila, int columna, Piezas color){
        int[] filaDir = {-1, -1, -1, 0, 0, 1, 1 ,1};
        int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};
        ArrayList<int[]> capturas = new ArrayList<>();
        Piezas colorPropio = color;
        Piezas colorRival = color == Piezas.NEGRA ? Piezas.BLANCA : Piezas.NEGRA;

        if (tablero.tablero[fila][columna] != Piezas.VACIA){
            return capturas;
        }

        for (int d = 0; d < 8; d++){
            int r = fila + filaDir[d];
            int c = columna + colDir[d];
            ArrayList<int[]> jugadores = new ArrayList<>();

            while (r >= 0 && r < 8 && c >= 0 && c < 8){
                if(tablero.tablero[r][c] == Piezas.VACIA){
                    break;
                }
                if (tablero.tablero[r][c] == colorRival){
                    jugadores.add(new int[]{r,c});
                }
                if (tablero.tablero[r][c] == colorPropio){
                    capturas.addAll(jugadores);
                    break;
                }
                r += filaDir[d];
                c += colDir[d];
            }
        }

        return capturas;
    }
    public boolean tieneMovimientosValidos(Tablero tablero){
        for (int i = 0; i < tablero.tablero.length; i++) {
            for (int j = 0; j < tablero.tablero.length; j++) {
                if (tablero.tablero[i][j] == Piezas.VACIA) {
                    if (esMovValido(tablero, i, j, Piezas.NEGRA) || esMovValido(tablero, i, j, Piezas.BLANCA)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String hayMovimientosJugador(Tablero tablero, Piezas color){
        for (int i = 0; i < tablero.tablero.length; i++) {
            for (int j = 0; j < tablero.tablero.length; j++) {
                if(tablero.tablero[i][j] == Piezas.VACIA){
                    if(esMovValido(tablero, i, j, color)){
                        return "Si tienes movimientos validos";
                    }
                }
            }           
        }
        return "No te quedan movimientos validos";
    }
}
