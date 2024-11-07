package aritmetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Menu {
    public static ArbolExp arbol = null;

    public static void desplegarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Ingresar expresión");
            System.out.println("2. Mostrar árbol");
            System.out.println("3. Resolver expresión");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> ingresarExpresion();
                case 2 -> mostrarArbol();
                case 3 -> resolverExpresion();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void ingresarExpresion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la expresión matemática (separada por espacios): ");
        String expresion = scanner.nextLine();
        String[] elementos = expresion.split(" ");
        arbol = new ArbolExp(elementos);
        System.out.println("Expresión ingresada y árbol construido");
    }

    public static void mostrarArbol() {
        if (arbol == null || arbol.root == null) {
            System.out.println("No se ha ingresado una expresión aún");
            return;
        }

        System.out.println("Árbol en notación prefija:");
        arbol.notPrefija(arbol.root);

        System.out.println("\nÁrbol en notación infija:");
        arbol.notInfija(arbol.root);

        System.out.println("\nÁrbol en notación postfija:");
        arbol.notPostfija(arbol.root);
    }

    public static void resolverExpresion() {
        if (arbol == null || arbol.root == null) {
            System.out.println("No se ha ingresado una expresión aún");
            return;
        }

        List<String> rpn = obtenerRPN(arbol.root);
        System.out.println("Reverse Polish Notation: " + String.join(" ", rpn));
        double resultado = resolverConRPN(rpn);
        System.out.println("Resultado de la expresión: " + resultado);
    }

    public static List<String> obtenerRPN(Nodo nodo) {
        List<String> rpn = new ArrayList<>();
        obtenerRPNPostorden(nodo, rpn);
        return rpn;
    }

    public static void obtenerRPNPostorden(Nodo nodo, List<String> rpn) {
        if (nodo != null) {
            obtenerRPNPostorden(nodo.getIzq(), rpn);
            obtenerRPNPostorden(nodo.getDer(), rpn);
            rpn.add(nodo.getValor());
        }
    }

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
