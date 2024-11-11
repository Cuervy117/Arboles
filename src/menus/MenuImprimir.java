package menus;

import java.util.ArrayList;
import java.util.Scanner;

import arboles.binario.ArbolBinario;

public class MenuImprimir implements Menu {
    private static ArrayList<Integer> impresion = new ArrayList<>();

    private static void opciones(){
        System.out.println("1   ----    Recorrido en PreOrden");
        System.out.println("2   ----    Recorrido en InOrden");
        System.out.println("3   ----    Recorrido en PostOrden");
    }

    public static <T> void ejecutarMenu(Scanner sc, ArbolBinario<T> arbol){
        int opcion = 0;
        opciones();
        try{
            opcion = Integer.parseInt(sc.nextLine());
        } catch (Exception e){
            System.out.println("Ingresa el número que corresponde a la opción.");
        }
        switch (opcion) {
            case 1 -> {
                ArbolBinario.notPrefija(arbol.getRoot(), impresion);
                System.out.println(impresion.toString());
            }
            case 2 -> {
                ArbolBinario.notInfija(arbol.getRoot(), impresion);
                System.out.println(impresion.toString());

            }
            case 3 -> {
                ArbolBinario.notPostfija(arbol.getRoot(), impresion);
                System.out.println(impresion.toString());

            }
            default -> System.out.println("Opción inválida.");
        }
    }
}
