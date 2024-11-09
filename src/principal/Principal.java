package principal;
import java.util.ArrayList;

import arboles.AVL.*;
import arboles.binario.Nodo;
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
        NodeAVL<Integer> nodo = new NodeAVL<>(25);
        ArbolAVL<Integer> nuevoArbol = new ArbolAVL<>(new NodeAVL<>(20));
        nuevoArbol.add((Nodo<Integer>) new NodeAVL<>(15));
        nuevoArbol.add((Nodo<Integer>) new NodeAVL<>(10));
        nuevoArbol.add((Nodo<Integer>) new NodeAVL<>(12));
        nuevoArbol.add((Nodo<Integer>) nodo);
        nuevoArbol.add((Nodo<Integer>) new NodeAVL<>(30));
        nuevoArbol.add((Nodo<Integer>) new NodeAVL<>(28));
        nuevoArbol.notPrefija(nuevoArbol.getRoot());
        // Guardar el árbol en la base de datos
        ArrayList<ArbolAVL<Integer>> arboles = new ArrayList<>();
        arboles.add(nuevoArbol);
        Archivos.guardarDatos(arboles); // Guardar el árbol en el archivo

        // Limpiar la lista y cargar desde la base de datos para verificar
        // Verificar el árbol cargado
        if (!arboles.isEmpty()) {
            ArbolAVL<Integer> arbolCargado = arboles.get(0);
            arbolCargado.notPrefija(arbolCargado.getRoot()); // Recorrer el árbol en preorden
        } else {
            System.out.println("No se pudo cargar el árbol desde la base de datos.");
        }
        ArbolAVL<Integer> arbolito = arboles.get(0);
        arbolito.delete(nodo);
        System.out.println("nodo eliminado");
        //ArbolAVL.preOrder(arbolito.getAVLRoot());
        arbolito.notPrefija(arbolito.getRoot());
        System.out.println("Buscando 30");
        System.out.println(arbolito.search(30).getClave());
    }
}
