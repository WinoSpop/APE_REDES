import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Menú en consola y recorrido completo del mensaje.
 *
 * DUEÑA: Persona 2.
 *
 * Este archivo YA FUNCIONA: solo llama a los métodos del contrato, en este orden:
 *   ConversorBits.textoABits -> Trama.armar -> Manchester.codificar
 *   -> (opcional) Canal.alterar -> Receptor.recibir
 * Mientras falte implementar alguna pieza, el programa avisa cuál falta y vuelve al menú.
 * Los textos que se imprimen van SIN tildes para que se vean bien en la consola de Windows.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("=== SIMULADOR DE CAPA FISICA Y DE ENLACE ===");
            System.out.println("1. Enviar mensaje sin errores");
            System.out.println("2. Enviar mensaje con errores");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");

            if (!in.hasNextLine()) {
                break;
            }
            String opcion = in.nextLine().trim();

            if (opcion.equals("1")) {
                enviar(in, false);
            } else if (opcion.equals("2")) {
                enviar(in, true);
            } else if (opcion.equals("3")) {
                salir = true;
            } else {
                System.out.println("Opcion no valida.");
            }
        }
        System.out.println("Fin del programa.");
    }

    /** Recorre todo el camino: emisor, canal (con o sin errores) y receptor. */
    private static void enviar(Scanner in, boolean conErrores) {
        try {
            String texto;
            int[] datos;
            while (true) {
                System.out.print("Escribe el mensaje (1 a " + Constantes.MAX_CARACTERES + " caracteres ASCII): ");
                if (!in.hasNextLine()) {
                    return;
                }
                texto = in.nextLine();
                try {
                    datos = ConversorBits.textoABits(texto);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Mensaje no valido: " + e.getMessage());
                }
            }

            System.out.println();
            System.out.println("--- EMISOR ---");
            System.out.println("Mensaje: \"" + texto + "\"");
            System.out.println("Bits del mensaje (" + datos.length + "): " + Bits.aTexto(datos));

            int[] trama = Trama.armar(datos);
            System.out.println("Trama (" + trama.length + " bits): " + Bits.aTexto(trama));

            int[] senal = Manchester.codificar(trama);
            System.out.println("Senal Manchester diferencial (" + senal.length + " niveles): " + Bits.aTexto(senal));

            if (conErrores) {
                int[] posiciones = pedirPosiciones(in, senal.length);
                if (posiciones == null) {
                    return;
                }
                senal = Canal.alterar(senal, posiciones);
                System.out.println("Senal despues del canal (" + senal.length + " niveles): " + Bits.aTexto(senal));
            }

            System.out.println();
            System.out.println("--- RECEPTOR ---");
            Receptor.recibir(senal);

        } catch (UnsupportedOperationException e) {
            System.out.println();
            System.out.println("*** " + e.getMessage());
        }
    }

    /**
     * Pide las posiciones de los niveles a alterar (empiezan en 0), separadas por espacios o comas.
     * Quita repetidas y repite la pregunta si algo no es válido. Devuelve null si se acaba la entrada.
     */
    private static int[] pedirPosiciones(Scanner in, int cantidadNiveles) {
        while (true) {
            System.out.print("Posiciones a alterar (0 a " + (cantidadNiveles - 1) + ", separadas por espacios): ");
            if (!in.hasNextLine()) {
                return null;
            }
            String linea = in.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.println("Escribe al menos una posicion.");
                continue;
            }

            Set<Integer> posiciones = new LinkedHashSet<Integer>();
            boolean valido = true;
            for (String parte : linea.split("[\\s,]+")) {
                try {
                    int p = Integer.parseInt(parte);
                    if (p < 0 || p >= cantidadNiveles) {
                        System.out.println("Posicion fuera de rango: " + p);
                        valido = false;
                        break;
                    }
                    posiciones.add(p);
                } catch (NumberFormatException e) {
                    System.out.println("No es un numero: " + parte);
                    valido = false;
                    break;
                }
            }

            if (valido) {
                int[] resultado = new int[posiciones.size()];
                int i = 0;
                for (int p : posiciones) {
                    resultado[i++] = p;
                }
                return resultado;
            }
        }
    }

    private Main() { }
}
