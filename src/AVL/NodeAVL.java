/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

import arbolBinario.*;
/**
 *
 * @author David
 */
public class NodeAVL<T> extends Nodo<T>{
    private static final long serialVersionUID = 123456789L;

    private NodeAVL<T> hijoIzquierdo;
    private NodeAVL<T> hijoDerecho; 
    private NodeAVL<T> padre;
    
    public NodeAVL(){

    }
    
    public NodeAVL(T clave){
        this.clave = clave;
    }
    
    public boolean isLeaf(){
        return (hijoIzquierdo == null) && (hijoDerecho == null) ? true : false;
    }
    
    public T getClave() {
        return clave;
    }

    public void setclave(T clave) {
        this.clave = clave;
    }

    public NodeAVL<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodeAVL<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodeAVL<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodeAVL<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    public NodeAVL<T> getPadre(){
        return padre;
    }
    
    public void setPadre(NodeAVL<T> padre){
        this.padre = padre;
    }
    
    public int getHeight(){
        if(this.isLeaf())return 0;
        else{
            return 1 + Math.max(hijoDerecho != null ? hijoDerecho.getHeight() : -1,
                    hijoIzquierdo != null ? hijoIzquierdo.getHeight() : -1);
        }
    }
    
    public int getWeight(){
        return (hijoDerecho != null ? hijoDerecho.getHeight() : -1) 
        - (hijoIzquierdo != null ? hijoIzquierdo.getHeight() : -1);
    }
}
