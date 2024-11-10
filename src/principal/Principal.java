package principal;
import java.util.ArrayList;

import arboles.AVL.*;
import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
import arboles.heap.Heap;
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
        Heap<Integer> arbol = new Heap<>(nodo1);
        arbol.add(nodo2);
        System.out.println("agregado");
        arbol.add(nodo3);
        System.out.println("agregado");
        arbol.add(nodo4);
        System.out.println("agregado");
        arbol.add(nodo5);
        System.out.println("agregado");
        arbol.add(nodo6);
        System.out.println("agregado");
        arbol.add(nodo7);
        System.out.println("agregado");
        arbol.add(nodo8);
        System.out.println("agregado");
        arbol.add(nodo9);
        System.out.println("agregado");
        arbol.notPrefija(arbol.getRoot());
        //System.out.println("eliminando 11");
        //arbol.delete(nodo3);
        //arbol.notPrefija(arbol.getRoot());

    }
}
