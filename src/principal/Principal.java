/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import Redblack.*;

/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
        Arbolredblack rojinegro = new Arbolredblack();
        rojinegro.insertar(new Nodo(5));

        System.out.println(rojinegro.notacionPrefija());
        
        System.out.println("Agregado");

    }
}
