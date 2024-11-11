package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import arboles.aritmetico.ArbolExp;
import arboles.binario.Nodo;

public class MenuAritmetico implements Menu{
    public static ArbolExp arbol;

    public static void opciones(){
        System.out.println("Seleccione una opción: ");
        System.out.println("1. Ingresar expresión");
        System.out.println("2. Mostrar árbol");
        System.out.println("3. Resolver expresión");
        System.out.println("4. Salir");
    }

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

        scanner.close();
    }

    public static void ingresarExpresion(Scanner scanner) {
        System.out.print("Ingrese la expresión matemática (separada por espacios): ");
        String expresion = scanner.nextLine();
        String[] elementos = expresion.split(" ");
        arbol = new ArbolExp(elementos);
        System.out.println("Expresión ingresada y árbol construido");
    }

   /*public static void mostrarArbol() {
        if (arbol == null || arbol.getRoot() == null) {
            System.out.println("No se ha ingresado una expresión aún");
            return;
        }

        System.out.println("Árbol en notación prefija:");
        arbol.notPrefija(arbol.getRoot());

        System.out.println("\nÁrbol en notación infija:");
        arbol.notInfija(arbol.getRoot());

        System.out.println("\nÁrbol en notación postfija:");
        arbol.notPostfija(arbol.getRoot());
    }*/

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

    public static List<String> obtenerRPN(Nodo<String> nodo) {
        List<String> rpn = new ArrayList<>();
        obtenerRPNPostorden(nodo, rpn);
        return rpn;
    }

    public static void obtenerRPNPostorden(Nodo<String> nodo, List<String> rpn) {
        if (nodo != null) {
            obtenerRPNPostorden(nodo.getHijoIzquierdo(), rpn);
            obtenerRPNPostorden(nodo.getHijoDerecho(), rpn);
            rpn.add(nodo.getClave());
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

