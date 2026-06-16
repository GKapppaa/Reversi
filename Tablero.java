public class Tablero {
    Piezas[][] tablero = new Piezas[8][8];
    public Tablero(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) tablero[i][j] = Piezas.VACIA;
        }
        tablero[3][3] = Piezas.NEGRA;
        tablero[4][4] = Piezas.NEGRA;
        tablero[3][4] = Piezas.BLANCA;
        tablero[4][3] = Piezas.BLANCA;
    }
    public void mostrarTablero(){
        System.out.println("   0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print("|" + convertirAChar(tablero[i][j]));
            }
            System.out.println("|");
        } 
      
    }

    private char convertirAChar(Piezas p){
        return switch(p){
            case NEGRA -> '●';
            case BLANCA -> 'O';
            case VACIA -> '-';
        };
    }
}
