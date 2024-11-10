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
    public static void main(String[] args) throws Exception {
        Nodo<Integer> nodo1 = new Nodo<>(12);
        Heap<Integer> arbol = new Heap<>(nodo1);
        arbol.add(5);
        arbol.add(11);
        arbol.add(1);
        arbol.add(2);
        arbol.add(20);
        arbol.add(40);
        arbol.add(50);
        arbol.add(500);
        arbol.notPrefija(arbol.getRoot());
        arbol.delete(12);
        System.out.println("eliminado");
        arbol.notPrefija(arbol.getRoot());
        

    }
}
