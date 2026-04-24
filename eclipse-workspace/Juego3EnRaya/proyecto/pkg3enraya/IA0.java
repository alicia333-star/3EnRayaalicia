package pkg3enraya;

/**
 * Clase que representa una Inteligencia Artificial de nivel básico (IA0).
 * Esta IA recorre el tablero fila por filay columna por columna, 
 *realizando su movimiento en la primera casilla vacía que encuentra.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class IA0 extends Jugador {
    /** Referencia al tablero de juego para que la IA pueda analizar las casillas libres */
    Taulell taulell; 
 
    /**
     * Constructor para la IA de nivel 0.
     * @param nombre Nombre identificativo para la IA.
     */
    public IA0(String nombre){
        super(nombre);
    }
    
    /**
     * Implementación de la lógica de movimiento de la IA0.
     * Escanea el tablero de izquierda a derecha y de arriba a abajo.
     * @return Un objeto Moviment con las coordenadas de la primera casilla libre encontrada, 
     * o null si no hay movimientos posibles.
     */
    @Override
    public Moviment moviment() {
        Moviment moviment = null;

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                // Comprueba si la casilla está vacía (-1 representa vacío)
                if (this.taulell.getCasilla(f, c) == -1) {
                    moviment = new Moviment(this, f, c);
                    return moviment; // Retorna inmediatamente al encontrar el primer hueco
                }
            }
        }
        
        return moviment;
    }
    
    /**
     * Asigna el tablero actual a la IA para que pueda realizar los análisis de movimiento.
     * @param t El objeto Taulell que se está utilizando en la partida.
     */
    public void setTaulell (Taulell t) {
        this.taulell = t;
    }
}