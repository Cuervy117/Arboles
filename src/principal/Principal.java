/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.util.Scanner;
import menus.*;

/**
 * Programa el cual nos ofrece la posibilidad de trabajar con 4 tipos de arbol binario, cada uno con su forma de
 * trabajar distinta, desplegando menús personalizados para cada caso.
 * 
 * Este programa nos permite verificar el funcionamiento de distintas implementaciones de los arboles binarios.
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
                case 4 -> new MenuRedBlack().ejecutarMenu(sc);//Aqui va redblack
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }

}
