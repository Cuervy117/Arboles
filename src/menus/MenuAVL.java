package menus;

import java.util.Scanner;
import arboles.AVL.*;
import arboles.binario.Nodo;
public class MenuAVL implements Menu {
    private static ArbolAVL<Integer> arbol;

    public static void opciones(){
        System.out.println("1   ----    Crear Arbol");
        System.out.println("2   ----    Agregar clave");
        System.out.println("3   ----    Eliminar clave");
        System.out.println("4   ----    Buscar clave");
        System.out.println("5   ----    Imprimir arbol");
        System.out.println("6   ----    Salir de Arboles AVL");
    }

    public static void ejecutarMenu(Scanner sc){
        int opcion;

        do {

            opciones();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingresa el valor que tendrá la raiz. " 
                    + "\nEn caso de querer crear un arbol vació puede dejar el espacio en blanco");
                    if(arbol != null){
                        System.out.println("Estas a punto de sobre-escribir tu antiguo arbol"
                        + "\n¿Quieres proceder? " 
                        + "\n[S / N]");
                        if(sc.nextLine().equals("N")) break;
                    }
                        Integer raiz;
                    try{
                        raiz = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                         raiz = null;
                    }
                    arbol = new ArbolAVL<>(raiz);
                }
                case 2 -> {
                    if(arbol == null){
                        System.out.println("Aun no haz creado un arbol AVL, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Ingresa el valor del nodo que deseas agregar");
                    Integer valor;
                    try{
                        valor = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                        System.out.println("El arbol AVL solo almacena valores númericos enteros, favor de intentar denuevo");
                        break;
                    }
                    arbol.add(valor);
                    System.out.println(valor + " agregado con exito");
                }
                case 3 -> {
                    if(arbol == null || arbol.getRoot() == null){
                        System.out.println("Aun no haz creado un arbol AVL o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Ingresa el valor del nodo que deseas eliminar");
                    Integer valor;
                    try{
                        valor = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                        System.out.println("El arbol AVL solo almacena valores númericos enteros, favor de intentar denuevo");
                        break;
                    }
                    arbol.delete(valor);
                    System.out.println(valor + " eliminado con exito");
                }
                case 4 -> {
                    if(arbol == null){
                        System.out.println("Aun no haz creado un arbol AVL o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Ingresa el valor del nodo que deseas buscar");
                    Integer valor;
                    try{
                        valor = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                        System.out.println("El arbol AVL solo almacena valores númericos enteros, favor de intentar denuevo");
                        break;
                    }
                    Nodo<Integer> aBuscar = arbol.search(valor);
                    if(aBuscar != null){
                        System.out.println("El valor que buscas si se encuentra en el arbol AVL");
                    } else {
                        System.out.println("El nodo que buscas no se encuentra en el arbol AVL");
                    }
                }
                case 5 -> {
                    arbol.notPrefija(arbol.getRoot());
                }
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println(arbol.getRoot().getClave());
        } while (opcion != 4);

    }
}
