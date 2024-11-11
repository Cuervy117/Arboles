/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles.AVL;

import arboles.binario.Nodo;
/**
 * Clase que representa los nodos propios de un arbol AVL.
 * Contiene los métodos necesarios para obtener la altura y el factor de equilibrio del propio nodo
 * Implementación del nodo de un arbol binario común.
 * 
 * @param <T> Tipo de dato que contiene el nodo.
 * @author David
 */
public class NodeAVL<T> extends Nodo<T>{
    
    public NodeAVL(){
        super();
    }
    
    public NodeAVL(T clave){
        super(clave);
    }
    
    /**
     * Método el cual calcula la altura del nodo sobre el que se llama.
     * Obtiene recursivamente la altura de los sub-arboles, obteniendo solamente la altura más alta.
     * En cada iteración recursiva se suma uno, llegando hasta la raiz.
     * @return  Un valor positivo que representa la altura del arbol dado por el nodo.
     */
    public int getHeight(){
        if(this.isLeaf()) return 0;
        else{
            NodeAVL<T> alturaHijoDerecho =  (NodeAVL<T>) this.getHijoDerecho();
            NodeAVL<T> alturaHijoIzquierdo =  (NodeAVL<T>) this.getHijoIzquierdo();
            return 1 + Math.max(hijoDerecho != null ? alturaHijoDerecho.getHeight() : -1,
                    hijoIzquierdo != null ? alturaHijoIzquierdo.getHeight() : -1);
        }
    }
    
    /**
     * Método el cual calcula el peso o factor de equilibrio del nodo sobre el que se llama.
     * Dicho peso se calcula restando la altura del sub-arbol izquierdo al sub-arbol derecho.
     * 
     * @return Un valor entero que representa el peso o factor de equilibrio.
     *         Retorna un valor positivo si el sub-arbol derecho es más alto que el sub-arbol izquierdo.
     *         Retorna un valor negativo si el sub-arbol izquierdo es más alto que el sub-arbol derecho.
     *         Retorna 0 si ambos arboles tienen la misma altura. 
     */
    public int getWeight(){
        NodeAVL<T> pesoHijoDerecho =  (NodeAVL<T>) this.getHijoDerecho();
        NodeAVL<T> pesoHijoIzquierdo =  (NodeAVL<T>) this.getHijoIzquierdo();
        return (hijoDerecho != null ? pesoHijoDerecho.getHeight() : -1) 
        - (hijoIzquierdo != null ? pesoHijoIzquierdo.getHeight() : -1);
    }
}
