package pkg3enraya;

/**
 * Clase que representa el nivel máximo de Inteligencia Artificial (IA3).
 * Implementa una lógica de juego completa que incluye:
 * 1. Priorizar la victoria inmediata si es posible.
 * 2. Bloquear movimientos ganadores del adversario (Defensa).
 * 3. Aplicar estrategia de pesos si no hay jugadas críticas inmediatas.
 * * @author Alicia
 * @version 1.0 (Sprint 1)
 */
public class IA3 extends IA2 {

    /**
     * Constructor para la IA de nivel 3.
     * @param nombre Nombre identificativo para la IA.
     */
    public IA3 (String nombre) {
        super(nombre);
    }

    /**
     * Implementación avanzada del movimiento de la IA.
     * Sigue una jerarquía de decisiones:
     * Primero, intenta ganar. 
     * Segundo, si no puede ganar, intenta evitar que el oponente gane (bloqueo).
     * Tercero, si no hay jugadas, usa la mejor casilla por peso estratégico.
     * * @return El objeto Moviment óptimo tras analizar ataque y defensa.
     */
    @Override
    public Moviment moviment() {        
        // 1. INTENTO DE ATAQUE: ¿Puedo ganar yo en este turno?
        if (this.getMovimentGuanyador(super.getTorn()) != null) {
            return this.getMovimentGuanyador(super.getTorn());
        }
        
        // 2. INTENTO DE DEFENSA: ¿Va a ganar mi adversario? Si es así, lo bloqueo.
        if (this.getMovimentGuanyador(this.getAdversari()) != null) {
            return this.getMovimentGuanyador(this.getAdversari());
        }

        // 3. ESTRATEGIA: Si nadie está a punto de ganar, muevo en la mejor casilla disponible.
        return this.calcularMillorMoviment();
    }

}