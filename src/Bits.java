import java.util.Arrays;

/**
 * Utilidades para trabajar con arreglos de bits.
 *
 * YA ESTÁN HECHAS: úsenlas y no las reescriban (así todos manejan los bits igual).
 * Un "arreglo de bits" es un int[] que solo contiene 0 y 1; el índice 0 es el primer bit.
 * Nadie edita este archivo sin avisar al grupo.
 */
public class Bits {

    /** Junta varios arreglos en uno nuevo, en el mismo orden. Ej.: concatenar({1,0}, {1}) -> {1,0,1} */
    public static int[] concatenar(int[]... partes) {
        int total = 0;
        for (int[] p : partes) {
            total += p.length;
        }
        int[] resultado = new int[total];
        int pos = 0;
        for (int[] p : partes) {
            System.arraycopy(p, 0, resultado, pos, p.length);
            pos += p.length;
        }
        return resultado;
    }

    /** Copia bits[desde .. hasta-1]; "hasta" NO se incluye. Ej.: subarreglo({1,0,1,1}, 1, 3) -> {0,1} */
    public static int[] subarreglo(int[] bits, int desde, int hasta) {
        if (desde < 0 || hasta > bits.length || desde > hasta) {
            throw new IllegalArgumentException("Rango invalido: " + desde + " a " + hasta
                    + " (el arreglo tiene " + bits.length + " bits)");
        }
        return Arrays.copyOfRange(bits, desde, hasta);
    }

    /** Convierte a texto para imprimir. Ej.: {1,0,1,1} -> "1011" */
    public static String aTexto(int[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int b : bits) {
            sb.append(b);
        }
        return sb.toString();
    }

    /** Lee los bits como un número (el primero es el más significativo). Ej.: {1,0,1,1} -> 11. Máximo 31 bits. */
    public static int aEntero(int[] bits) {
        if (bits.length > 31) {
            throw new IllegalArgumentException("Maximo 31 bits");
        }
        int valor = 0;
        for (int b : bits) {
            valor = (valor << 1) | b;
        }
        return valor;
    }

    /** Escribe un número con una cantidad fija de bits. Ej.: deEntero(11, 6) -> {0,0,1,0,1,1} */
    public static int[] deEntero(int valor, int cantidadBits) {
        if (cantidadBits < 0 || cantidadBits > 31 || valor < 0 || (valor >> cantidadBits) != 0) {
            throw new IllegalArgumentException(valor + " no cabe en " + cantidadBits + " bits");
        }
        int[] bits = new int[cantidadBits];
        for (int i = 0; i < cantidadBits; i++) {
            bits[cantidadBits - 1 - i] = (valor >> i) & 1;
        }
        return bits;
    }

    private Bits() { }
}
