import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Tablero tablero = new Tablero();    
        Scanner datos = new Scanner(System.in);
        // ANOmbre del jugador 1
        System.out.println("Nombre del jugador 1: ");
        String nombreJ1 = datos.nextLine();

        // Nombre del jugador 2
        System.out.println("Nombre del jugador 2: ");
        String nombreJ2 = datos.nextLine();

        System.out.println("Jugador " + nombreJ1 + " que color de pieza quieres? (NEGRA/BLANCA)");
        Piezas colorJ1 = Piezas.valueOf(datos.nextLine().toUpperCase());

        Turnos turnos = new Turnos(nombreJ1, nombreJ2, colorJ1, colorJ1, colorJ1);
        Validaciones validaciones = new Validaciones();
        
        while (true){
            tablero.mostrarTablero();

            System.out.println("Es tu turno: " + turnos.obtenerJugadorActual());
            System.out.println("Ingresa tu jugada (fila,columna): ");

            String jugada = datos.nextLine();
            String[]  partes = jugada.split(",");
            int fila = -1;
            int columna = -1;
            try{
                fila = Integer.parseInt(partes[0].trim());
                columna = Integer.parseInt(partes[1].trim());
            }catch(Exception e){
                System.out.println("Tipo de respuesta invalida. Usa el siguiente ejemplo: (3,2)");
                continue;
            }
        
            if(validaciones.esMovValido(tablero, fila, columna, turnos.obtenerColorActual())){
                ArrayList<int[]> capturas = validaciones.obtenerCapturas(tablero, fila, columna, turnos.obtenerColorActual());

                tablero.tablero[fila][columna] = turnos.obtenerColorActual();
        
                for(int[] pos : capturas){
                    tablero.tablero[pos[0]][pos[1]] = turnos.obtenerColorActual();
                }
                turnos.contarFichas(tablero);
                turnos.cambiarTurno();
                if (validaciones.hayMovimientosJugador(tablero, turnos.obtenerColorActual()).equals("No te quedan movimientos validos")) {
                    System.out.println(turnos.obtenerJugadorActual() + " no puede mover. Pasa turno.");
                    turnos.cambiarTurno();
                    if (validaciones.hayMovimientosJugador(tablero, turnos.obtenerColorActual()).equals("No te quedan movimientos validos")) {
                        System.out.println("Fin del juego. Nadie puede mover.");
                        turnos.mostrarGanador(tablero);
                        break;
                    }
                }

            } else {
                System.out.println("Jugada invalida. Haga otro movimiento");
            }      
        }
    }
    // Error en el imprimir el ganador
}
