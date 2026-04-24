package pkg3enraya;
/**
 * Clase que representa una Inteligencia Artificial de nivel 1 (IA1).
 * A diferencia de la IA0, esta versión es mas estrategica priorizando el centro y las esquinas.
 * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class IA1 extends IA0 {

    /** Matriz asigna un valor estratégico a cada casilla del tablero */
    private int[][] caselles;

    /**
     * Constructor para la IA de nivel 1.
     * Inicializa la matriz de estrategia con pesos (4 para el centro, 3 para esquinas, 2 para laterales).
     * @param nombre Nombre identificativo para la IA.
     */
    public IA1(String nombre) {
        super(nombre);
        this.caselles = new int[][]{
            {3, 2, 3},
            {2, 4, 2},
            {3, 2, 3}
        };
    }
    
    /**
     * Sobrecarga del método para calcular el mejor movimiento usando la estrategia interna.
     * @return El objeto Moviment considerado óptimo.
     */
    public Moviment calcularMillorMoviment(){
        return this.calcularMillorMoviment(this.caselles);
    }

    /**
     * Lógica principal de decisión de la IA1.
     * Busca la casilla vacía con el mayor peso estratégico. 
     * @param estrategia Matriz de pesos a utilizar para el cálculo.
     * @return El objeto Moviment seleccionado.
     */
    public Moviment calcularMillorMoviment(int estrategia[][]) {
        int[] casella = new int[2];
        int valorCasella;
        int millorValor = -1;
        Moviment moviment;

        for (int f = 0; f < this.caselles.length; f++) {
            for (int c = 0; c < this.caselles[0].length; c++) {
                Moviment mov = new Moviment(this, f, c);
                // Solo evalúa si la casilla está libre
                if (this.taulell.validarCasillaBuida(mov)) {
                    valorCasella = estrategia[f][c];
                    // Si encontramos una casilla mejor, la seleccionamos
                    if (valorCasella > millorValor) {
                        casella[0] = f;
                        casella[1] = c;
                        millorValor = valorCasella;
                    // Si el valor es igual, decidimos aleatoriamente si cambiarla
                    } else if ((valorCasella == millorValor) && this.canviarCasella()) {
                        casella[0] = f;
                        casella[1] = c;
                    }
                }
            }
        }
        moviment = new Moviment(this, casella[0], casella[1]);

        return moviment;
    }

    /**
     * Introduce un componente de azar en la decisión de la IA.
     * Genera un número aleatorio para decidir si se cambia una casilla seleccionada 
     * por otra de igual valor estratégico (50% de probabilidad).
     * @return true si se debe cambiar la casilla, false en caso contrario.
     */
    public boolean canviarCasella() {
        boolean canviar;
        double d;
        int i;
        d = Math.random() * 100;
        i = (int) d;
        canviar = i < 50;

        return canviar;
    }

    /**
     * Implementación del método de movimiento heredado de Jugador.
     * @return El movimiento calculado mediante la estrategia de pesos.
     */
    @Override
    public Moviment moviment() {
        return this.calcularMillorMoviment(this.caselles);
    }

}