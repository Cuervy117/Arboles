package menus;

import arboles.Redblack.*;
import arboles.binario.*;
import java.util.Scanner;

public class MenuArbolRedBlack {

    private static ArbolRedBlack<Integer> arbol;
    private static Scanner scanner;

    public static void main(String[] args) {
        arbol = new ArbolRedBlack<>(); // Crear un nuevo árbol Red-Black
        scanner = new Scanner(System.in);
        
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            ejecutarOpcion(opcion);
        }
    }

    private static void mostrarMenu() {
        System.out.println("------ Menú de Árbol Red-Black ------");
        System.out.println("1. Insertar un nodo");
        System.out.println("2. Mostrar el árbol (preorden)");
        System.out.println("3. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                insertarNodo();
                break;
            case 2:
                mostrarArbol();
                break;
            case 3:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void insertarNodo() {
        System.out.print("Ingrese el valor del nodo a insertar: ");
        int valor = scanner.nextInt();
        NodoRB<Integer> nuevoNodo = new NodoRB<>(valor);
        arbol.add(nuevoNodo);
        System.out.println("Nodo " + valor + " insertado correctamente.");
    }

    private static void mostrarArbol() {
        System.out.println("Mostrando el árbol en preorden:");
        mostrarPreorden((NodoRB<Integer>) arbol.getRaiz());
        System.out.println();
    }

    private static void mostrarPreorden(NodoRB<Integer> nodo) {
        if (nodo == null) return;
        
        System.out.print(nodo.getClave() + "(" + nodo.getColor() + ") ");
        mostrarPreorden((NodoRB<Integer>) nodo.getHijoIzquierdo());
        mostrarPreorden((NodoRB<Integer>) nodo.getHijoDerecho());
    }
}



