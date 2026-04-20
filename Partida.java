package pkg3enraya;

/**
 * Clase que controla el desarrollo de una partida individual.
 * Gestiona los turnos, la comunicación entre los jugadores y el tablero,
 * y actualiza el ranking al finalizar el encuentro.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Partida {

    /** Contador de la jugada actual */
    private int jugadaActual;
  
    private Jugador[] jugadors;
    /** Referencia al registro de estadísticas */
    private Ranking ranking;
    /** Referencia al tablero donde se desarrolla la acción */
    private Taulell taulell;

    /**
     * Constructor de la clase Partida.
     * Inicia el tablero, asigna los jugadores y prepara el inicio.
     * * @param r El ranking donde se guardarán los resultados.
     * @param j1 Primer jugador (tras el sorteo).
     * @param j2 Segundo jugador (tras el sorteo).
     */
    public Partida(Ranking r, Jugador j1, Jugador j2) {
        this.ranking = r;
        this.crearTaulell();

        /** Aseguramos que la IA tenga acceso al tablero para decidir sus movimientos
        */ 
        if (j1 instanceof IA0) {
            ((IA0) j1).setTaulell(this.taulell);
        } else {
            ((IA0) j2).setTaulell(this.taulell);
        }
        jugadors = new Jugador[2];
        this.addJugadores(j1, j2);
        this.jugadaActual = 0;
    }

    /**
     * Añade los jugadoresa la la partida.
     * @param j1 Jugador 1.
     * @param j2 Jugador 2.
     */
    private void addJugadores(Jugador j1, Jugador j2) {
        this.jugadors[0] = j1;
        this.jugadors[1] = j2;
    }

    /**
     * Inicia un nuevo tablero.
     */
    private void crearTaulell() {
        this.taulell = new Taulell();
    }

    /**
     * Procesa la victoria de un jugador, muestra el tablero final y actualiza el ranking.
     */
    private void gestionarGuanyador(int tornActual) {
        this.taulell.mostrarTaulell();
        this.mostrarGuanyador(tornActual);
        this.ranking.guanyar(this.jugadors[tornActual]);
    }

    /**
     * Finaliza la partida si un jugador intenta realizar un movimiento ilegal.
     * La victoria se le otorga automáticamente al rival.
     * @param torn Índice del jugador que cometió el error.
     */
    private void gestionarMovimentIncorrecte(int torn) {
        int adversari = this.jugadors[torn].getAdversari();
        System.out.println("\n                        El movimiento propuesto no es válido.");
        this.mostrarGuanyador(adversari);
        this.ranking.guanyar(this.jugadors[adversari]);
    }

    /**
     * Gestiona el escenario donde no hay más movimientos posibles y el tablero está lleno.
     */
    private void gestionarTablas() {
        this.taulell.mostrarTaulell();
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("                        No hay más movimientos posibles.");
        System.out.println("                       La partida ha terminado en tablas.");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        this.ranking.empatar();
    }

    /**
     * Imprime por consola el nombre del ganador.
     * @param guanyador Índice del jugador en el array.
     */
    private void mostrarGuanyador(int guanyador) {
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("                       El ganador de la partida es " + this.jugadors[guanyador].getNom());
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    /**
     * Muestra la información del turno actual: número de jugada y quién mueve.
     * @param torn Índice del jugador que debe mover.
     */
    private void mostrarInfoTorn(int torn) {
        System.out.println("\n                             ______________________\n");
        System.out.println("                                     Jugada nº " + this.jugadaActual);
        System.out.println("                                     Mueve: " + this.jugadors[torn].getNom() + "\n");
        System.out.println("                                     " + this.jugadors[0].getNom() + " ----> O");
        System.out.println("                                     " + this.jugadors[1].getNom() + " ----> X");
    }

    /**
     * Bucle principal de la partida.
     * Se ejecuta mientras no haya ganador, el tablero no esté lleno y los movimientos sean válidos.
     */
    public void jugar() {
        boolean movimentValid = true;
        int tornActual = 0;
        Moviment moviment;

        while (((this.taulell.comprovarGuanyador() == -1)
                && !this.taulell.comprovarPle()) && movimentValid) {
            
            this.jugadaActual++;
            tornActual = (this.jugadaActual + 1) % 2;
            this.mostrarInfoTorn(tornActual);
            this.taulell.mostrarTaulell();

            moviment = jugadors[tornActual].moviment();
            moviment.setFitxa(tornActual);

            if (this.taulell.validarMoviment(moviment)) {
                this.taulell.moure(moviment);
            } else {
                movimentValid = false;
                this.gestionarMovimentIncorrecte(tornActual);
            }
        }
        
        if (this.taulell.comprovarGuanyador() != -1) {
            this.gestionarGuanyador(tornActual);
        }
        if ((this.taulell.comprovarGuanyador() == -1) && this.taulell.comprovarPle()) {
            this.gestionarTablas();
        }
    }

    /**
     * Asigna el turno a cada jugador al inicio de la partida.
     */
    public void setTurnos() {
        this.jugadors[0].setTorn(0);
        this.jugadors[1].setTorn(1);
    }
}
