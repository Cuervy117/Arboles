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
            int compracion = nodo.getClave().compareTo(rootAux.getClave());
            if(rootAux.getClave() == nodo.getClave()) break;
            else if(compracion > 0){
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
