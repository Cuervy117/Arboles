<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import Redblack.*;

=======
package principal;
import java.util.ArrayList;

import arboles.AVL.*;
import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
import archivos.*;
>>>>>>> f1892418f061e8a1e0b240d643fad74e4a21fa64
/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
<<<<<<< HEAD
        Arbolredblack rojinegro = new Arbolredblack();
        rojinegro.insertar(new Nodo(11));
        rojinegro.insertar(new Nodo(2));
        rojinegro.insertar(new Nodo(14));
        rojinegro.insertar(new Nodo(14));
        rojinegro.insertar(new Nodo(1));
        //rojinegro.insertar(new Nodo(7));
        //rojinegro.insertar(new Nodo(15));
        //rojinegro.insertar(new Nodo(8));
        //rojinegro.insertar(new Nodo(4));


        rojinegro.recorridoPreOrden(rojinegro.getRaiz());
        
        System.out.println("Agregado");
=======
        Nodo<Integer> nodo1 = new Nodo<>(12);
        Nodo<Integer> nodo2 = new Nodo<>(5);
        Nodo<Integer> nodo3 = new Nodo<>(11);
        Nodo<Integer> nodo4 = new Nodo<>(1);
        Nodo<Integer> nodo5 = new Nodo<>(2);
        Nodo<Integer> nodo6 = new Nodo<>(20);
        Nodo<Integer> nodo7 = new Nodo<>(40);
        Nodo<Integer> nodo8 = new Nodo<>(50);
        Nodo<Integer> nodo9 = new Nodo<>(500);
        ArbolBinario<Integer> arbol = new ArbolBinario<>(nodo1);
        arbol.add(nodo2);
        arbol.add(nodo3);
        arbol.add(nodo4);
        arbol.add(nodo5);
        arbol.add(nodo6);
        arbol.add(nodo7);
        arbol.add(nodo8);
        arbol.add(nodo9);
        arbol.notPrefija(arbol.getRoot());
        arbol.nodeSwap(nodo8, nodo9);
        System.out.println("intercambio");
        arbol.notPrefija(arbol.getRoot());
>>>>>>> f1892418f061e8a1e0b240d643fad74e4a21fa64

    }
}
