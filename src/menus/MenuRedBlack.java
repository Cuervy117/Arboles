package menus;
import java.util.Scanner;
import Redblack.Arbolredblack;
import Redblack.Nodo;
public class MenuRedBlack implements Menu{
    private Arbolredblack arbol;

    public MenuRedBlack() {
        arbol = new ArbolRedBlack();
    }

    public void ejecutarMenu(Scanner scanner) {
        int opcion;
        do {
            opciones();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1 -> insertarNodo(scanner);
                case 2 -> mostrarArbol();
                case 3 -> eliminarNodo(scanner);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void opciones() {
        System.out.println("\t--- Menú Árbol Red-Black ---");
        System.out.println("1. Insertar nodo");
        System.out.println("2. Mostrar árbol");
        System.out.println("3. Eliminar nodo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void insertarNodo(Scanner scanner) {
        System.out.print("Ingrese el valor del nodo: ");
        int valor = scanner.nextInt();
        Nodo nuevoNodo = new Nodo(valor);
        arbol.añadir(nuevoNodo);
        System.out.println("Nodo " + valor + " insertado.");
    }

    private void mostrarArbol() {
        Arbolredblack.recorridoPreOrden(arbol.getRaiz());
    }

    private void eliminarNodo(Scanner scanner) {
        System.out.print("Ingrese el valor del nodo a eliminar: ");
        int valor = scanner.nextInt();
        arbol.eliminar(valor);
        System.out.println("Nodo " + valor + " eliminado.");
    }
}
