/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles.AVL;

import arboles.binario.Nodo;
import arboles.deBusqueda.*;

/**
 * Clase que representa a un arbol AVL, el cual cumple con la condición de ser un arbol balanceado.
 * Contiene los métodos necesarios para la inserción y eliminación de nodos.
 * Contiene los métodos necesarios para el balanceo del arbol mediante rotaciones simples y dobles. 
 * 
 * Extención de un arbol binario de búsqueda. 
 * @param <T> El tipo de dato que contienen los nodos del arbol, para su correcto funcionamiento es necesario que el dato
 *            implemente la interfaz "Comparable".
 * @author David
 */
public class ArbolAVL<T extends Comparable<T>> extends ABB<T>{

    public ArbolAVL(){
        super();
    }
    
    public ArbolAVL(T clave){
        NodeAVL<T> root = new NodeAVL<>(clave);
        this.root = root;
    }

    public ArbolAVL(Nodo<T> root){
        super(root);
    }
    
    public NodeAVL<T> getAVLRoot(){
        return (NodeAVL<T>) root;
    }

    /**
     * Realiza la inserción de una clave en el arbol de la misma manera que un arbol binario.
     * Posterior a la inserción se realiza un recorrido sobre el arbol en busca de los posibles desequilibrios generados 
     * el nuevo nodo. 
     * @param nodo Nodo que se desea insertar en el arbol
     */
    @Override
    public void add(Nodo<T> nodo){
        super.add(nodo);
        this.restructuring();
    }

    /**
     * Realiza el borrado de una clave en el arbol como si de un arbol bianrio de búsqueda se tratara.
     * Posterior a la eliminación se realiza un recorrido sobre el arbol, realizando las rotaciones necesarias en caso de encontrar
     * nodos desbalanceados para mantener la propiedad de equilibrio de un arbol AVL.
     * @param clave Clave del nodo que se desea eliminar
     * @throws Exception Si la clave no se encuentra en el arbol ocurrirá un error.
     */
    @Override
    public void delete(T clave) throws Exception{
        super.delete(clave);
        this.restructuring();
    }

    /**
     * Método el cual realiza una rotación simple con dirección hacia la derecha.
     * Esta rotación simple hacia la derecha se dá cuando el factor de equilibrio es menor a -1, pues esto denota
     * que el arbol está cargado a la izquierda, es decir, el sub-arbol izquierdo es más grande que el sub-arbol derecho.
     * Además, para que este método funcione efectivamente, el nodo hijo sobre el cual se realiza la rotación
     * debe de tener el mismo signo.
     * 
     * Reposiciona los nodos involucrados para eliminar el desbalance en el nodo rotado, dando como resultado un sub-arbol
     * de factor 0
     * @param node Nodo desbalanceado sobre el cual se realizará la rotación.
     *             La ubicación de este nodo sirve como eje de rotación para la estructura de 3 nodos que se rotan.
     */
    public void simpleRightRotation(NodeAVL<T> node){
        if(node == null) return;
        Nodo<T> child = node.getHijoIzquierdo();
        if(child == null)return;
        
        Nodo<T> parent = node.getPadre();
        
        node.setHijoIzquierdo(child.getHijoDerecho());
        if(child.getHijoDerecho() != null) child.getHijoDerecho().setPadre((Nodo<T>) node);
        
        child.setPadre(parent);
        if(parent == null)root = child;
        else if(node == parent.getHijoIzquierdo()) parent.setHijoIzquierdo(child);
        else parent.setHijoDerecho(child);
        
        child.setHijoDerecho((Nodo<T>) node);
        node.setPadre(child);
    }

    /**
     * Método el cual realiza una rotación simple con dirección hacia la izquierda.
     * Esta rotación simple hacia la izquierda se dá cuando el factor de equilibrio es mayor a 1, pues esto denota
     * que el arbol está cargado a la derecha, es decir, el sub-arbol derecho es mas grande que el sub-arbol izquierdo.
     * Además, para que este método funcione efectivamente, el nodo hijo sobre el cual se realiza la rotación
     * debe de tener el mismo signo.
     * 
     * Reposiciona los nodos involucrados para eliminar el desbalance en el nodo rotado, dando como resultado un sub-arbol
     * de factor 0
     * @param node Nodo desbalanceado sobre el cual se realizará la rotación.
     *             La ubicación de este nodo sirve como eje de rotación para la estructura de 3 nodos que se rotan.
     */
    public void simpleLeftRotation(NodeAVL<T> node){
        if(node == null) return;
        Nodo<T> child = node.getHijoDerecho();
        if(child == null) return;
        
        Nodo<T> parent = node.getPadre();
        
        node.setHijoDerecho(child.getHijoIzquierdo());
        if(child.getHijoIzquierdo() != null) child.getHijoIzquierdo().setPadre((Nodo<T>) node);
        
        child.setPadre(parent);
        if(parent == null) root = child;
        else if(node == parent.getHijoIzquierdo()) parent.setHijoIzquierdo(child);
        else parent.setHijoDerecho(child);
        
        child.setHijoIzquierdo((Nodo<T>) node);
        node.setPadre(child);
    }

