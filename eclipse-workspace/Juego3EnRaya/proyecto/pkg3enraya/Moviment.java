package pkg3enraya;

/**
 * Clase que representa un movimiento individual en el juego.
 * y la ficha asociada al movimiento.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Moviment {
    /** Tipo de ficha colocada (0 para O, 1 para X) */
    private int fitxa;
    /** Índice de la fila seleccionada (0-2) */
    private int fila;    
    /** Índice de la columna seleccionada (0-2) */
    private int columna;    
    /** Referencia al jugador que realiza la acción */
    private Jugador jugador; 
 
    /**
     * Constructor de la clase Moviment.
     * @param jugador El jugador que propone el movimiento.
     * @param fila Fila de destino.
     * @param columna Columna de destino.
     */
    public Moviment(Jugador jugador, int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.jugador = jugador;
    }
    
    /**
     * Obtiene el tipo de ficha del movimiento.
     * @return El entero que representante de la ficha.
     */
    public int getFitxa() {
       return this.fitxa;
    }
    
    /**
     * Obtiene el índice de la columna.
     * @return Entero de la columna seleccionada.
     */
    public int getColumna() {
       return this.columna;
    }
    
    /**
     * Obtiene el índice de la fila.
     * @return Entero de la fila seleccionada.
     */
    public int getFila() {
       return this.fila;
    }
    
    /**
     * Asigna la ficha a este movimiento.
     * @param f El valor de la ficha (0 o 1).
     */
    public void setFitxa(int f) {
        this.fitxa = f;
    }
}