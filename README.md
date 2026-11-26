# Simulador de capa física y de enlace (Proyecto de Redes, Java)

Programa de consola, en Java y sin interfaz gráfica, que simula una transmisión en una sola computadora:
una frase de **máximo 10 caracteres ASCII** se convierte en bits, se arma una trama protegida con
**Checksum de internet, Hamming extendido y CRC**, se codifica en **Manchester diferencial**, se puede
dañar a propósito y el receptor la decodifica, revisa los tres controles e informa qué detectó cada uno.

Este repositorio es el **esqueleto del grupo**: todas las clases ya existen, con los métodos vacíos y su
contrato escrito. Cada persona rellena **solo sus archivos** y, como todos respetan el contrato,
las piezas encajan al final.

## 1. Recorrido del mensaje

```
Main (Persona 2) pide el texto
  -> ConversorBits.textoABits      (P1)   texto  -> bits
  -> Trama.armar                   (P2)   bits   -> trama (usa Hamming.codificar, Checksum.calcular, CRC.calcular)
  -> Manchester.codificar          (P3)   trama  -> señal de niveles
  -> Canal.alterar                 (P3)   (solo en la opción 2) daña niveles de la señal
  -> Receptor.recibir              (P3)   señal  -> revisa todo y muestra el resultado
         usa Manchester.decodificar (P3), Trama.leer (P2), Hamming.extraerDatos / decodificar (P1),
         Checksum.verificar y CRC.verificar (P2), ConversorBits.bitsATexto (P1)
```

Los tres controles (Checksum, Hamming y CRC) se aplican **siempre, todos juntos**. El menú del `Main`
solo elige entre enviar sin errores, enviar con errores o salir; no sirve para elegir algoritmos.

## 2. Reparto

| Persona | Archivos de los que es dueña | Qué programa | Tiempo estimado |
| --- | --- | --- | --- |
| **1** Jeremy Torosina | `ConversorBits.java`, `Hamming.java` | Texto a bits y bits a texto. Hamming extendido: `codificar`, `decodificar` y `extraerDatos` (`longitudPalabra` ya está hecha). | 3 a 5 h |
| **2** Andrés Llamuca | `Constantes.java`, `Checksum.java`, `CRC.java`, `Trama.java`, `Main.java` | Checksum, CRC, armar y leer la trama. `Main` ya funciona; solo se ajusta si hace falta. | 3 a 5 h |
| **3** Joseph Romo | `Manchester.java`, `Canal.java`, `Receptor.java` | Manchester diferencial (codificar y decodificar), alterar la señal y el receptor completo. | 3 a 5 h |

**Ya están hechos y nadie los edita sin avisar al grupo:** `Bits.java` (utilidades), `ResultadoHamming.java`
y `CamposTrama.java` (clases que solo guardan datos), `PruebaBits.java` (ejemplo de cómo hacer una prueba).

Los tiempos son estimados, no datos exactos. Hamming (P1) y el receptor (P3) son las piezas más pesadas.

## 3. Formato de la trama

Los campos van en este orden, de izquierda a derecha:

| Campo | Tamaño | Contenido |
| --- | --- | --- |
| bandera | 8 bits | `Constantes.BANDERA` (01111110) |
| sentido | 1 bit | `Constantes.SENTIDO_ENVIO` (1) al armar |
| longitud | 4 bits | cantidad de **caracteres** del mensaje (1 a 10), con `Bits.deEntero` |
| palabra Hamming | `Hamming.longitudPalabra(n)` | `Hamming.codificar(datos)` |
| checksum | 16 bits | `Checksum.calcular(datos)` sobre los datos **originales** |
| crc | `Constantes.BITS_CRC` | `CRC.calcular(datos)` sobre los datos **originales** |

Para 10 caracteres (70 bits de datos): 13 + 78 + 16 + 16 = **123 bits**. En Manchester cada bit ocupa
2 niveles, así que la señal mide el doble que la trama (246 niveles en ese caso).

La longitud en la cabecera permite que el receptor sepa dónde termina cada campo.

## 4. El contrato

Todos los arreglos de bits son `int[]` con solo 0 y 1, y el índice 0 es el primer bit.

