/**
 * Pasa texto a bits y bits a texto.
 *
 * DUEÑA: Persona 1.
 * La usan: Main (Persona 2) y Receptor (Persona 3).
 */
public class ConversorBits {

    /**
     * Convierte el texto a un arreglo de bits: 7 bits por carácter (ASCII), el bit más
     * significativo primero.
     *
     * Ejemplo: "A" (código 65) -> {1,0,0,0,0,0,1}
     *
     * Debe lanzar IllegalArgumentException con un mensaje claro (sin tildes) si:
     *  - el texto es null o vacío,
     *  - tiene más de Constantes.MAX_CARACTERES caracteres (los espacios cuentan),
     *  - algún carácter no es ASCII imprimible (código menor que 32 o mayor que 126).
     *    Así quedan fuera tildes, ñ, diéresis y símbolos raros.
     *
     * @return arreglo de texto.length() * 7 bits (máximo 70)
     */
    public static int[] textoABits(String texto) {
        throw new UnsupportedOperationException("Falta implementar ConversorBits.textoABits (Persona 1)");
    }

    /**
     * Operación inversa: lee los bits de 7 en 7 y devuelve el texto.
     *
     * Ejemplo: {1,0,0,0,0,0,1} -> "A"
     *
     * Lanza IllegalArgumentException si la cantidad de bits no es múltiplo de 7.
     * Si un grupo de 7 bits no es un carácter imprimible (puede pasar cuando hubo errores),
     * pon '?' en su lugar en vez de lanzar excepción: el Receptor debe poder mostrar algo.
     */
    public static String bitsATexto(int[] bits) {
        throw new UnsupportedOperationException("Falta implementar ConversorBits.bitsATexto (Persona 1)");
    }

    private ConversorBits() { }
}
