/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles.AVL;

import arboles.binario.Nodo;
/**
 *
 * @author David
 */
public class NodeAVL<T> extends Nodo<T>{
    
    public NodeAVL(){
        super();
    }
    
    public NodeAVL(T clave){
        super(clave);
    }
    
    public int getHeight(){
        if(this.isLeaf()) return 0;
        else{
            NodeAVL<T> alturaHijoDerecho =  (NodeAVL<T>) this.getHijoDerecho();
            NodeAVL<T> alturaHijoIzquierdo =  (NodeAVL<T>) this.getHijoIzquierdo();
            return 1 + Math.max(hijoDerecho != null ? alturaHijoDerecho.getHeight() : -1,
                    hijoIzquierdo != null ? alturaHijoIzquierdo.getHeight() : -1);
        }
    }
    
    public int getWeight(){
        NodeAVL<T> pesoHijoDerecho =  (NodeAVL<T>) this.getHijoDerecho();
        NodeAVL<T> pesoHijoIzquierdo =  (NodeAVL<T>) this.getHijoIzquierdo();
        return (hijoDerecho != null ? pesoHijoDerecho.getHeight() : -1) 
        - (hijoIzquierdo != null ? pesoHijoIzquierdo.getHeight() : -1);
    }
}