| Método | Recibe | Devuelve | Dueña | La usan |
| --- | --- | --- | --- | --- |
| `ConversorBits.textoABits` | `String` | `int[]` (7 bits por carácter) | P1 | Main |
| `ConversorBits.bitsATexto` | `int[]` | `String` | P1 | Receptor |
| `Hamming.longitudPalabra` (hecho) | `int nDatos` | `int` | P1 | Trama |
| `Hamming.codificar` | `int[] datos` | `int[]` palabra | P1 | Trama |
| `Hamming.decodificar` | `int[] palabra` | `ResultadoHamming` | P1 | Receptor |
| `Hamming.extraerDatos` | `int[] palabra` | `int[]` datos sin corregir | P1 | Receptor |
| `Checksum.calcular` | `int[] datos` | `int[]` de 16 bits | P2 | Trama |
| `Checksum.verificar` | `int[] datos, int[] checksum` | `boolean` (true = sin error) | P2 | Receptor |
| `CRC.calcular` | `int[] datos` | `int[]` de `BITS_CRC` bits | P2 | Trama |
| `CRC.verificar` | `int[] datos, int[] crc` | `boolean` (true = sin error) | P2 | Receptor |
| `Trama.armar` | `int[] datos` | `int[]` trama | P2 | Main |
| `Trama.leer` | `int[] trama` | `CamposTrama` | P2 | Receptor |
| `Manchester.codificar` | `int[] trama` | `int[]` señal (2 niveles por bit) | P3 | Main |
| `Manchester.decodificar` | `int[] senal` | `int[]` trama | P3 | Receptor |
| `Canal.alterar` | `int[] senal, int[] posiciones` | `int[]` señal alterada (copia) | P3 | Main |
| `Receptor.recibir` | `int[] senal` | nada (imprime todo) | P3 | Main |

Las utilidades de `Bits` (ya hechas) son `concatenar`, `subarreglo`, `aTexto`, `aEntero` y `deEntero`.
Sus ejemplos están en los comentarios del archivo.

**Clases que solo guardan datos**

- `ResultadoHamming`: `datos`, `estado` (`SIN_ERROR`, `CORREGIDO` o `DOBLE_ERROR`) y `posicionError` (-1 si no aplica).
- `CamposTrama`: `valida`, `motivo`, `longitudCaracteres`, `cabecera`, `palabraHamming`, `checksum` y `crc`.

**Valores compartidos** (todos en `Constantes.java`, no los escribas a mano en tu código): `MAX_CARACTERES`,
`BITS_POR_CARACTER`, `MAX_BITS_DATOS`, `BANDERA`, `BITS_CABECERA`, `BITS_CHECKSUM`, `GENERADOR_CRC` y `BITS_CRC`.

Los detalles de cada método (casos especiales, qué imprimir, ejemplos para probar) están escritos
en el comentario que tiene sobre sí en el código. **Léelo antes de programarlo.**

## 5. Reglas para que todo encaje

1. Los bits siempre son `int[]` con 0 y 1. La señal de Manchester también.
2. Un método **no modifica** el arreglo que recibe: devuelve uno nuevo.
3. `calcular`, `verificar`, `codificar`, `decodificar`, `armar`, `alterar` y `recibir` **imprimen sus pasos** con
   `System.out.println`, porque es lo que el profesor quiere ver. Los textos van **sin tildes ni ñ**, para que
   se vean bien en la consola de Windows.
4. Si un método recibe algo inválido, lanza `IllegalArgumentException` con un mensaje claro. Las dos excepciones
   son `Trama.leer` y `Manchester.decodificar`: reciben datos posiblemente dañados, así que no lanzan, avisan y siguen.
5. **Nadie edita archivos de otra persona.** Si necesitas un cambio en su clase, se lo pides.
6. No cambies el nombre ni los parámetros de un método del contrato. Si de verdad hace falta, se acuerda en
   el grupo y se actualizan el código y este README al mismo tiempo.
7. Solo `Main` lee del teclado. Sin librerías externas y sin interfaz gráfica.
8. Usa `Bits` y `Constantes` en vez de repetir números o copiar código.

## 6. Decisiones pendientes

| Decisión | Quién | Dónde se cambia |
| --- | --- | --- |
| Polinomio del CRC. Hay uno **provisional** (CRC-16, x^16 + x^12 + x^5 + 1). Confirmarlo con el ejercicio de las diapositivas. | P2 | solo `Constantes.GENERADOR_CRC` |
| Convención de Manchester diferencial: qué bit lleva el cambio al inicio y con qué nivel empieza. Sale de las diapositivas. | P3 | solo dentro de `Manchester.java` |
| Control de flujo: el audio lo menciona en la introducción, pero no está en la lista de requisitos. Preguntar al profesor en la clase del 1 de diciembre si hay que implementarlo. | todos | por definir |
| Pasos que debe mostrar el receptor: completos o resumidos. | todos | por definir con el profesor |
| El bit de sentido: hoy nadie lo revisa, así que si se altera pasa sin aviso. ¿`Trama.leer` debe exigir que valga `SENTIDO_ENVIO` y marcar la trama como no válida si no? | P2 | solo `Trama.leer` (el contrato no cambia) |

