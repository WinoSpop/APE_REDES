/**
 * Lo que devuelve Trama.leer(): la trama ya separada en sus campos. Solo guarda datos; YA ESTÁ HECHA.
 * La llena la Persona 2 (en Trama) y la lee la Persona 3 (en Receptor).
 */
public class CamposTrama {

    /** false si la cabecera está dañada o el tamaño no cuadra (entonces los demás campos no sirven). */
    public final boolean valida;

    /** Si valida == false, explica por qué. Si valida == true, es "". */
    public final String motivo;

    /** Cantidad de caracteres del mensaje (1 a 10), leída de la cabecera. */
    public final int longitudCaracteres;

    public final int[] cabecera;
    public final int[] palabraHamming;
    public final int[] checksum;
    public final int[] crc;

    /** Para una trama correcta. */
    public CamposTrama(int longitudCaracteres, int[] cabecera, int[] palabraHamming, int[] checksum, int[] crc) {
        this.valida = true;
        this.motivo = "";
        this.longitudCaracteres = longitudCaracteres;
        this.cabecera = cabecera;
        this.palabraHamming = palabraHamming;
        this.checksum = checksum;
        this.crc = crc;
    }

    private CamposTrama(String motivo) {
        this.valida = false;
        this.motivo = motivo;
        this.longitudCaracteres = 0;
        this.cabecera = new int[0];
        this.palabraHamming = new int[0];
        this.checksum = new int[0];
        this.crc = new int[0];
    }

    /** Para una trama dañada que no se puede leer. */
    public static CamposTrama invalida(String motivo) {
        return new CamposTrama(motivo);
    }
}
