/**
 * Arma la trama con todos sus campos y la vuelve a separar.
 *
 * DUEÑA: Persona 2.
 * La usan: Main (Persona 2) y Receptor (Persona 3).
 *
 * FORMATO DE LA TRAMA (en este orden, de izquierda a derecha):
 *
 *   | bandera | sentido | longitud | palabra Hamming            | checksum | crc      |
 *   | 8 bits  | 1 bit   | 4 bits   | Hamming.longitudPalabra(n) | 16 bits  | BITS_CRC |
 *
 *  - bandera: Constantes.BANDERA
 *  - sentido: Constantes.SENTIDO_ENVIO al armar
 *  - longitud: cantidad de CARACTERES del mensaje (n / 7), en 4 bits (Bits.deEntero)
 *  - palabra Hamming: Hamming.codificar(datos)
 *  - checksum: Checksum.calcular(datos)   <- sobre los datos ORIGINALES, no sobre la palabra Hamming
 *  - crc: CRC.calcular(datos)             <- sobre los datos ORIGINALES, no sobre la palabra Hamming
 *
 * Para 10 caracteres (70 bits): 13 + 78 + 16 + 16 = 123 bits.
 * Usa Bits.concatenar y Bits.subarreglo (ya hechas).
 */
public class Trama {

    /**
     * Arma la trama completa. Imprime cada campo.
     *
     * Debe lanzar IllegalArgumentException si datos.length no es múltiplo de 7 o no está entre
     * 7 y Constantes.MAX_BITS_DATOS.
     *
     * @param datos bits del mensaje
     * @return la trama en bits
     */
    public static int[] armar(int[] datos) {
        throw new UnsupportedOperationException("Falta implementar Trama.armar (Persona 2)");
    }

    /**
     * Separa una trama recibida en sus campos. NO lanza excepción si la trama está dañada:
     * devuelve CamposTrama.invalida("motivo") en estos casos:
     *  1. trama.length es menor que Constantes.BITS_CABECERA,
     *  2. la bandera recibida no es igual a Constantes.BANDERA,
     *  3. la longitud leída no está entre 1 y Constantes.MAX_CARACTERES,
     *  4. el tamaño total no coincide con el esperado:
     *     BITS_CABECERA + Hamming.longitudPalabra(longitud * 7) + BITS_CHECKSUM + BITS_CRC.
     * Si todo cuadra, corta con Bits.subarreglo y devuelve new CamposTrama(...).
     *
     * @param trama bits recibidos (ya pasados por Manchester.decodificar)
     */
    public static CamposTrama leer(int[] trama) {
        throw new UnsupportedOperationException("Falta implementar Trama.leer (Persona 2)");
    }

    private Trama() { }
}
