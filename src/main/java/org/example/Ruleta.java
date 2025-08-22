package org.example;

import java.util.Random;
import java.util.Scanner;
public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    /**
     * Método principal: inicia el programa llamando al menú.
     */
    public static void main(String[] args) {
        menu();
    }
    /**
     * Controla el flujo principal del programa mostrando un menú en consola.
     */
    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion);
        } while (opcion != 3);
    }
    /**
     * Muestra en consola las opciones disponibles del menú.
     */
    public static void mostrarMenu() {
        System.out.println("\n Ruleta Black Cat ");
        System.out.println("1. Iniciar Ronda");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }
    /**
     * Lee la opción elegida por el usuario desde teclado.
     * @param in Scanner para entrada por consola.
     * @return número de opción ingresado.
     */
    public static int leerOpcion(Scanner in) {
        return in.nextInt();
    }
    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     *
     * @param opcion opción elegida por el usuario.
     */
    public static void ejecutarOpcion(int opcion) {
        Scanner in = new Scanner(System.in);
        switch (opcion) {
            case 1 -> iniciarRonda(in);
            case 2 -> mostrarEstadisticas();
            case 3 -> System.out.println("¡Gracias por jugar!");
            default -> System.out.println("La Opción es inválida.");
        }
    }
    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar, evaluar y mostrar resultado.
     * @param in Scanner para entrada por consola.
     */
    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);

        System.out.print("Ingresa el monto a apostar: ");
        int monto = in.nextInt();

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }
    /**
     * Permite al usuario seleccionar el tipo de apuesta (R/N/P/I).
     * @param in Scanner para entrada por consola.
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        char tipo;
        while (true) {
            System.out.println("Tipos de apuesta:");
            System.out.println("R = Rojo");
            System.out.println("N = Negro");
            System.out.println("P = Par");
            System.out.println("I = Impar");
            System.out.print("Elige: ");

            String entrada = in.next().toUpperCase();

            if (entrada.length() == 1) {
                tipo = entrada.charAt(0);
                if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                    return tipo; // ✅ opción válida
                }
            }
            System.out.println("Error: Ingrese una opcion valida por favor \n");
        }
    }
    /**
     * Simula el giro de la ruleta generando un número aleatorio de 0 a 36.
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
        return rng.nextInt(37);
    }
    /**
     * Evalúa si la apuesta realizada por el jugador fue acertada.
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    public static boolean evaluarResultado(int numero, char tipo) {
        return switch (tipo) {
            case 'R' -> esRojo(numero);
            case 'N' -> numero != 0 && !esRojo(numero);
            case 'P' -> numero != 0 && numero % 2 == 0;
            case 'I' -> numero % 2 != 0;
            default -> false;
        };
    }
    /**
     * Determina si un número corresponde a color rojo.
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }
    /**
     * Registra los resultados de la ronda en los arreglos de historial.
     * @param numero número obtenido en la ruleta.
     * @param apuesta monto apostado.
     * @param acierto si el jugador acertó o no.
     */
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }
    /**
     * Muestra en consola el resultado de la ronda.
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
        String color = (numero == 0) ? "Verde" : (esRojo(numero) ? "Rojo" : "Negro");
        System.out.println("\nLa ruleta giró: " + numero + " (" + color + ")");
        if (acierto) {
            System.out.println("¡Ganaste! +" + monto + " créditos");
        } else {
            System.out.println("Perdiste -" + monto + " créditos");
        }
    }
    /**
     * Muestra estadísticas generales de todas las rondas jugadas.
     */
    public static void mostrarEstadisticas() {
        int totalApostado = 0;
        int aciertos = 0;
        int ganancia = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                aciertos++;
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
            }
        }

        System.out.println("\n Estadísticas");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Total apostado: " + totalApostado);
        System.out.println("Total aciertos: " + aciertos);
        if (historialSize > 0) {
            double porcentaje = (aciertos * 100.0) / historialSize;
            System.out.println("% de acierto: " + String.format("%.2f", porcentaje) + "%");
        }
        System.out.println("Ganancia/Pérdida neta: " + ganancia);
    }
}