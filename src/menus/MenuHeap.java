package menus;

import arboles.heap.*;
import archivos.Archivos;

import java.util.Scanner;

import arboles.binario.Nodo;
public class MenuHeap implements Menu {
    private static Heap<Integer> arbol;

    private static void opciones(){
        System.out.println("1   ----    Crear Arbol");
        System.out.println("2   ----    Agregar clave");
        System.out.println("3   ----    Eliminar clave");
        System.out.println("4   ----    Imprimir arbol");
        System.out.println("5   ----    Salir de Arboles Heap");
    }

    @SuppressWarnings("unchecked")
    public static void ejecutarMenu(Scanner sc){
        int opcion;
        try {
            Object objetoLeido = Archivos.leerBaseDeDatos("Heap");
            if (objetoLeido instanceof Heap) {
                arbol = (Heap<Integer>) objetoLeido;
            } else {
                System.out.println("Error: El archivo no contiene un objeto Heap.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el árbol AVL: " + e.getMessage());
        }
        do {
            opciones();
            System.out.println("Ingresa una opcion: ");
            try{
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e){
                System.out.println("Ingresa el número que corresponde a la opción");
                opcion = 100;
            }
            switch (opcion) {
                case 1 -> {
                    if(arbol != null){
                        System.out.println("Estas a punto de sobre-escribir tu antiguo arbol"
                        + "\n¿Quieres proceder? " 
                        + "\n[S / N]");
                        if(sc.nextLine().equals("N")) break;
                    }
                    Integer raiz = null;

                    System.out.println("Ingresa el valor que tendrá la raiz. " 
                    + "\nEn caso de querer crear un arbol vació puede dejar el espacio en blanco");

                    try{
                        raiz = Integer.parseInt(sc.nextLine());
                        arbol = new Heap<>(raiz);
                    }catch (Exception e){
                        arbol = new Heap<>();
                    }
                    System.out.println("Arbol de raiz " + raiz + " creado");
                }
                case 2 -> {
                    if(arbol == null){
                        System.out.println("Aun no haz creado un Heap, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Ingresa el valor del nodo que deseas agregar");
                    Integer valor;
                    try{
                        valor = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                        System.out.println("El Heap solo almacena valores númericos enteros, favor de intentar denuevo");
                        break;
                    }
                    arbol.add(new Nodo<>(valor));
                    System.out.println(valor + " agregado con exito");
                }
                case 3 -> {
                    if(arbol == null || arbol.getRoot() == null){
                        System.out.println("Aun no haz creado un Heap o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Ingresa el valor del nodo que deseas eliminar");
                    Integer valor;
                    try{
                        valor = Integer.parseInt(sc.nextLine());
                    }catch (Exception e){
                        System.out.println("El Heap AVL solo almacena valores númericos enteros, favor de intentar denuevo");
                        break;
                    }
                    try{
                        arbol.delete(valor);
                        System.out.println(valor + " eliminado con exito");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    if(arbol == null || arbol.getRoot() == null){
                        System.out.println("Aun no haz creado un Heap o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Imprimiendo arbol...");
                    MenuImprimir.ejecutarMenu(sc, arbol);
                }
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
        Archivos.guardarDatos(arbol, "Heap");
    }
}
