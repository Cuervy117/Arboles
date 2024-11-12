package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import arboles.aritmetico.ArbolExp;
import arboles.binario.Nodo;

/**
 * Clase que implementa el menú para manipular un árbol de expresiones aritméticas.
 * Permite al usuario ingresar una expresión, mostrar el árbol y resolver la expresión.
 */
public class MenuAritmetico implements Menu{
    public static ArbolExp arbol;

    /**
     * Muestra las opciones disponibles en el menú.
     */
    public static void opciones(){
        System.out.println("Seleccione una opción: ");
        System.out.println("1. Ingresar expresión");
        System.out.println("2. Mostrar árbol");
        System.out.println("3. Resolver expresión");
        System.out.println("4. Salir");
    }

    /**
     * Ejecuta el menú de opciones donde solicita al usuario que elija una acción a realizar.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public static void ejecutarMenu(Scanner scanner) {
        int opcion;

        do {
            opciones();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> ingresarExpresion(scanner);
                case 2 -> {
                    if(arbol == null || arbol.getRoot() == null){
                        System.out.println("Aun no haz creado un arbol de expresion o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Imprimiendo arbol...");
                    MenuImprimir.ejecutarMenu(scanner, arbol);
                }
                case 3 -> resolverExpresion();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);

    }

    /**
     * Permite al usuario ingresar una expresión matemática y construir un árbol de expresiones.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public static void ingresarExpresion(Scanner scanner) {
        System.out.print("Ingrese la expresión matemática (separada por espacios): ");
        String expresion = scanner.nextLine();
        String[] elementos = expresion.split(" ");
        arbol = new ArbolExp(elementos);
        System.out.println("Expresión ingresada y árbol construido");
    }

    /**
     * Resuelve la expresión aritmética del árbol y muestra el resultado.
     * Convierte la expresión en notación polaca inversa y la evalúa.
     */
    public static void resolverExpresion() {
        if (arbol == null || arbol.getRoot() == null) {
            System.out.println("No se ha ingresado una expresión aún");
            return;
        }

        List<String> rpn = obtenerRPN(arbol.getRoot());
        System.out.println("Notacion polaca inversa: " + String.join(" ", rpn));
        double resultado = resolverConRPN(rpn);
        System.out.println("Resultado de la expresión: " + resultado);
    }

    /**
     * Obtiene la notación polaca inversa de la expresión en el árbol mediante recorrido en postorden.
     * @param nodo Nodo raíz del árbol de expresión.
     * @return Lista de strings que representa la expresión en notación polaca inversa.
     */
    public static List<String> obtenerRPN(Nodo<String> nodo) {
        List<String> rpn = new ArrayList<>();
        obtenerRPNPostorden(nodo, rpn);
        return rpn;
    }

    /**
     * Realiza un recorrido en postorden del árbol para generar la notación polaca inversa.
     * @param nodo Nodo actual en el recorrido.
     * @param rpn Lista donde se almacena la expresión en notación polaca inversa.
     */
    public static void obtenerRPNPostorden(Nodo<String> nodo, List<String> rpn) {
        if (nodo != null) {
            obtenerRPNPostorden(nodo.getHijoIzquierdo(), rpn);
            obtenerRPNPostorden(nodo.getHijoDerecho(), rpn);
            rpn.add(nodo.getClave());
        }
    }

    /**
     * Resuelve la expresión en Notación Polaca Inversa.
     * Evalúa la lista de elementos en RPN utilizando una pila para realizar las operaciones.
     * @param rpn Lista de strings que representa la expresión en notación polaca inversa.
     * @return Resultado de la expresión.
     */
    public static double resolverConRPN(List<String> rpn) {
        Stack<Double> pila = new Stack<>();

        for (String elemento : rpn) {
            if (ArbolExp.esOperando(elemento)) {
                pila.push(Double.parseDouble(elemento));
            } else if (ArbolExp.esOperador(elemento)) {
                double der = pila.pop();
                double izq = pila.pop();
                double resultado = switch (elemento) {
                    case "+" -> izq + der;
                    case "-" -> izq - der;
                    case "*" -> izq * der;
                    case "/" -> izq / der;
                    default -> 0;
                };
                pila.push(resultado);
            }
        }
        return pila.pop();
    }
}

