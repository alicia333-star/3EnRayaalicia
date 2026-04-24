package pkg3enraya;
/**
 * Clase principal que inicia el juego del 3 en Raya.
 * Gestiona las instrucciones y el inicio de sesión.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class TresEnRaya {
    /**
     * Muestra por consola la bienvenida y las reglas del juego.
     * Muy importante para que el jugador conozca las penalizaciones antes de jugar.
     */
    public static void mostrarInstrucciones() {
        System.out.println("*********************************************************************************");
        System.out.println("*                                  3 EN RAYA                                    *");
        System.out.println("*********************************************************************************");
        System.out.println("*                                                                               *");
        System.out.println("*                                INSTRUCCIONES                                  *");
        System.out.println("*                                -------------                                  *");
        System.out.println("*                                                                               *");
        System.out.println("*                         No muevas fuera del tablero                           *");
        System.out.println("*                      No muevas en una casilla ocupada                         *");
        System.out.println("* Si mueves fuera del tablero o en una casilla ocupada perderás automáticamente *");
        System.out.println("*                                                                               *");
        System.out.println("*********************************************************************************");
    }
    /**
     * Método principal del programa.
     * Aparecen las instrucciones e instancia la sesión del juego.
     * * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        TresEnRaya.mostrarInstrucciones();
        Sessio sesion = new Sessio();
        String hora = sesion.consultarHora();
        sesion.setHoraInici(hora);
        sesion.iniciarSessio();     
    }
}