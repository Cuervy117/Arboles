package menus;

import arboles.Redblack.*;
import java.util.Scanner;



public class MenuArbolRedBlack implements Menu {
    private static ArbolRedBlack<Integer> arbol = new ArbolRedBlack<>();

    public void opciones() {
        System.out.println("\n--- Menú Árbol Red-Black ---");
        System.out.println("1. Insertar un valor");
        System.out.println("2. Mostrar InOrden");
        System.out.println("3. Mostrar PreOrden");
        System.out.println("4. Mostrar PostOrden");
        System.out.println("5. Salir");
    }

    public void ejecutarMenu(Scanner sc) {
        int opcion;
        do {
            opciones();  // Llamamos a la opción para mostrar las opciones en consola
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el valor a insertar: ");
                    int valor = sc.nextInt();
                    arbol.add(new NodoRB<>(valor));
                }
                case 2 -> arbol.imprimirInOrden();
                case 3 -> arbol.imprimirPreOrden();
                case 4 -> arbol.imprimirPostOrden();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuArbolRedBlack menu = new MenuArbolRedBlack();
        menu.ejecutarMenu(scanner);  // Ejecuta el menú en consola
        scanner.close();
    }
}





