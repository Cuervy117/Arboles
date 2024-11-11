/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import Redblack.*;
import java.util.Scanner;
import menus.*;

/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
        Arbolredblack rojinegro = new Arbolredblack();
        rojinegro.insertar(new Nodo(11));
        rojinegro.insertar(new Nodo(2));
        rojinegro.insertar(new Nodo(14));
        rojinegro.insertar(new Nodo(1));
        rojinegro.insertar(new Nodo(7));
        rojinegro.insertar(new Nodo(15));
        rojinegro.insertar(new Nodo(5));
        rojinegro.insertar(new Nodo(8));
        rojinegro.insertar(new Nodo(6));

        rojinegro.eliminar(7);
        rojinegro.eliminar(6);
        Arbolredblack.recorridoPreOrden(rojinegro.getRaiz());
        
        System.out.println("Agregado");
        MenuRedBlack menu = new MenuRedBlack();
        menu.ejecutarMenu(new Scanner(System.in));
    }


}
