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
 * !! CONVENCIÓN PENDIENTE !! Cuál de los dos bits (0 o 1) lleva el cambio al inicio, y con qué nivel
 * parte el primer bit, hay que sacarlo de las DIAPOSITIVAS del profesor (los libros no coinciden).
 * Esa decisión queda DENTRO de este archivo (usa una constante privada para el nivel inicial) y
 * no cambia el contrato con los demás.
 */
public class Manchester {

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
        throw new UnsupportedOperationException("Falta implementar Manchester.codificar (Persona 3)");
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
        throw new UnsupportedOperationException("Falta implementar Manchester.decodificar (Persona 3)");
    }

    private Manchester() { }
}
