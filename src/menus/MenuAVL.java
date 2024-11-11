package menus;

import java.util.Scanner;
import arboles.AVL.*;
import arboles.binario.Nodo;
import archivos.Archivos;
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
        try {
            Object objetoLeido = Archivos.leerBaseDeDatos("AVL");
            if (objetoLeido instanceof ArbolAVL) {
                arbol = (ArbolAVL<Integer>) objetoLeido;
            } else {
                System.out.println("Error: El archivo no contiene un objeto ArbolAVL.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el árbol AVL: " + e.getMessage());
        }
        int opcion;
        do {
            opciones();
            System.out.println("Ingresa una opcion: ");
            try{
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e){
                System.out.println("Ingresa el número que corresponde a la opción.");
                opcion = 100;
            }
            switch (opcion) {
                case 1 -> {
                    if(arbol != null){
                        System.out.println("Estas a punto de sobre-escribir tu antiguo arbol"
                        + "\n¿Quieres proceder? " 
                        + "\n[S / N]");
                        if(sc.nextLine().equals("N")) 
                        break;
                    }
                    Integer raiz = null;
                    System.out.println("Ingresa el valor que tendrá la raiz. " 
                    + "\nEn caso de querer crear un arbol vació puede dejar el espacio en blanco");

                    try{
                        raiz = Integer.parseInt(sc.nextLine());
                        arbol = new ArbolAVL<>(raiz);
                    }catch (Exception e){
                        arbol = new ArbolAVL<>();
                    }
                    System.out.println("Arbol de raiz " + raiz + " creado");
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
                    arbol.add(new NodeAVL<>(valor));
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
                    try{
                        arbol.delete(valor);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
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
                    Nodo<Integer> aBuscar;
                    try{
                        aBuscar = arbol.search(valor);
                        System.out.println("El nodo de valor " + aBuscar.getClave() + " Se encuentra en la altura " + ( (NodeAVL<Integer>) aBuscar).getHeight());

                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    if(arbol == null){
                        System.out.println("Aun no haz creado un arbol AVL o está vacio, intenta seleccionando la opción 1 primero");
                        break;
                    }
                    System.out.println("Imprimiendo arbol...");
                    MenuImprimir.ejecutarMenu(sc, arbol);
                }
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
        Archivos.guardarDatos(arbol, "AVL");
    }
}
