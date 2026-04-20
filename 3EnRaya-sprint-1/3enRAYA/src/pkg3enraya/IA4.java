package pkg3enraya;

/**
 * Clase que representa el nivel de Inteligencia Artificial más avanzado (IA4).
 * Esta IA calcula en cada turno qué casillas tienen mayor potencial de formar una línea ganadora
 *  basándose en el estado actual del tablero y la posición del adversario.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class IA4 extends IA3 {

    /**
     * Constructor para la IA de nivel 4.
     * @param nombre Nombre identificativo para la IA.
     */
    public IA4(String nombre) {
        super(nombre);
    }

    /**
     * Incrementa el valor estratégico de las casillas en columnas donde el 
     * adversario aún no ha colocado ninguna ficha.
     * @param adversari Identificador del turno del oponente.
     * @param estrategia Matriz de pesos dinámicos a actualizar.
     */
    private void actualitzarColumnes(int adversari, int estrategia[][]) {
        for (int c = 0; c < 3; c++) {
            if (this.taulell.getCasilla(0, c) != adversari
                    && this.taulell.getCasilla(1, c) != adversari
                    && this.taulell.getCasilla(2, c) != adversari) {
                estrategia[0][c]++;
                estrategia[1][c]++;
                estrategia[2][c]++;
            }
        }
    }

    /**
     * Incrementa el valor estratégico de las casillas en las diagonales donde el 
     * adversario aún no ha colocado ninguna ficha.
     * @param adversari Identificador del turno del oponente.
     * @param estrategia Matriz de pesos dinámicos a actualizar.
     */
    private void actualitzarDiagonals(int adversari, int estrategia[][]) {
        // Diagonal principal
        if (this.taulell.getCasilla(0, 0) != adversari
                && this.taulell.getCasilla(1, 1) != adversari
                && this.taulell.getCasilla(2, 2) != adversari) {
            estrategia[0][0]++;
            estrategia[1][1]++;
            estrategia[2][2]++;
        }
        // Diagonal secundaria
        if (this.taulell.getCasilla(0, 2) != adversari
                && this.taulell.getCasilla(1, 1) != adversari
                && this.taulell.getCasilla(2, 0) != adversari) {
            estrategia[0][2]++;
            estrategia[1][1]++;
            estrategia[2][0]++;
        }
    }

    /**
     * Incrementa el valor estratégico de las casillas en filas donde el 
     * adversario aún no ha colocado ninguna ficha.
     * @param adversari Identificador del turno del oponente.
     * @param estrategia Matriz de pesos dinámicos a actualizar.
     */
    private void actualitzarFiles(int adversari, int estrategia[][]) {
        for (int f = 0; f < 3; f++) {
            if (this.taulell.getCasilla(f, 0) != adversari
                    && this.taulell.getCasilla(f, 1) != adversari
                    && this.taulell.getCasilla(f, 2) != adversari) {
                estrategia[f][0]++;
                estrategia[f][1]++;
                estrategia[f][2]++;
            }
        }
    }

    /**
     * Ejecuta la actualizaciónde estrategia evaluando filas, columnas y diagonales.
     * @param estrategia Matriz de pesos a procesar.
     */
    private void actualitzarValorCaselles(int estrategia[][]) {
        this.actualitzarFiles(this.getAdversari(), estrategia);
        this.actualitzarColumnes(this.getAdversari(), estrategia);
        this.actualitzarDiagonals(this.getAdversari(), estrategia);
    }

    /**
     * Implementación del movimiento para IA4.
     * Mantiene la prioridad de victoria y bloqueo de IA3, pero sustituye los 
     * pesos fijos por un cálculo dinámico de casillas con mayor potencial de éxito.
     * @return El objeto Moviment optimizado dinámicamente.
     */
    @Override
    public Moviment moviment() {
        int[][] estrategia;
        Moviment moviment;
        
        // Prioridad 1: Ganar si es posible
        if ((moviment = this.getMovimentGuanyador(super.getTorn())) != null) {
            return moviment; 
        }

        // Prioridad 2: Bloquear si el adversario va a ganar
        if ((moviment = this.getMovimentGuanyador(this.getAdversari())) != null) {
            return moviment; 
        }
        
        // Prioridad 3: Cálculo dinámico de la mejor casilla libre
        estrategia = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};        
        this.actualitzarValorCaselles(estrategia);        

        return this.calcularMillorMoviment(estrategia);
    }
}
