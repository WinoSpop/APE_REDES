/**
 * Checksum de internet (suma de bloques de 16 bits con acarreo circular).
 *
 * DUEÑA: Persona 2.
 * La usan: Trama (Persona 2) y Receptor (Persona 3).
 *
 * PASOS DEL ALGORITMO:
 *  1. Partir los datos en bloques de 16 bits. Si el último queda corto, completarlo con ceros
 *     a la DERECHA (70 bits no son múltiplo de 16).
 *  2. Sumar todos los bloques. Si la suma se pasa de 16 bits, el acarreo se suma de nuevo
 *     al resultado (acarreo circular).
 *  3. Invertir todos los bits de la suma (complemento a uno). Ese es el checksum.
 *
 * Ejemplo para probar: datos = 0000000000000001 y 0000000000000010 (dos bloques)
 *   suma = 3 -> checksum = 1111111111111100
 */
public class Checksum {

    /**
     * Calcula el checksum. Imprime los pasos (bloques, suma parcial, resultado).
     *
     * @param datos bits del mensaje (entre 7 y 70 bits)
     * @return arreglo de Constantes.BITS_CHECKSUM (16) bits
     */
    public static int[] calcular(int[] datos) {
        throw new UnsupportedOperationException("Falta implementar Checksum.calcular (Persona 2)");
    }

    /**
     * Revisa si el checksum recibido corresponde a los datos recibidos.
     * Imprime los pasos de la verificación.
     *
     * @param datos    bits del mensaje tal como llegaron
     * @param checksum los 16 bits de checksum tal como llegaron
     * @return true si no se detecta error; false si se detecta error
     */
    public static boolean verificar(int[] datos, int[] checksum) {
        throw new UnsupportedOperationException("Falta implementar Checksum.verificar (Persona 2)");
    }

    private Checksum() { }
}
