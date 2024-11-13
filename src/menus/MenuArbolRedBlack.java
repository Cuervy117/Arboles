package menus;

import arboles.Redblack.*;
import java.util.Scanner;

public class MenuArbolRedBlack implements Menu {
    private static ArbolRedBlack<Integer> arbol = new ArbolRedBlack<>();

    // Opciones del menú
    public void opciones() {
        System.out.println("\n--- Menú Árbol Red-Black ---");
        System.out.println("1. Insertar un valor");
        System.out.println("2. Mostrar InOrden");
        System.out.println("3. Mostrar PreOrden");
        System.out.println("4. Mostrar PostOrden");
        System.out.println("5. Eliminar un valor");
        System.out.println("6. Salir");
    }
    
    // Ejecutar menú y sus respectivas opciones
    public void ejecutarMenu(Scanner sc) {
        int opcion;
        do {
            opciones();  // Llamamos a la opción para mostrar las opciones en consola
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
    
            switch (opcion) {
                case 1 -> {
                    // Opción para insertar un valor
                    System.out.print("Ingrese el valor a insertar: ");
                    int valor = sc.nextInt();
                    arbol.add(new NodoRB<>(valor));  // Inserta el valor en el árbol
                }
                case 2 -> arbol.imprimirInOrden();  // Muestra los valores en InOrden
                case 3 -> arbol.imprimirPreOrden();  // Muestra los valores en PreOrden
                case 4 -> arbol.imprimirPostOrden();  // Muestra los valores en PostOrden
                case 5 -> {
                    // Opción para eliminar un valor
                    System.out.print("Ingrese el valor a eliminar: ");
                    int valor = sc.nextInt();
                    try {
                        arbol.delete(valor);  // Elimina el valor del árbol
                        System.out.println("Valor eliminado.");
                        System.out.println("Es balanceado en altura negra: " + arbol.esBalanceadoEnAlturaNegra());
                        
                    } catch (Exception e) {
                        System.out.println("Error al eliminar el valor: " + e.getMessage());
                    }

                }
                case 6 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

}






