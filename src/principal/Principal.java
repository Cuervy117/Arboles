package principal;
import java.util.ArrayList;

import AVL.*;
import arbolBinario.Nodo;
import archivos.*;
/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
        /*ArbolAVL<Integer> nuevoArbol = new ArbolAVL<>(new NodeAVL<>(20));
        NodeAVL<Integer> node1 = new NodeAVL<>(15);
        NodeAVL<Integer> node2 = new NodeAVL<>(10);
        NodeAVL<Integer> node3 = new NodeAVL<>(12);
        NodeAVL<Integer> node4 = new NodeAVL<>(25);
        NodeAVL<Integer> node5 = new NodeAVL<>(30);
        NodeAVL<Integer> node6 = new NodeAVL<>(28);
        nuevoArbol.add(node1);
        nuevoArbol.add(node2);
        nuevoArbol.add(node3);
        nuevoArbol.add(node4);
        nuevoArbol.add(node5);
        nuevoArbol.add(node6); 
        ArrayList<ArbolAVL<Integer>> arboles = new ArrayList<>();
        //arboles.add(nuevoArbol);
        Archivos.leerBaseDeDatos(arboles);
        ArbolAVL.preOrder(arboles.get(0).getRoot());
        Archivos.guardarDatos(arboles);*/
        ArbolAVL<Integer> nuevoArbol = new ArbolAVL<>(new NodeAVL<>(20));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(15));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(10));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(12));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(25));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(30));
        nuevoArbol.add((Nodo<Integer>)new NodeAVL<>(28));
        ArbolAVL.preOrder(nuevoArbol.getAVLRoot());
        // Guardar el árbol en la base de datos
        ArrayList<ArbolAVL<Integer>> arboles = new ArrayList<>();
        arboles.add(nuevoArbol);
        Archivos.guardarDatos(arboles); // Guardar el árbol en el archivo

        // Limpiar la lista y cargar desde la base de datos para verificar
        arboles.clear();
        Archivos.leerBaseDeDatos(arboles); // Cargar árboles desde el archivo

        // Verificar el árbol cargado
        if (!arboles.isEmpty()) {
            ArbolAVL<Integer> arbolCargado = arboles.get(0);
            ArbolAVL.preOrder(arbolCargado.getAVLRoot()); // Recorrer el árbol en preorden
        } else {
            System.out.println("No se pudo cargar el árbol desde la base de datos.");
        }
    
    }
}
