package arboles.heap;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
/**
 * Clase que representa un arbol del tipo min-heap.
 * Extención de un arbol binario, contiene los métodos de inserción y eliminación que cumplen
 * con las condiciones de un min-heap.
 * 
 * Estas condiciones son:
 * 1.- Cada nodo se debe de insertar en posición consiguiente al ultimo nodo insertado, es decir, la
 * inserción se realiza llenando cada nivel primero por la izquierda hasta terminar con la derecha antes de crear 
 * un nuevo nivel.
 * 2.- Cada nodo padre debe de tener una clave menor a la de sus nodos hijos.
 */
public class Heap<T extends Comparable<T>> extends ArbolBinario<T>{
    /**
     * Constructor de un heap vacio, con raiz nula
     */
    public Heap(){
        super();
    }
    /**
     * Constructor de un heap con raiz no nula
     * @param nodo la raiz que asumirá el nuevo heap
     */
    public Heap(Nodo<T> nodo){
        super(nodo);
    }
    /**
     * Constructor de un Heap con raiz no nula
     * @param clave Valor que tomará la raiz
     */
    public Heap(T clave){
        super(clave);
    }
    /**
     * Método que inserta un nodo en el heap, realiza la inserción de un arbol binario.
     * Posterior a la inserción se realiza una verificación hacia arriba de los valores de la clave insertada.
     * Si la clave del nodo insertado es menor a la de su nodo padre se realizará un intercambio entre estos dos nodos, manteniendo
     * la propiedad de un min-heap.
     * 
     * Esto sucederá hasta llegar a la raiz del heap.
     * @param nodo Nodo que se desea insertar.
     */
    @Override
    public void add(Nodo<T> nodo){
        super.add(nodo);

        while (nodo.getPadre() != null && nodo.getClave().compareTo(nodo.getPadre().getClave()) < 0) {
            nodeSwapHeap(nodo.getPadre(), nodo);
        }
        
    }

    /**
     * Método que realiza la eliminación de un clave dentro del arbol. Implementando el funcionamiento de un arbol binario.
     * Gracias a la naturaleza de la inserción, no es necesario re-estructurar todo el arbol.
     * 
     * Despues de la eliminación del nodo se re-estructurará el arbol de arriba hacia abajo partiendo del nodo intercambiado pues 
     * la posición del nodo eliminado es la unica que puede violar las propiedades de un min-heap, esto para mantener la propiedad
     * de un min-heap.
     */
    @Override
    public void delete(T clave) throws Exception{
        if(root == null) return;
        Nodo<T> nodo;
        Nodo<T> ultimo;
        nodo = search(clave);
        if(nodo.isLeaf()){
            eliminarHoja(nodo);
        } else {
            ultimo = obtenerUltimo();
            nodeSwap(nodo, ultimo);
            eliminarHoja(nodo);
            minHeapify(ultimo);
        }
    }

    /**
     * Realiza una primera comprobacion sobre la raiz, si esta no cumple con la propiedad de un min-heap se realizará
     * el intercambio pertinente. 
     * 
     * Debido a la naturaleza de la inserción, cuando se realice la eliminación solo será afectado el sub-arbol del nodo eliminado, 
     * perteneciendo ahora al nodo por el cual se intercambió.
     * 
     * Se comparan los valores de los nodos hijos para determinar el nodo más pequeño, si el nodo más pequeño no es la raiz se realizará un intercambio
     * entre esos nodos y se aplicará el método recursivamente sobre el sub-arbol modificado para verificar la propiedad
     * de los nodos subsecuentes.
     * @param root
     */
    private void minHeapify(Nodo<T> root){
        if(root == null || root.isLeaf()  ) return;
        
        Nodo<T> der = root.getHijoDerecho();
        Nodo<T> izq = root.getHijoIzquierdo();
    
        Nodo<T> pequeño = root;

        if(izq != null && izq.getClave().compareTo(pequeño.getClave()) < 0) {
            pequeño = izq;  
        }
        if(der != null && der.getClave().compareTo(pequeño.getClave()) < 0) {
            pequeño = der;
        }
        if(pequeño != root){
            nodeSwapHeap(root, pequeño);
            minHeapify(root);
        }
    }

    /**
     * Método el cual realiza un intercambio de nodos, actualizando los punteros de hijos y padres de los nodos involucrados.
     * 
     * Este método es exclusivo al caso donde el nodo objetivo es un nodo hoja, por ello es indispensable su utilización en conjunto
     * del método "obtenerUltimo".
     * 
     * El proposito de este método es el de desplazar un nodo con clave mayor a sus hijos hacia abajo.
     * @param origen
     * @param objetivo
     */
    public void nodeSwapHeap(Nodo<T> origen, Nodo<T> objetivo){
        Nodo<T> auxDerecho = origen.getHijoDerecho();
        Nodo<T> auxIzquierdo = origen.getHijoIzquierdo();
        Nodo<T> auxPadre = origen.getPadre();
        
        origen.setHijoDerecho(objetivo.getHijoDerecho());
        origen.setHijoIzquierdo(objetivo.getHijoIzquierdo());

        actualizarPadre(objetivo, origen);

        actualizarPadre(origen, objetivo);

        origen.setPadre(objetivo);

        if(origen == root){
            setRoot(objetivo);
            objetivo.setPadre(null);
        } else {
            objetivo.setPadre(auxPadre);
        }

        if(objetivo == auxDerecho) {
            objetivo.setHijoDerecho(origen);
            objetivo.setHijoIzquierdo(auxIzquierdo);

        } else {
            objetivo.setHijoIzquierdo(origen);
            objetivo.setHijoDerecho(auxDerecho);
        }

        if(objetivo.getHijoDerecho() != null){
            objetivo.getHijoDerecho().setPadre(objetivo);
        }
        
        if(objetivo.getHijoIzquierdo() != null){
            objetivo.getHijoIzquierdo().setPadre(objetivo);
        }

        if(origen.getHijoDerecho() != null){
            origen.getHijoDerecho().setPadre(origen);
        }
        
        if(origen.getHijoIzquierdo() != null){
            origen.getHijoIzquierdo().setPadre(origen);
        }
    }
}
