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
        if (senal == null) {
            throw new IllegalArgumentException("La senal no puede ser nula");
        }

        System.out.println("1. Decodificando senal Manchester diferencial...");
        int[] bits = Manchester.decodificar(senal);
        System.out.println("Trama recibida (" + bits.length + " bits): " + Bits.aTexto(bits));

        System.out.println();
        System.out.println("2. Leyendo campos de la trama...");
        CamposTrama campos = Trama.leer(bits);
        if (!campos.valida) {
            System.out.println("Motivo: " + campos.motivo);
            System.out.println("La trama no es valida y no se puede revisar.");
            return;
        }
        System.out.println("Trama valida (" + campos.longitudCaracteres + " caracter(es)).");
        System.out.println("  Cabecera (" + campos.cabecera.length + " bits): " + Bits.aTexto(campos.cabecera));
        System.out.println("  Palabra Hamming (" + campos.palabraHamming.length + " bits): "
                + Bits.aTexto(campos.palabraHamming));
        System.out.println("  Checksum (" + campos.checksum.length + " bits): " + Bits.aTexto(campos.checksum));
        System.out.println("  CRC (" + campos.crc.length + " bits): " + Bits.aTexto(campos.crc));

        System.out.println();
        System.out.println("3. Extrayendo datos recibidos...");
        int[] datosRecibidos = Hamming.extraerDatos(campos.palabraHamming);
        System.out.println("Datos recibidos (" + datosRecibidos.length + " bits): " + Bits.aTexto(datosRecibidos));

        System.out.println();
        System.out.println("4. Verificando Checksum...");
        boolean okChecksum = Checksum.verificar(datosRecibidos, campos.checksum);

        System.out.println();
        System.out.println("5. Verificando CRC...");
        boolean okCrc = CRC.verificar(datosRecibidos, campos.crc);

        System.out.println();
        System.out.println("6. Verificando y decodificando Hamming extendido...");
        ResultadoHamming hamming = Hamming.decodificar(campos.palabraHamming);

        String resumenHamming;
        if (hamming.estado == ResultadoHamming.SIN_ERROR) {
            resumenHamming = "sin error";
        } else if (hamming.estado == ResultadoHamming.CORREGIDO) {
            resumenHamming = "1 error corregido en la posicion " + hamming.posicionError;
        } else if (hamming.estado == ResultadoHamming.DOBLE_ERROR) {
            resumenHamming = "2 errores detectados, no se pueden corregir";
        } else {
            resumenHamming = "estado desconocido (" + hamming.estado + ")";
        }

        System.out.println();
        System.out.println("=== RESUMEN FINAL ===");
        System.out.println("Hamming:  " + resumenHamming);
        System.out.println("Checksum: " + (okChecksum ? "sin error" : "ERROR DETECTADO"));
        System.out.println("CRC:      " + (okCrc ? "sin error" : "ERROR DETECTADO"));

        boolean sinErrores = okChecksum && okCrc && hamming.estado == ResultadoHamming.SIN_ERROR;
        if (sinErrores) {
            System.out.println("Resultado general: no hubo error.");
        } else {
            System.out.println("Resultado general: se detectaron errores en la transmision.");
        }

        String mensajeRecuperado = ConversorBits.bitsATexto(hamming.datos);
        System.out.println("Datos finales (" + hamming.datos.length + " bits): " + Bits.aTexto(hamming.datos));
        System.out.println("Mensaje recuperado: \"" + mensajeRecuperado + "\"");
    }

    private Receptor() { }
}
