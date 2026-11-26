/**
 * El lado que recibe: decodifica, separa la trama y revisa los tres controles.
 *
 * DUEÑA: Persona 3.
 * La usa: Main (Persona 2).
 */
public class Receptor {

    /**
     * Recibe la señal y muestra TODO el proceso y el resultado. Pasos:
     *
     *  1. bits = Manchester.decodificar(senal)
     *  2. campos = Trama.leer(bits)
     *     Si !campos.valida: imprime campos.motivo, di que la trama no se puede revisar y TERMINA.
     *  3. datosRecibidos = Hamming.extraerDatos(campos.palabraHamming)   (datos tal como llegaron)
     *  4. okChecksum = Checksum.verificar(datosRecibidos, campos.checksum)
     *  5. okCrc      = CRC.verificar(datosRecibidos, campos.crc)
     *  6. hamming    = Hamming.decodificar(campos.palabraHamming)
     *  7. Resumen final, uno por línea, con estas tres posibilidades para Hamming:
     *       - SIN_ERROR:   "sin error"
     *       - CORREGIDO:   "1 error corregido en la posicion X"
     *       - DOBLE_ERROR: "2 errores detectados, no se pueden corregir"
     *     Y para Checksum y CRC: "sin error" o "ERROR DETECTADO".
     *  8. Si todo salió sin error: indica claramente "no hubo error".
     *     Muestra el mensaje recuperado con ConversorBits.bitsATexto(...) usando los datos
     *     corregidos de Hamming (hamming.datos).
     *
     * Los pasos internos de cada validación los imprime cada método (regla 3 del README);
     * aquí solo se imprimen los títulos de cada etapa y el resumen final.
     *
     * @param senal niveles recibidos (puede venir alterada por Canal.alterar)
     */
    public static void recibir(int[] senal) {
        throw new UnsupportedOperationException("Falta implementar Receptor.recibir (Persona 3)");
    }

    private Receptor() { }
}
