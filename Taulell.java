package pkg3enraya;
/**
 * Clase que gestiona el tablero del juego 3 en Raya.
 * Se encarga de la creación de la cuadrícula y de la validación de movimientos,
 * visualización por consola.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Taulell {
/** Matriz de enteros que representa las casillas: 0 para O, 1 para X, -1 para libre */
    //  0 ----> BLANCAS
    //  1 ----> NEGRAS
    // -1 ----> casilla libre
    private int[][] caselles;
    /**
     * Inicializa un tablero de 3x3 
     * Estableciendo todas las casillas a -1.
     */
    public Taulell() {
        this.caselles = new int[3][3];

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                this.caselles[f][c] = -1;
            }
        }
    }
    /**
     * Comprueba si algún jugador ha completado una columna.
     * @return El identificador del jugador o -1 si no hay ganador.
     */
    private int comprovarGuanyadorColumnes() {
        // Comprovar ses columnes
        for (int c = 0; c < 3; c++) {
            if ((this.caselles[0][c] != -1) && (this.caselles[0][c] == this.caselles[1][c])
                    && (this.caselles[1][c] == this.caselles[2][c])) {
                return this.caselles[0][c];
            }
        }

        return -1;
    }
    /**
     * Comprueba si algún jugador ha completado una diagonal.
     * @return El identificador del jugador o -1 si no hay ganador.
     */
    private int comprovarGuanyadorDiagonals() {
        // Comprovar ses diagonals

        if ((this.caselles[0][0] != -1) && (this.caselles[0][0] == this.caselles[1][1])
                && (this.caselles[1][1] == this.caselles[2][2])) {
            return this.caselles[0][0];
        }

        if ((this.caselles[0][2] != -1) && (this.caselles[0][2] == this.caselles[1][1])
                && (this.caselles[1][1] == this.caselles[2][0])) {
            return this.caselles[0][2];
        }

        return -1;
    }
   /**
     * Comprueba si algún jugador ha completado una fila.
     * @return El identificador del jugador o -1 si no hay ganador.
     */ 
    private int comprovarGuanyadorFiles() {
        // Comprovar ses files
        for (int f = 0; f < 3; f++) {
            if ((this.caselles[f][0] != -1) && (this.caselles[f][0] == this.caselles[f][1])
                    && (this.caselles[f][1] == this.caselles[f][2])) {
                return this.caselles[f][0];
            }
        }

        return -1;
    }
    /**
     * Comprueba de forma global si hay un ganador en filas, columnas o diagonales.
     * @return El identificador del jugador ganador o -1 si nadie ha ganado todavía.
     */
    public int comprovarGuanyador() {
        int jugador;

        jugador = this.comprovarGuanyadorFiles();
        if (jugador == -1) {
            jugador = this.comprovarGuanyadorColumnes();
        }
        if (jugador == -1) {
            jugador = this.comprovarGuanyadorDiagonals();
        }

        return jugador;
    }
    /**
     * Comprueba si el tablero está totalmente ocupado sin que haya un ganador.
     * @return true si el tablero está lleno, false en caso contrario.
     */
    public boolean comprovarPle() {
        boolean lleno = true;

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (caselles[f][c] == -1) {
                    lleno = false;
                }
            }
        }

        return lleno;
    }
    /**
     * Obtiene el valor de una casilla específica.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return El valor almacenado.
     */
    public int getCasilla(int fila, int columna) {
        int valor;
        valor = this.caselles[fila][columna];
        
        return valor;
    }   
    /**
     * Dibuja el tablero por consola.
     * Representa las fichas 0 como 'O' y las fichas 1 como 'X'.
     */
    public void mostrarTaulell() {
        System.out.println("");
        System.out.println("                                        1 2 3");
        System.out.println("                                        - - -");
        for (int i = 0; i < 3; i++) {
            System.out.print("                                     " + (i + 1) + "|");
            for (int j = 0; j < 3; j++) {
                switch (caselles[i][j]) {
                    case -1:
                        System.out.print("  ");
                        break;
                    case 0:
                        System.out.print(" O");
                        break;
                    default:
                        System.out.print(" X");
                        break;
                }
            }
            System.out.println("");
        }
    }
    /**
     * Registra el movimiento de un jugador en el tablero.
     * @param moviment Objeto que contiene las coordenadas y el tipo de ficha.
     */
    public void moure(Moviment moviment) {
        if (moviment.getFitxa() == 0) {
            this.caselles[moviment.getFila()][moviment.getColumna()] = 0;
        } else {
            this.caselles[moviment.getFila()][moviment.getColumna()] = 1;
        }
    }
    /**
     * Comprueba si una posición específica está libre.
     * @param moviment El movimiento a validar.
     * @return true si la casilla es -1, false si ya está ocupada.
     */
    public boolean validarCasillaBuida(Moviment moviment) {
        return (this.caselles[moviment.getFila()][moviment.getColumna()] == -1);
    }
    /**
     * Realiza una validación completa de un movimiento.
     * Comprueba que esté dentro de los límites y que la casilla esté vacía.
     * @param moviment El movimiento a validar.
     * @return true si el movimiento es legal, false en caso contrario.
     */
    public boolean validarMoviment(Moviment moviment) {

        return !(moviment.getFila() > 2 || moviment.getFila() < 0 || moviment.getColumna() > 2
                || moviment.getColumna() < 0 || this.validarCasillaBuida(moviment) == false);
    }

}
