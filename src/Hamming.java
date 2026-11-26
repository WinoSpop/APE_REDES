/**
 * Código de Hamming extendido: detecta hasta 2 errores y corrige 1.
 *
 * DUEÑA: Persona 1.
 * La usan: Trama (Persona 2) y Receptor (Persona 3).
 *
 * DISPOSICIÓN RECOMENDADA de la palabra (n bits de datos, r bits de control):
 *  - Posición 0: bit de paridad global (es lo que "extiende" el Hamming normal).
 *  - Posiciones 1 a n+r: Hamming clásico. Las posiciones 1, 2, 4, 8, 16, 32, 64 son bits de
 *    control; las demás llevan los datos en orden.
 *  - Paridad par en todo.
 * La longitud de la palabra es n + r + 1, donde r es el menor número que cumple 2^r >= n + r + 1.
 * Valores: 4 datos -> 8 bits, 7 datos -> 12 bits, 70 datos -> 78 bits.
 *
 * Ejemplo para probar (4 datos, solo para comprobar a mano): datos {1,0,1,1}
 *   -> palabra {0,0,1,1,0,0,1,1}
 *      (posición 0 = paridad global; posiciones 1..7 = 0,1,1,0,0,1,1 = control,control,dato,control,dato,dato,dato)
 */
public class Hamming {

    /**
     * YA ESTÁ HECHA (la necesita Trama para saber dónde termina cada campo). No la modifiques.
     * Devuelve la longitud de la palabra Hamming extendida para nDatos bits de datos.
     */
    public static int longitudPalabra(int nDatos) {
        if (nDatos < 1) {
            throw new IllegalArgumentException("nDatos debe ser al menos 1");
        }
        int r = 1;
        while ((1 << r) < nDatos + r + 1) {
            r++;
        }
        return nDatos + r + 1;
    }

    /**
     * Mete los bits de control entre los datos y calcula la paridad global.
     * Imprime los pasos (qué bit de control vigila qué posiciones y cuánto vale).
     *
     * @param datos bits del mensaje (entre 7 y 70 bits)
     * @return palabra de longitud longitudPalabra(datos.length)
     */
    public static int[] codificar(int[] datos) {
        throw new UnsupportedOperationException("Falta implementar Hamming.codificar (Persona 1)");
    }

    /**
     * Revisa la palabra recibida, la corrige si puede y dice qué pasó.
     * Imprime los pasos (síndrome, paridad global, decisión).
     * Debe distinguir los tres casos de ResultadoHamming: SIN_ERROR, CORREGIDO y DOBLE_ERROR.
     *
     * @param palabra palabra recibida (su longitud la fija el mensaje original)
     * @return ResultadoHamming con los datos, el estado y la posición del error (-1 si no aplica)
     */
    public static ResultadoHamming decodificar(int[] palabra) {
        throw new UnsupportedOperationException("Falta implementar Hamming.decodificar (Persona 1)");
    }

    /**
     * Saca los bits de datos de la palabra SIN corregir nada y sin imprimir nada.
     * El Receptor lo usa para revisar Checksum y CRC con los datos tal como llegaron.
     *
     * Pista: la cantidad de datos n se deduce de palabra.length (busca el n tal que
     * longitudPalabra(n) == palabra.length). Si no existe, lanza IllegalArgumentException.
     */
    public static int[] extraerDatos(int[] palabra) {
        throw new UnsupportedOperationException("Falta implementar Hamming.extraerDatos (Persona 1)");
    }

    private Hamming() { }
}
