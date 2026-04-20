package pkg3enraya;

/**
 * Clase que gestiona las estadísticas globales de las partidas.
 * Almacena y calcula el número de victorias, empates y derrotas
 * tanto para el jugador humano como para la Inteligencia Artificial.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class Ranking {     
    /** Contador total de empates registrados */
    private int empats;    
    /** Contador de victorias del jugador humano */
    private int guanyadesJugador;
    /** Contador total de partidas finalizadas */
    private int partidesJugades;
 
    /**
     * Constructor de la clase Ranking.
     * Inicializa todos los contadores de estadísticas a cero.
     */
    public Ranking(){
        this.partidesJugades = 0;
        this.guanyadesJugador = 0;
        this.empats = 0;
    }
    
    /**
     * Incrementa el contador de partidas jugadas y el de empates.
     */
    public void empatar() {
        this.partidesJugades++; 
        this.empats++;
    }
    
    /**
     * Registra una victoria.
     * Si el ganador no es la IA, se contabiliza como victoria humana.
     * @param j El objeto Jugador que ha ganado la partida.
     */
    public void guanyar(Jugador j) {
        this.partidesJugades++; 
        if(!(j instanceof IA0)) {
            this.guanyadesJugador++;
        }   
    }
    
    /**
     * Calcula las estadísticas actuales y las muestra por consola.
     */
    public void mostrarRanking() {
        int derrotasJugador = this.partidesJugades - this.empats - this.guanyadesJugador;
        
        System.out.println("");
        System.out.println("                           ***************************");
        System.out.println("                                      RANKING");
        System.out.println("                           ***************************");        
        System.out.println("");
        System.out.println("                               Partidas jugadas: " + this.partidesJugades);
        System.out.println("                                    Empates: " + this.empats);
        System.out.println("                               Victorias Humano: " + this.guanyadesJugador);
        System.out.println("                                Derrotas Humano: " + derrotasJugador);
        System.out.println("                                 Victorias IA: " + derrotasJugador);
        System.out.println("                                  Derrotas IA: " + this.guanyadesJugador);
    }
}
