package pkg3enraya;

import java.util.*;
/**
 * Clase que gestiona el ciclo de vida de una partida.
 * Controla el menú principal, la creación de jugadores,
 * realiza el sorteo de turnos y el acceso al ranking de puntuaciones.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Sessio {

    /** Hora en la que se inició la sesión actual */
    private String horaInici;
    /** Array que contiene a los dos jugadores de la partida */
    private Jugador[] jugadors;
    /** Instancia de la partida en curso */
    private Partida partida;
    /** Registro de puntuaciones de los jugadores */
    private Ranking ranking;

    /**
     * Constructor de la clase Sessio.
     * Inicializa el ranking crea al jugador humano.
     */
    public Sessio() {
        this.ranking = new Ranking();
        this.jugadors = new Jugador[2];
        this.jugadors[0] = this.crearHumano();
    }

    /**
     * Solicita por consola el nombre del usuario y crea al Jugador.
     * @return otro Jugador con el nombre introducido.
     */
    private Jugador crearHumano() {
        String nombre;
        Scanner opcioTriada = new Scanner(System.in);
        System.out.print("                         ¿Cuál es tu nombre? --> ");
        nombre = opcioTriada.next();
        return new Jugador(nombre);
    }

    /**
     * Crea un jugador controlado por la CPU según el nivel de dificultad.
     * @param nivel Entero del 0 al 4 que define la dificultad de la IA.
     * @return Una instancia de IA correspondiente al nivel elegido.
     */
    private Jugador crearIA(int nivel) {
        Jugador jugadorIA = null;
        switch (nivel) {
            case 0: jugadorIA = new IA0("IA0"); break;
            case 1: jugadorIA = new IA1("IA1"); break;
            case 2: jugadorIA = new IA2("IA2"); break;
            case 3: jugadorIA = new IA3("IA3"); break;
            case 4: jugadorIA = new IA4("IA4"); break;
            default:
                jugadorIA = new IA4("IA4");
                System.out.println("\n------------------------ Jugarás en el nivel más difícil :-) --------------------\n");
                break;
        }
        return jugadorIA;
    }

    /**
     * Inicio de una nueva partida con los jugadores asignados.
     * @param j1 Primer jugador.
     * @param j2 Segundo jugador.
     * @return El objeto Partida creado.
     */
    private Partida crearPartida(Jugador j1, Jugador j2) {
        this.partida = new Partida(this.ranking, j1, j2);
        return this.partida;
    }

    /**
     * Muestra un mensaje al cerrar la sesión.
     */
    private void mostrarDespedida() {
        System.out.println("\n\n                      ************************************");
        System.out.println("                      * ¡Hasta la próxima!       *");
        System.out.println("                      ************************************");
        for (int i = 0; i < 10; i++) {
            System.out.println("                      * Adioooooooos          *");
        }
        System.out.println("                      ************************************");
    }

    /**
     * Informa al usuario de quién comienza la partida según el sorteo.
     * @param blanques true si el humano empieza, false si empieza la CPU.
     */
    private void mostrarTorn(boolean blanques) {
        System.out.println("\n*********************************************************************************");
        if (blanques) {
            System.out.println("* Te ha tocado jugar con blancas. Empiezas tú.                *");
        } else {
            System.out.println("* Te ha tocado jugar con negras. Empieza la CPU.              *");
        }
        System.out.println("*********************************************************************************");
    }

    /**
     * Configura los parámetros necesarios antes de empezar el juego:
     * Dificultad, creación de IA, sorteo de turnos y asignación de fichas.
     */
    private void prepararPartida() {
        boolean iaCreada = false;
        int nivellIA;

        while (!iaCreada) {
            try {
                nivellIA = this.triarDificultat();
                this.jugadors[1] = this.crearIA(nivellIA);
                iaCreada = true;
            } catch (InputMismatchException e) {
                System.out.println("\n-------------------- HAS DE PULSAR UN NÚMERO: 0, 1, 2, 3 o 4 --------------------\n");
            }
        }

        if (this.sorteigTorn()) {
            this.mostrarTorn(true);
            this.partida = this.crearPartida(this.jugadors[0], this.jugadors[1]);
        } else {
            this.mostrarTorn(false);
            this.partida = this.crearPartida(this.jugadors[1], this.jugadors[0]);
        }
        this.partida.setTurnos();
    }

    /**
     * Realiza un sorteo aleatorio para decidir quién lleva las fichas blancas.
     * @return true si el humano gana el sorteo, false en caso contrario.
     */
    private boolean sorteigTorn() {
        return ((int)(Math.random() * 10)) < 5;
    }

    /**
     * Solicita al jugador elegir la dificultad del oponente.
     * @return El nivel de dificultad elegido.
     */
    private int triarDificultat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n                               NIVEL DE DIFICULTAD\n                               -------------------");
        System.out.println("\n                                 0 - Muy fácil\n                                 1 - Fácil\n                                 2 - Medio\n                                 3 - Difícil\n                                 4 - Muy difícil\n");
        System.out.print("                         Elige el nivel de dificultad --> ");
        return sc.nextInt();
    }

    /**
     * Imprime el menú principal y captura la opción del usuario.
     * @return La opción del menú seleccionada.
     */
    private int verMenu() {
        System.out.println("\n\n\n*********************************************************************************");
        System.out.println("* Inicio de sesión: " + this.horaInici + "                             *");
        System.out.println("*********************************************************************************");
        System.out.println("* *");
        System.out.println("* MENU                                      *");
        System.out.println("* ----                                      *");
        System.out.println("* *");
        System.out.println("* 1- Nueva partida                                *");
        System.out.println("* 2- Ver Ranking                                 *");
        System.out.println("* 3- Cerrar sesión                                *");
        System.out.println("* *");
        System.out.println("*********************************************************************************");
        System.out.print("\n                        Elige una opción: 1, 2 o 3 --> ");

        return new Scanner(System.in).nextInt();
    }

    /**
     * Obtiene la hora actual del sistema formateada para la sesión.
     * @return String con la hora en formato HH'mm.
     */
    public String consultarHora() {
        Calendar calendario = new GregorianCalendar();
        int horas = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        String minutosS = (minutos < 10) ? "0" + minutos : Integer.toString(minutos);
        return horas + "'" + minutosS;
    }

    /**
     * Inicia el bucle principal de la sesión, permitiendo jugar, ver el ranking o salir.
     */
    public void iniciarSessio() {
        boolean sortir = false;
        while (!sortir) {
            try {
                int opcion = this.verMenu();
                switch (opcion) {
                    case 1:
                        this.prepararPartida();
                        this.partida.jugar();
                        break;
                    case 2:
                        this.ranking.mostrarRanking();
                        break;
                    case 3:
                        this.mostrarDespedida();
                        sortir = true;
                        break;
                    default:
                        System.out.println("\n----------------------------- Opció desconeguda!!! -------------------------------\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n----------------------- HAS DE PULSAR UN NÚMERO: 1, 2 o 3 -----------------------\n");
            }
        }
    }

    /**
     * Establece la hora de inicio de la sesión si no ha sido establecida previamente.
     * @param hora String con la hora de inicio.
     */
    public void setHoraInici(String hora) {
        if (this.horaInici == null) {
            this.horaInici = hora;
        }
    }
}