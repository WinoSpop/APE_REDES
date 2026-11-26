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
        throw new UnsupportedOperationException("Falta implementar Canal.alterar (Persona 3)");
    }

    private Canal() { }
}
