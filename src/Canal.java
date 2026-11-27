import java.util.Arrays;

/**
 * El "canal" de transmisión: aquí se dañan los bits a propósito.
 *
 * DUEÑA: Persona 3.
 * La usa: Main (Persona 2).
 *
 * Según el profesor esta es probablemente la parte más difícil: se trabaja sobre la SEÑAL
 * (los niveles de Manchester), no sobre los bits de la trama.
 */
public class Canal {

    /**
     * Devuelve una COPIA de la señal con los niveles de las posiciones indicadas invertidos
     * (0 pasa a 1 y 1 pasa a 0). La señal original no se modifica.
     *
     * Las posiciones empiezan en 0 y Main ya garantiza que están en rango y sin repetir; aun así,
     * lanza IllegalArgumentException si alguna posición está fuera de 0 .. senal.length - 1.
     *
     * Imprime qué posiciones se alteraron y cuánto valía cada nivel antes y después.
     *
     * @param senal      niveles originales
     * @param posiciones índices de los niveles a invertir
     * @return señal alterada (arreglo nuevo, misma longitud)
     */
    public static int[] alterar(int[] senal, int[] posiciones) {
        if (senal == null) {
            throw new IllegalArgumentException("La senal no puede ser nula");
        }
        if (posiciones == null) {
            throw new IllegalArgumentException("El arreglo de posiciones no puede ser nulo");
        }
        for (int i = 0; i < senal.length; i++) {
            if (senal[i] != 0 && senal[i] != 1) {
                throw new IllegalArgumentException("Nivel invalido en la posicion " + i + ": " + senal[i]);
            }
        }
        for (int i = 0; i < posiciones.length; i++) {
            int pos = posiciones[i];
            if (pos < 0 || pos >= senal.length) {
                throw new IllegalArgumentException("Posicion fuera de rango: " + pos
                        + " (rango valido: 0 a " + (senal.length - 1) + ")");
            }
        }

        int[] alterada = Arrays.copyOf(senal, senal.length);

        if (posiciones.length == 0) {
            System.out.println("[Canal.alterar] No se indico ninguna posicion para alterar.");
            return alterada;
        }

        System.out.println("[Canal.alterar] Alterando " + posiciones.length + " nivel(es) de la senal:");
        for (int i = 0; i < posiciones.length; i++) {
            int pos = posiciones[i];
            int antes = alterada[pos];
            int despues = 1 - antes;
            alterada[pos] = despues;
            System.out.println("  posicion " + pos + ": " + antes + " -> " + despues);
        }

        return alterada;
    }

    private Canal() { }
}
