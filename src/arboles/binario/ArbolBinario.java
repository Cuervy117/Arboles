package arboles.binario;

import java.io.Serializable;

public class ArbolBinario<T> implements Serializable{
    protected Nodo<T> root;

    public ArbolBinario(){

    }

    public ArbolBinario(T clave){
        root = new Nodo<T>(clave);
    }
    
    public ArbolBinario(Nodo<T> root){
        this.root = root;
    }

    public Nodo<T> getRoot(){
        return root;
    }
    
    public void add(Nodo<T> nodo){
        
    }

    public void delete(Nodo<T> nodo){

    }
    
    protected void eliminarHoja(Nodo<T> hoja){
        if(!hoja.isLeaf()) return;
        Nodo<T> padre = hoja.getPadre();
        if(padre == null) root = null;
        else if(hoja == padre.getHijoIzquierdo()) padre.setHijoIzquierdo(null);
        else padre.setHijoDerecho(null);
    }

    public void notPrefija(Nodo<T> r) {
        if (r != null) {
            System.out.println(r.getClave());
            notPrefija(r.getHijoIzquierdo());
            notPrefija(r.getHijoDerecho());
        }   
    }

    public void notInfija(Nodo<T> r){
        if (r != null) {
            notInfija(r.getHijoIzquierdo());
            System.out.println(r.getClave());
            notInfija(r.getHijoDerecho());
        }  
    }

    public void notPostfija(Nodo<T> r) {
        if (r != null) {
            notPostfija(r.getHijoIzquierdo());
            notPostfija(r.getHijoDerecho());
            System.out.println(r.getClave());
        }  
    } 
}
