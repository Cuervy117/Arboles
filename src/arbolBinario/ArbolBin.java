package arbolBinario;

import java.io.Serializable;

public class ArbolBin<T extends Comparable<T>> implements Serializable {
    protected Nodo<T> root;
    
    public ArbolBin(){

    }
    
    public ArbolBin(T val){
        root = new Nodo<T>(val);
    }
    
    public ArbolBin(Nodo<T> root){
        this.root = root;
    }

    public Nodo<T> getRoot(){
        return root;
    }
    
    public void add(Nodo<T> nodo){
        Nodo<T> rootAux = root;
        if(root == null){
            this.root = nodo;
            return;
        }
        while(true){
            int comparacion = nodo.getClave().compareTo(rootAux.getClave());
            if(comparacion == 0) break;
            else if(comparacion > 0){
                if(rootAux.getHijoDerecho() != null){
                    rootAux = rootAux.getHijoDerecho();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoDerecho(nodo);
                    break;
                }
            }else{
                if(rootAux.getHijoIzquierdo() != null){
                    rootAux = rootAux.getHijoIzquierdo();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoIzquierdo(nodo);
                    break;
                }                    
            }
        }
    }

    public void eliminarNodo(Nodo<T> nodo){
        if(nodo.isLeaf()){ //Caso de nodo Hoja
            eliminarHoja(nodo);
        }else{
            Nodo<T> reemplazo = nodo.getReemplazo();
            //asilamos al reemplazo
            Nodo<T> padreReemplazo = reemplazo.getPadre();
            if(padreReemplazo.getHijoDerecho() == reemplazo) padreReemplazo.setHijoDerecho(null);
            else padreReemplazo.setHijoIzquierdo(null);
            //reasignamos los hijos
            reemplazo.setHijoDerecho(nodo.getHijoDerecho());
            reemplazo.setHijoIzquierdo(nodo.getHijoIzquierdo());

            //reasignamos la referencia del padre
            Nodo<T> padreNodo = nodo.getPadre();
            reemplazo.setPadre(padreNodo);
            if(padreNodo.getHijoDerecho() == nodo) padreNodo.setHijoDerecho(reemplazo);
            else padreNodo.setHijoIzquierdo(reemplazo);    
        }
    }

    private void eliminarHoja(Nodo<T> hoja){
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
