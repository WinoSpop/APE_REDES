/**
 * Codificación de línea Manchester diferencial.
 *
 * DUEÑA: Persona 3.
 * La usan: Main (Persona 2) y Receptor (Persona 3).
 *
 * La señal es un arreglo de NIVELES (0 = bajo, 1 = alto). Cada bit de la trama ocupa DOS niveles
 * seguidos: la primera mitad y la segunda mitad del tiempo del bit. Por eso la señal mide
 * el doble que la trama.
 *
 * REGLAS DE MANCHESTER DIFERENCIAL:
 *  - Siempre hay un cambio de nivel a la MITAD de cada bit (primera mitad distinta de la segunda).
 *  - El valor del bit se marca con lo que pasa AL INICIO del bit, comparando con el último nivel
 *    del bit anterior: hay cambio al inicio para un valor de bit y no hay cambio para el otro.
 *
 * !! CONVENCIÓN CONFIGURABLE !! Usa las constantes privadas NIVEL_INICIAL y
 * BIT_CON_CAMBIO_AL_INICIO segun las diapositivas del profesor (por defecto: nivel previo 1,
 * el bit 0 lleva cambio al inicio y el bit 1 no lleva cambio al inicio).
 */
public class Manchester {

    /** Nivel previo asumido antes del primer bit (0 = bajo, 1 = alto). */
    private static final int NIVEL_INICIAL = 1;

    /** Bit que produce un cambio de nivel al inicio del intervalo (0 o 1). */
    private static final int BIT_CON_CAMBIO_AL_INICIO = 0;

    /**
     * Convierte la trama a niveles de señal.
     *
     * Ejemplo: la señal siempre tiene trama.length * 2 niveles, y en cada pareja de niveles
     * (posiciones 2k y 2k+1) los dos valores son distintos.
     *
     * Imprime los pasos (bit -> pareja de niveles) o al menos un resumen claro.
     *
     * @param trama bits de la trama
     * @return arreglo de trama.length * 2 niveles
     */
    public static int[] codificar(int[] trama) {
        if (trama == null) {
            throw new IllegalArgumentException("La trama no puede ser nula");
        }
        for (int i = 0; i < trama.length; i++) {
            if (trama[i] != 0 && trama[i] != 1) {
                throw new IllegalArgumentException("Bit invalido en la posicion " + i + ": " + trama[i]);
            }
        }

        int[] senal = new int[trama.length * 2];
        int nivelAnterior = NIVEL_INICIAL;

        System.out.println("[Manchester.codificar] Codificando " + trama.length + " bit(s) "
                + "(nivel inicial previo: " + NIVEL_INICIAL + ", bit " + BIT_CON_CAMBIO_AL_INICIO
                + " = cambio al inicio):");

        for (int i = 0; i < trama.length; i++) {
            int bit = trama[i];
            boolean cambioAlInicio = (bit == BIT_CON_CAMBIO_AL_INICIO);
            int primeraMitad = cambioAlInicio ? (1 - nivelAnterior) : nivelAnterior;
            int segundaMitad = 1 - primeraMitad;

            senal[2 * i] = primeraMitad;
            senal[2 * i + 1] = segundaMitad;
            nivelAnterior = segundaMitad;

            System.out.println("  bit " + i + ": " + bit + " -> niveles " + primeraMitad + "" + segundaMitad
                    + (cambioAlInicio ? " (cambio al inicio)" : " (sin cambio al inicio)"));
        }

        return senal;
    }

    /**
     * Operación inversa: convierte los niveles de vuelta a bits.
     *
     * Si en alguna pareja los dos niveles son iguales (no hay cambio a la mitad del bit), la señal
     * está dañada: avísalo con println ("posicion X: no hay cambio a la mitad del bit") y SIGUE
     * decodificando de todas formas, para que los tres controles puedan hacer su trabajo.
     *
     * Lanza IllegalArgumentException si senal.length es impar.
     *
     * @param senal niveles recibidos (0 y 1)
     * @return arreglo de senal.length / 2 bits
     */
    public static int[] decodificar(int[] senal) {
        if (senal == null) {
            throw new IllegalArgumentException("La senal no puede ser nula");
        }
        if (senal.length % 2 != 0) {
            throw new IllegalArgumentException("La longitud de la senal debe ser par, recibida: " + senal.length);
        }
        for (int i = 0; i < senal.length; i++) {
            if (senal[i] != 0 && senal[i] != 1) {
                throw new IllegalArgumentException("Nivel invalido en la posicion " + i + ": " + senal[i]);
            }
        }

        int totalBits = senal.length / 2;
        int[] trama = new int[totalBits];
        int nivelAnterior = NIVEL_INICIAL;
        int erroresMitadBit = 0;

        System.out.println("[Manchester.decodificar] Decodificando " + senal.length + " nivel(es) ("
                + totalBits + " bit(s)):");

        for (int i = 0; i < totalBits; i++) {
            int primeraMitad = senal[2 * i];
            int segundaMitad = senal[2 * i + 1];

            if (primeraMitad == segundaMitad) {
                erroresMitadBit++;
                System.out.println("posicion " + i + ": no hay cambio a la mitad del bit (niveles "
                        + primeraMitad + "" + segundaMitad + " en indices " + (2 * i) + " y " + (2 * i + 1) + ")");
            }

            boolean cambioAlInicio = (primeraMitad != nivelAnterior);
            int bit = cambioAlInicio ? BIT_CON_CAMBIO_AL_INICIO : (1 - BIT_CON_CAMBIO_AL_INICIO);

            trama[i] = bit;
            nivelAnterior = segundaMitad;

            System.out.println("  bit " + i + ": niveles " + primeraMitad + "" + segundaMitad + " -> " + bit
                    + (cambioAlInicio ? " (cambio al inicio)" : " (sin cambio al inicio)"));
        }

        if (erroresMitadBit > 0) {
            System.out.println("Advertencia: se detectaron " + erroresMitadBit
                    + " bit(s) sin cambio a la mitad del bit.");
        }

        return trama;
    }

    private Manchester() { }
}
