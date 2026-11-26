/**
 * Constantes compartidas por todo el proyecto.
 *
 * DUEÑA: Persona 2. Nadie más edita este archivo.
 * Si alguien necesita cambiar un valor, avisa al grupo ANTES y lo cambia la Persona 2.
 */
public class Constantes {

    // ---------- Mensaje ----------
    public static final int MAX_CARACTERES = 10;
    public static final int BITS_POR_CARACTER = 7;                                // ASCII de 7 bits
    public static final int MAX_BITS_DATOS = MAX_CARACTERES * BITS_POR_CARACTER;  // 70

    // ---------- Cabecera de la trama (13 bits) ----------
    // bandera (8 bits) + sentido (1 bit) + cantidad de caracteres (4 bits)
    public static final int[] BANDERA = {0, 1, 1, 1, 1, 1, 1, 0};
    public static final int BITS_BANDERA = BANDERA.length;
    public static final int BITS_SENTIDO = 1;
    public static final int SENTIDO_ENVIO = 1;
    public static final int SENTIDO_RECEPCION = 0;
    public static final int BITS_LONGITUD = 4;
    public static final int BITS_CABECERA = BITS_BANDERA + BITS_SENTIDO + BITS_LONGITUD;

    // ---------- Checksum de internet ----------
    public static final int BITS_CHECKSUM = 16;

    // ---------- CRC ----------
    // PROVISIONAL: CRC-16 con el polinomio x^16 + x^12 + x^5 + 1 (17 coeficientes).
    // Si las diapositivas del profesor usan otro polinomio, la Persona 2 cambia SOLO esta
    // línea y avisa al grupo. BITS_CRC se ajusta solo (grado del polinomio).
    public static final int[] GENERADOR_CRC = {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
    public static final int BITS_CRC = GENERADOR_CRC.length - 1;

    private Constantes() { }
}
