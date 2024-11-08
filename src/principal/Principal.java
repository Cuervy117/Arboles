package principal;
import java.util.ArrayList;

import arboles.AVL.*;
import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
import archivos.*;
/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
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
        arbol.notPrefija(nodo1);
        arbol.delete(nodo9);
        System.out.println("11 eliminado");
        arbol.notPrefija(nodo1);

    }
}
