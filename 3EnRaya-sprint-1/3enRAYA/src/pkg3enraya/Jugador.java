package pkg3enraya;

import java.util.*;

/**
 * Clase que representa a un jugador humano en el juego del 3 en raya.
 * Gestiona la entrada de datos por consola para realizar movimientos
 * y almacena la información básica del jugador como su nombre y turno.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Jugador {

    /** El turno asignado al jugador */
    private int torn;
    /** El nombre o identificador del jugador */
    private String nombre;

    /**
     * Constructor de la clase Jugador.
     * @param nombre Nombre que se le asignará al jugador humano.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Solicita al usuario las fila y column por consola.
     * @return Un array de enteros con dos posiciones: [fila, columna].
     */
    private int[] demanarCasella() {
        int[] casella = new int[2];
        Scanner resposta = new Scanner(System.in);

        System.out.print("Elige fila ---> ");
        casella[0] = resposta.nextInt() - 1;
        System.out.print("Elige columna ---> ");
        casella[1] = resposta.nextInt() - 1;

        return casella;
    }

    /**
     * Calcula y devuelve el identificador del turno del oponente.
     * @return 1 si el jugador actual es el 0, o 0 si el jugador actual es el 1.
     */
    public int getAdversari() {
        int adversari;
        if (this.getTorn() == 0) {
            adversari = 1;
        } else {
            adversari = 0;
        }
        return adversari;
    }

    /**
     * Obtiene el nombre del jugador.
     * @return El nombre almacenado en el objeto.
     */
    public String getNom() {
        return this.nombre;
    }

    /**
     * Obtiene el turno actual del jugador.
     * @return El entero representativo del turno.
     */
    public int getTorn() {
        return this.torn;
    }

    /**
     * Gestiona la creación de un nuevo movimiento solicitando la casilla.
     * Incluye control de errores para asegurar que la entrada sea numérica.
     * @return Un objeto Moviment con las coordenadas elegidas.
     */
    public Moviment moviment() {
        int casella[];
        Moviment moviment = null;

        while (moviment == null) {
            try {
                casella = this.demanarCasella();
                moviment = new Moviment(this, casella[0], casella[1]);
            } catch (InputMismatchException e) {
                System.out.println("\n-------- SÓLO PUEDES PULSAR NÚMEROS --------\n");
            } 
        }

        return moviment;
    }

    /**
     * Asigna el turno al jugador.
     * @param torn El identificador del turno (0 o 1).
     */
    public void setTorn(int torn) {
        this.torn = torn;
    }
}
