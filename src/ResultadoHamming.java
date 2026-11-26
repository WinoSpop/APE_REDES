/**
 * Lo que devuelve Hamming.decodificar(). Solo guarda datos; YA ESTÁ HECHA.
 * La llena la Persona 1 (en Hamming) y la lee la Persona 3 (en Receptor).
 */
public class ResultadoHamming {

    /** No se encontró ningún error. */
    public static final int SIN_ERROR = 0;
    /** Había 1 error y se corrigió. */
    public static final int CORREGIDO = 1;
    /** Hay 2 errores: se detectaron pero NO se pueden corregir. */
    public static final int DOBLE_ERROR = 2;

    /** Los bits del mensaje. Si estado == CORREGIDO ya van corregidos; si es DOBLE_ERROR van tal como llegaron. */
    public final int[] datos;

    /** SIN_ERROR, CORREGIDO o DOBLE_ERROR. */
    public final int estado;

    /** Índice (dentro de la palabra recibida) del bit que estaba mal. Vale -1 si no aplica. */
    public final int posicionError;

    public ResultadoHamming(int[] datos, int estado, int posicionError) {
        this.datos = datos;
        this.estado = estado;
        this.posicionError = posicionError;
    }
}