La cabecera (bandera 01111110, sentido y longitud) es una propuesta inventada, como pide el proyecto.
Si el grupo prefiere otra, se cambia en `Constantes.java` y nada más.

## 7. Compilar y ejecutar

Desde la carpeta del proyecto, en la consola (Windows o Linux):

```
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

En VS Code basta con abrir la carpeta y ejecutar `Main.java`. Mientras falte alguna pieza, el programa avisa
cuál (`Falta implementar ...`) y vuelve al menú: eso es normal.

**Probar tu pieza sola:** crea un archivo `src/Prueba<TuClase>.java` con su propio `main` (por ejemplo
`PruebaHamming.java`), copiando el estilo de `PruebaBits.java`, y prueba con los ejemplos de los comentarios.
Es tuyo, y sirve también para demostrar el proyecto.

## 8. Git paso a paso

**Una sola vez.** La persona que crea el repositorio:

1. Crea un repositorio vacío en GitHub y agrega a las otras dos en *Settings > Collaborators*.
2. En la carpeta del proyecto, sube el esqueleto:

```
git init
git add .
git commit -m "Esqueleto inicial con el contrato"
git branch -M main
git remote add origin https://github.com/USUARIO/REPOSITORIO.git
git push -u origin main
```

Las otras dos lo bajan con `git clone https://github.com/USUARIO/REPOSITORIO.git`.

**Cada vez que trabajan:**

```
git pull                                  (antes de empezar, para bajar lo de los demás)
... programas solo tus archivos ...
git status                                (comprueba que solo cambiaron tus archivos)
git add src/Hamming.java                  (los tuyos, con su nombre)
git commit -m "Hamming: codificar listo"
git pull
git push
```

- Hagan commits pequeños y seguido, no uno gigante al final.
- Trabajen directo en `main`: con archivos separados no hacen falta ramas.
- Si `git push` dice que fue rechazado, haz `git pull` y vuelve a hacer `git push`.
- Si Git avisa de un **conflicto**, no lo arregles a ciegas. Avisen al grupo y lo resuelven juntos.
  Si cada uno toca solo sus archivos, casi nunca pasa.

## 9. Orden de trabajo sugerido

**Etapa 1: cada uno su pieza.** Orden recomendado dentro de cada persona:

- **P1:** primero `ConversorBits` (lo necesita el `Main` para probar), después `Hamming`.
- **P2:** primero `Checksum` y `CRC`; `Trama` al final, porque necesita que `Hamming.codificar` ya esté subido.
- **P3:** `Manchester` y `Canal` con bits de ejemplo escritos a mano; `Receptor` al final, porque usa todas las piezas.

**Etapa 2: integrar** (una sesión juntos, de unas 2 horas). Todos hacen `git pull`, ejecutan el programa con un
mensaje sin errores y arreglan lo que no encaje, cada uno en lo suyo.

**Etapa 3: pruebas y presentación.** Meta sugerida: tener las piezas terminadas antes de la clase del 1 de
diciembre, para llegar con preguntas reales sobre lo que falte.

## 10. Pruebas finales

Con el programa completo, probar en la opción 2 (alterar la señal). Lo esperado es aproximado y depende de
cómo decodifique el Manchester de la Persona 3:

| Caso | Qué debería pasar, normalmente |
| --- | --- |
| Sin errores | Los tres controles sin error y el mensaje recuperado igual al original. |
| 1 nivel alterado dentro de los datos | Checksum y CRC detectan el error; Hamming lo corrige. |
| 1 nivel alterado en un bit de control de Hamming | Hamming lo corrige; Checksum y CRC no ven nada raro en los datos. |
| 2 niveles alterados en la palabra Hamming | Hamming detecta 2 errores sin poder corregir; CRC lo detecta; Checksum casi siempre. |
| Varios errores seguidos (ráfaga) | CRC lo detecta; Hamming puede fallar. Comprobar que el receptor no se rompe. |
| Nivel alterado en la cabecera (bandera o longitud) | `Trama.leer` avisa que la trama no es válida y el receptor termina sin excepciones. |
| Nivel alterado en el checksum o en el CRC | Solo ese control avisa del error. Los datos están bien: Hamming dice sin error y el mensaje se recupera igual. |
| Nivel alterado en el bit de sentido | Nadie lo nota: ningún control protege la cabecera y `Trama.leer` no lo revisa (ver decisiones pendientes). |
| Mensajes de 1 y de 10 caracteres | Funcionan; el de 10 da una trama de 123 bits. |
| Mensaje con tilde, ñ o más de 10 caracteres | `Main` lo rechaza con un mensaje y vuelve a pedirlo. |

Alterar un nivel de la señal suele dañar un bit de la trama y además rompe el cambio a la mitad de ese bit.
