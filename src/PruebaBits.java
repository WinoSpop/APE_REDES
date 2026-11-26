import java.util.Arrays;

/** Prueba de las utilidades que ya vienen hechas (Bits, Constantes, Hamming.longitudPalabra, clases de datos). */
public class PruebaBits {
    static int fallos = 0;

    static void igual(String nombre, Object esperado, Object obtenido) {
        boolean ok = esperado.equals(obtenido);
        if (!ok) {
            fallos++;
        }
        System.out.println((ok ? "OK    " : "FALLA ") + nombre + " -> " + obtenido + (ok ? "" : " (esperado " + esperado + ")"));
    }

    public static void main(String[] args) {
        igual("concatenar", "101", Bits.aTexto(Bits.concatenar(new int[]{1, 0}, new int[]{1})));
        igual("concatenar vacio", "11", Bits.aTexto(Bits.concatenar(new int[]{}, new int[]{1, 1}, new int[]{})));
        igual("subarreglo", "01", Bits.aTexto(Bits.subarreglo(new int[]{1, 0, 1, 1}, 1, 3)));
        igual("subarreglo vacio", "", Bits.aTexto(Bits.subarreglo(new int[]{1, 0}, 2, 2)));
        igual("aEntero", 11, Bits.aEntero(new int[]{1, 0, 1, 1}));
        igual("aEntero 31 bits", Integer.MAX_VALUE, Bits.aEntero(Bits.deEntero(Integer.MAX_VALUE, 31)));
        igual("deEntero", "001011", Bits.aTexto(Bits.deEntero(11, 6)));
        igual("deEntero 0 bits", "", Bits.aTexto(Bits.deEntero(0, 0)));
        igual("deEntero longitud 10 en 4 bits", "1010", Bits.aTexto(Bits.deEntero(10, 4)));

        try {
            Bits.deEntero(16, 4);
            igual("deEntero que no cabe lanza excepcion", true, false);
        } catch (IllegalArgumentException e) {
            igual("deEntero que no cabe lanza excepcion", true, true);
        }
        try {
            Bits.subarreglo(new int[]{1, 0}, 0, 5);
            igual("subarreglo fuera de rango lanza excepcion", true, false);
        } catch (IllegalArgumentException e) {
            igual("subarreglo fuera de rango lanza excepcion", true, true);
        }

        igual("longitudPalabra(4)", 8, Hamming.longitudPalabra(4));
        igual("longitudPalabra(7)", 12, Hamming.longitudPalabra(7));
        igual("longitudPalabra(70)", 78, Hamming.longitudPalabra(70));

        igual("BITS_CABECERA", 13, Constantes.BITS_CABECERA);
        igual("BITS_CRC", 16, Constantes.BITS_CRC);
        igual("MAX_BITS_DATOS", 70, Constantes.MAX_BITS_DATOS);
        igual("trama maxima", 123, Constantes.BITS_CABECERA + Hamming.longitudPalabra(70) + Constantes.BITS_CHECKSUM + Constantes.BITS_CRC);

        CamposTrama mala = CamposTrama.invalida("prueba");
        igual("CamposTrama.invalida", false, mala.valida);
        igual("CamposTrama.invalida motivo", "prueba", mala.motivo);
        CamposTrama buena = new CamposTrama(2, new int[13], new int[12], new int[16], new int[16]);
        igual("CamposTrama valida", true, buena.valida);
        igual("ResultadoHamming", 1, new ResultadoHamming(new int[]{1}, ResultadoHamming.CORREGIDO, 5).estado);

        System.out.println(fallos == 0 ? "\nTodo bien." : "\nFALLOS: " + fallos);
    }
}
