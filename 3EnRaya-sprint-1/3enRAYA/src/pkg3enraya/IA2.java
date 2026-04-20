package pkg3enraya;

/**
 * Clase que representa una Inteligencia Artificial de nivel 2 (IA2).
 * Esta versión antes de mover por estrategia de pesos, comprueba si puede ganar la partida 
 * en el turno actual revisando filas, columnas y diagonales.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class IA2 extends IA1 {

    /**
     * Constructor para la IA de nivel 2.
     * @param nombre Nombre identificativo para la IA.
     */
    public IA2(String nombre) {
        super(nombre);
    }

    /**
     * Busca una columna donde haya dos fichas del mismo tipo y una casilla vacía.
     * @param fitxa El tipo de ficha a comprobar.
     * @return Objeto Moviment para completar la columna, o null si no hay ninguna.
     */
    private Moviment getColumnaGuanyadora(int fitxa) {
        Moviment moviment = null;

        for (int c = 0; c < 3; c++) {
            if (this.taulell.getCasilla(0, c) == fitxa 
                    && this.taulell.getCasilla(1, c) == fitxa 
                    && this.taulell.getCasilla(2, c) == -1) {
                moviment = new Moviment(this, 2, c);
                return moviment;
            }
            if (this.taulell.getCasilla(0, c) == fitxa 
                    && this.taulell.getCasilla(2, c) == fitxa 
                    && this.taulell.getCasilla(1, c) == -1) {
                moviment = new Moviment(this, 1, c);
                return moviment;
            }
            if (this.taulell.getCasilla(1, c) == fitxa 
                    && this.taulell.getCasilla(2, c) == fitxa 
                    && this.taulell.getCasilla(0, c) == -1) {
                moviment = new Moviment(this, 0, c);
                return moviment;
            }
        }

        return moviment;
    }

    /**
     * Busca una diagonal donde haya dos fichas del mismo tipo y una casilla vacía.
     * Cubre tanto la diagonal principal como la secundaria.
     * @param fitxa El tipo de ficha a comprobar.
     * @return Objeto Moviment para completar la diagonal, o null si no hay ninguna.
     */
    private Moviment getDiagonalGuanyadora(int fitxa) {
        Moviment moviment = null;

        // Diagonal Principal (0,0 a 2,2)
        if (this.taulell.getCasilla(0, 0) == fitxa 
                && this.taulell.getCasilla(1, 1) == fitxa 
                && this.taulell.getCasilla(2, 2) == -1) {
            moviment = new Moviment(this, 2, 2);
            return moviment;
        }
        if (this.taulell.getCasilla(0, 0) == fitxa 
                && this.taulell.getCasilla(2, 2) == fitxa 
                && this.taulell.getCasilla(1, 1) == -1) {
            moviment = new Moviment(this, 1, 1);
            return moviment;
        }
        if (this.taulell.getCasilla(1, 1) == fitxa 
                && this.taulell.getCasilla(2, 2) == fitxa 
                && this.taulell.getCasilla(0, 0) == -1) {
            moviment = new Moviment(this, 0, 0);
            return moviment;
        }

        // Diagonal Secundaria (0,2 a 2,0)
        if (this.taulell.getCasilla(0, 2) == fitxa 
                && this.taulell.getCasilla(1, 1) == fitxa 
                && this.taulell.getCasilla(2, 0) == -1) {
            moviment = new Moviment(this, 2, 0);
            return moviment;
        }
        if (this.taulell.getCasilla(0, 2) == fitxa 
                && this.taulell.getCasilla(2, 0) == fitxa 
                && this.taulell.getCasilla(1, 1) == -1) {
            moviment = new Moviment(this, 1, 1);
            return moviment;
        }
        if (this.taulell.getCasilla(1, 1) == fitxa 
                && this.taulell.getCasilla(2, 0) == fitxa 
                && this.taulell.getCasilla(0, 2) == -1) {
            moviment = new Moviment(this, 0, 2);
            return moviment;
        }

        return moviment;
    }
    
    /**
     * Busca una fila donde haya dos fichas del mismo tipo y una casilla vacía.
     * @param fitxa El tipo de ficha a comprobar.
     * @return Objeto Moviment para completar la fila, o null si no hay ninguna.
     */
    private Moviment getFilaGuanyadora(int fitxa) {
        Moviment moviment = null;

        for (int f = 0; f < 3; f++) {
            if (this.taulell.getCasilla(f, 0) == fitxa 
                    && this.taulell.getCasilla(f, 1) == fitxa 
                    && this.taulell.getCasilla(f, 2) == -1) {
                moviment = new Moviment(this, f, 2);
                return moviment;
            }
            if (this.taulell.getCasilla(f, 0) == fitxa 
                    && this.taulell.getCasilla(f, 2) == fitxa 
                    && this.taulell.getCasilla(f, 1) == -1) {
                moviment = new Moviment(this, f, 1);
                return moviment;
            }
            if (this.taulell.getCasilla(f, 1) == fitxa 
                    && this.taulell.getCasilla(f, 2) == fitxa 
                    && this.taulell.get
