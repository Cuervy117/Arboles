package menus;

import java.util.ArrayList;
import java.util.Scanner;

import arboles.binario.ArbolBinario;

public class MenuImprimir<T> implements Menu {
    /**
     * Lista de valores genericos que almacena los valores de un arbol en cierta notación para su posterior
     * impresión.
     */
    ArrayList<T> lista = new ArrayList<>();

        /**
     * Método que imprime en pantalla todas las posibles opciones en este menú
     */
    private static void opciones(){
        System.out.println("1   ----    Recorrido en PreOrden");
        System.out.println("2   ----    Recorrido en InOrden");
        System.out.println("3   ----    Recorrido en PostOrden");
    }

    /**
     * Método el cual se encarga de la lectura de la opcion requerida por el usuario, posterior a la elección se 
     * ejecutará uno u otro algoritmo de notación del arbol. 
     * @param <T> Tipo generico del arbol a trabajar en cuestion.
     * @param sc Objeto de tipo Scanner para la lectura de las opciones mediante la consola.
     * @param arbol Arbol binario sobre el cual se realizará el recorrido para su impresión.
     */
    public static <T> void ejecutarMenu(Scanner sc, ArbolBinario<T> arbol){
        ArrayList<T> lista = new ArrayList<>();
        int opcion = 0;
        opciones();
        try{
            opcion = Integer.parseInt(sc.nextLine());
        } catch (Exception e){
            System.out.println("Ingresa el número que corresponde a la opción.");
        }
        switch (opcion) {
            case 1 -> {
                    ArbolBinario.notPrefija(arbol.getRoot(), lista);
                    System.out.println(lista.toString());
            }
            case 2 -> {
                    ArbolBinario.notInfija(arbol.getRoot(), lista);
                    System.out.println(lista.toString());
            }
            case 3 -> {

                    ArbolBinario.notPostfija(arbol.getRoot(), lista);
                    System.out.println(lista.toString());
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    
}
