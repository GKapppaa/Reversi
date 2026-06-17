import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        Tablero tablero = new Tablero();    
        Scanner datos = new Scanner(System.in);

        // ANOmbre del jugador 1
        System.out.println("Nombre del jugador 1: ");
        String nombreJ1 = datos.nextLine();
        while (nombreJ1.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacio");
            System.out.println("Ingrese el nombre del jugador 1: ");
            nombreJ1 = datos.nextLine();
        }

        // Nombre del jugador 2
        System.out.println("Nombre del jugador 2: ");
        String nombreJ2 = datos.nextLine();
        while(nombreJ2.trim().isEmpty()){
            System.out.println("El nombre no puede estar vacio");
            System.out.println("Ingrese el nombre el jugador 2: ");
            nombreJ2 = datos.nextLine();
        }

        Piezas colorJ1 = null;
        while (colorJ1 == null) {
            System.out.println("Jugador " + nombreJ1 + " que color de pieza quieres? (NEGRA/BLANCA)");
            String colorInput = datos.nextLine().trim().toUpperCase();
            try {
                colorJ1 = Piezas.valueOf(colorInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Color invalido. Elige NEGRA o BLANCA.");
            }
        }
        Turnos turnos = new Turnos(nombreJ1, nombreJ2, colorJ1, colorJ1, colorJ1);
        Validaciones validaciones = new Validaciones();
        
        while (true){
            tablero.mostrarTablero();

            System.out.println("Es tu turno: " + turnos.obtenerJugadorActual());
            System.out.println("Ingresa tu jugada (fila,columna): ");

            String jugada = datos.nextLine();
            String[]  partes = jugada.split(",");

            if (partes.length != 2){
                System.out.println("Formato incorrecto. Usa: fila,columna");
                continue;
            }
            int fila = -1;
            int columna = -1;
            try{
                fila = Integer.parseInt(partes[0].trim());
                columna = Integer.parseInt(partes[1].trim());
            }catch(Exception e){
                System.out.println("Tipo de respuesta invalida. Usa el siguiente ejemplo: (3,2)");
                continue;
            }

            if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8){
                System.out.println("Coordenadas fuera de tablero");
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
}
