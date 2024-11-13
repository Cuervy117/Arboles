package menus;

import arboles.Redblack.*;
import java.util.Scanner;

/**
 * Esta clase implementa el menú interactivo para gestionar un árbol Red-Black. 
 * Ofrece opciones para insertar, eliminar y mostrar los valores del árbol en diferentes recorridos.
 * Implementa la interfaz {@link Menu} y utiliza un árbol Red-Black genérico de tipo {@link ArbolRedBlack}.
 * 
 */
public class MenuArbolRedBlack implements Menu {
    
    /**
     * Instancia del árbol Red-Black utilizado en este menú.
     */
    private static ArbolRedBlack<Integer> arbol = new ArbolRedBlack<>();

    /**
     * Muestra las opciones disponibles en el menú para que el usuario elija.
     * Las opciones incluyen insertar un valor, mostrar los valores en diferentes recorridos, 
     * eliminar un valor o salir del programa.
     */
    public void opciones() {
        System.out.println("\n--- Menú Árbol Red-Black ---");
        System.out.println("1. Insertar un valor");
        System.out.println("2. Mostrar InOrden");
        System.out.println("3. Mostrar PreOrden");
        System.out.println("4. Mostrar PostOrden");
        System.out.println("5. Eliminar un valor");
        System.out.println("6. Salir");
    }

    /**
     * Ejecuta el menú en un bucle hasta que el usuario seleccione la opción para salir (opción 6).
     * Según la opción seleccionada, se ejecutan diferentes acciones como insertar, eliminar o mostrar 
     * los valores del árbol en distintos recorridos.
     * 
     * @param sc El objeto {@link Scanner} utilizado para leer la entrada del usuario desde la consola.
     */
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