    /**
     * Método el cual realiza una rotación doble con dirección hacia la derecha.
     * Este caso se da cuando el factor de equilibrio es menor a -1, lo que quiere decir que el sub-arbol izquierdo es más 
     * grande que el sub-arbol derecho. Además de que para que el método sea efectivo, el nodo hijo debe de tener el signo opuesto.
     * 
     * La rotación se da en dos pasos:
     * Paso 1 : Rota en dirección contraria al sub-arbol cargado, esto para generar las condiciones necesarias para una rotación simple.
     * Paso 2 : Realizar la rotación simple para generar un arbol balanceado de factor 0.
     * 
     * @param node Nodo desbalanceado sobre el cual se realiza la rotación simple hacia la derecha, no sin antes haber rotado 
     *             en sentido contrario a su nodo hijo correspondiente al sub-arbol cargado.
     */
    public void doubleRightRotation(NodeAVL<T> node){
        this.simpleLeftRotation((NodeAVL<T>) node.getHijoIzquierdo());
        this.simpleRightRotation(node);
    }

    /**
     * Método el cual realiza una rotación doble con dirección hacia la uzquierda.
     * Este caso se da cuando el factor de equilibrio es mayor a 1, lo que quiere decir que el sub-arbol derecho es más 
     * grande que el sub-arbol izquierdo. Además de que para que el método sea efectivo, el nodo hijo debe de tener el signo opuesto.
     * 
     * La rotación se da en dos pasos:
     * Paso 1 : Rota en dirección contraria al sub-arbol cargado, esto para generar las condiciones necesarias para una rotación simple.
     * Paso 2 : Realizar la rotación simple para generar un arbol balanceado de factor 0.
     * 
     * @param node Nodo desbalanceado sobre el cual se realiza la rotación simple hacia la derecha, no sin antes haber rotado 
     *             en sentido contrario a su nodo hijo correspondiente al sub-arbol cargado.
     */
    public void doubleLeftRotation(NodeAVL<T> node){
        this.simpleRightRotation((NodeAVL<T>) node.getHijoDerecho());
        this.simpleLeftRotation(node);
    }

    /**
     * Método que busca el primer nodo que no cumple con la condición de equilibrio de un arbol AVL. 
     * Dicha condición hace referencia a que no pueden existir nodos con un factor de equilibrio menor a -1 o mayor a 1
     * lo cual se traduce a una diferencia de alturas máxima de uno.
     * @param node Nodo sobre el cual se realizará su busqueda, analizando sus sub-arboles.
     * @return El primer nodo que no cumple con la condición de equilibrio, en el caso de que todos los nodos estén 
     *         equilibrados retornará null.
     */
    public NodeAVL<T> balanced(NodeAVL<T> node){
        if(node == null) return null;
        int actualWeight = node.getWeight();
        if(actualWeight > 1 || actualWeight < -1) return node;
        else{ 
            NodeAVL<T> hijoIzquierdo = balanced((NodeAVL<T>) node.getHijoIzquierdo());
            if(hijoIzquierdo != null) return hijoIzquierdo;
            NodeAVL<T> hijoDerecho = balanced((NodeAVL<T>) node.getHijoDerecho());
            if(hijoDerecho != null) return hijoDerecho;
            return null; 
        }
    }

    /**
     * Realiza un recorrido completo del arbol sobre el que se llama en busca de nodos desbalanceados,esto con el proposito
     * de encontrar y corregir los nodos desbalanceados. Este método mantiene la propiedad de equilibrio de un arbol AVL.
     * 
     * El proceso de recorrido se realiza hasta que no existan nodos desbalanceados dentro del arbol.
     * 
     * Al encontrar un nodo desbalanceado evalua su factor de equilibrio para la toma de decisión sobre el tipo 
     * de rotación necesaria para solucionar dicho problema.
     * 
     * En los posibles casos se contemplan:
     * Nodo desbalanceado de signo positivo (cargado hacia la derecha)
     * Nodo desbalanceado de signo negativo (cargado hacia la izquierda)
     * Nodo desbalanceado y nodo hijo de diferente signo.
     * Nodo desbalanceado y nodo hijo del mismo signo.
     */
    public void restructuring(){
        NodeAVL<T> unbalanced;
        do {
            unbalanced =  balanced(this.getAVLRoot()); 
            if(unbalanced != null){
                if(unbalanced.getWeight() < -1){
                    if(((NodeAVL<T>) unbalanced.getHijoIzquierdo()).getWeight() < 0){
                        this.simpleRightRotation(unbalanced);
                    }else{
                        this.doubleRightRotation(unbalanced); 
                    }
                }else if(unbalanced.getWeight() > 1){
                    if(((NodeAVL<T>) unbalanced.getHijoDerecho()).getWeight() > 0){
                        this.simpleLeftRotation(unbalanced);
                    }else{
                        this.doubleLeftRotation(unbalanced);
                    }
                }
            }
        } while (unbalanced != null);
    }

}
