package principal;

import java.util.Scanner;

import menus.*;

/**
 *
 * @author David
 */
public class Principal {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1   ----    Arbol de Expresión aritmetica");
            System.out.println("2   ----    Arbol Heap");
            System.out.println("3   ----    Arbol AVL");
            System.out.println("4   ----    Arbol Red-Black");
            System.out.println("5   ----    Salir");
            System.out.println("Ingresa una opcion: ");
            try{
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e){
                System.out.println("Ingresa el número que corresponde a la opción.");
                opcion = 100;
            }
            switch (opcion) {
                case 1 -> MenuAritmetico.ejecutarMenu(sc);
                case 2 -> MenuHeap.ejecutarMenu(sc);
                case 3 -> MenuAVL.ejecutarMenu(sc);
                case 4 -> System.out.println("");//Aqui va redblack
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }

}
