/**
 * CRC (división de bits con XOR; el residuo viaja junto al mensaje).
 *
 * DUEÑA: Persona 2.
 * La usan: Trama (Persona 2) y Receptor (Persona 3).
 *
 * El polinomio está en Constantes.GENERADOR_CRC (por ahora PROVISIONAL: CRC-16).
 * Confírmenlo con el ejercicio de las diapositivas del profesor.
 *
 * PASOS DEL ALGORITMO:
 *  1. Al mensaje se le agregan BITS_CRC ceros al final.
 *  2. Se divide entre el generador usando XOR en vez de restas.
 *  3. El residuo (BITS_CRC bits) es el CRC.
 *  4. El receptor puede verificar de dos formas equivalentes: recalcular el CRC de los datos y
 *     compararlo con el recibido, o dividir (datos + crc) y comprobar que el residuo sea 0.
 *
 * Ejemplo para probar (solo vale con el polinomio provisional): los 9 caracteres "123456789"
 * escritos con 8 bits cada uno (OJO: 8, no 7, solo para esta prueba) dan el CRC 0x31C3 =
 * 0011000111000011.
 */
public class CRC {

    /**
     * Calcula el CRC. Imprime los pasos de la división.
     *
     * @param datos bits del mensaje (entre 7 y 70 bits)
     * @return arreglo de Constantes.BITS_CRC bits
     */
    public static int[] calcular(int[] datos) {
        throw new UnsupportedOperationException("Falta implementar CRC.calcular (Persona 2)");
    }

    /**
     * Revisa si el CRC recibido corresponde a los datos recibidos. Imprime los pasos.
     * Recuerda: el CRC solo DETECTA errores, no los corrige.
     *
     * @param datos bits del mensaje tal como llegaron
     * @param crc   los bits de CRC tal como llegaron
     * @return true si no se detecta error; false si se detecta error
     */
    public static boolean verificar(int[] datos, int[] crc) {
        throw new UnsupportedOperationException("Falta implementar CRC.verificar (Persona 2)");
    }

    private CRC() { }
}
