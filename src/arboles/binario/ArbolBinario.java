package arboles.binario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    public void setRoot(Nodo<T> root){
        this.root = root;
    }
    
    public void add(Nodo<T> nodo){
        try{
            search(nodo.getClave());
            System.out.println("El valor ya se encuentra en el arbol");
            return;
        }catch (Exception e){

        }
        if(root == null) {
            root = nodo;
        } else {
            Nodo<T> auxRoot = root;
            while(true){
                if(auxRoot == nodo) return;
                if(auxRoot.getHijoIzquierdo() == null) {
                    auxRoot.setHijoIzquierdo(nodo);
                    nodo.setPadre(auxRoot);
                    break;
                } else if(auxRoot.getHijoDerecho() == null) {
                    auxRoot.setHijoDerecho(nodo);
                    nodo.setPadre(auxRoot);
                    break;
                } else{
                    if(!auxRoot.getHijoIzquierdo().isFull()) {
                        auxRoot = auxRoot.getHijoIzquierdo();
                    } else if(!auxRoot.getHijoDerecho().isFull()) {
                        auxRoot = auxRoot.getHijoDerecho();
                    } else{
                        auxRoot = auxRoot.getHijoIzquierdo();
                    }
                }
            }
        }
    }

    public void delete(T clave) throws Exception{
        if(root == null) return;
        Nodo<T> nodo;
        nodo = search(clave);
        if(nodo.isLeaf()){
            eliminarHoja(nodo);
        } else {
            nodeSwap(nodo, obtenerUltimo());
            eliminarHoja(nodo);
        }
    }

    protected void eliminarHoja(Nodo<T> hoja){
        if(!hoja.isLeaf()) return;
        Nodo<T> padre = hoja.getPadre();
        if(padre == null) root = null;
        else if(hoja == padre.getHijoIzquierdo()) padre.setHijoIzquierdo(null);
        else padre.setHijoDerecho(null);
    }

    public static <T> void notPrefija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            lista.add(r.getClave());
            notPrefija(r.getHijoIzquierdo(), lista);
            notPrefija(r.getHijoDerecho(), lista);
        }   
    }

    public static <T> void notInfija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            notInfija(r.getHijoIzquierdo(), lista);
            lista.add(r.getClave());
            notInfija(r.getHijoDerecho(), lista);
        }  
    }

    public static <T> void notPostfija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            notPostfija(r.getHijoIzquierdo(), lista);
            notPostfija(r.getHijoDerecho(), lista);
            lista.add(r.getClave());
        }  
    } 
    /**
     * Objetivo necesariamente debe de ser una hoja
     * @param origen
     * @param objetivo
     * @throws Exception
     */
    public void nodeSwap(Nodo<T> origen, Nodo<T> objetivo) {
        Nodo<T> auxDerecho = origen.getHijoDerecho();
        Nodo<T> auxIzquierdo = origen.getHijoIzquierdo();
        Nodo<T> auxPadre = origen.getPadre();
        
        origen.setHijoDerecho(null);
        origen.setHijoIzquierdo(null);

        actualizarPadre(objetivo, origen);

        actualizarPadre(origen, objetivo);

        origen.setPadre(objetivo.getPadre());

        if(origen == root){
            setRoot(objetivo);
            objetivo.setPadre(null);
        } else {
            objetivo.setPadre(auxPadre);
        }

        if(objetivo == auxDerecho) {
            objetivo.setHijoDerecho(origen);
            objetivo.setHijoIzquierdo(auxIzquierdo);

        } else if(objetivo == auxIzquierdo) {
            objetivo.setHijoIzquierdo(origen);
            objetivo.setHijoDerecho(auxDerecho);
        } else {
            objetivo.setHijoDerecho(auxDerecho);
            objetivo.setHijoIzquierdo(auxIzquierdo);
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

    protected void actualizarPadre(Nodo<T> origen, Nodo<T> objetivo){
        Nodo<T> padre = origen.getPadre();

        if(padre == null || padre == objetivo) {
            return;
        }

        if(padre.getHijoDerecho() == origen){
            padre.setHijoDerecho(objetivo);
        } else {
            padre.setHijoIzquierdo(objetivo);
        }

    }

    public Nodo<T> obtenerUltimo(){
        Nodo<T> ultimo = null;
        Queue<Nodo<T>> nivel = new LinkedList<>();
        nivel.add(root);
        while(!nivel.isEmpty()) {
            ultimo = nivel.poll();
            if(ultimo.getHijoIzquierdo() != null){
                nivel.add(ultimo.getHijoIzquierdo());
            }
            if(ultimo.getHijoDerecho() != null){
                nivel.add(ultimo.getHijoDerecho());
            }
        }
        return ultimo;
    }


    public Nodo<T> search(T clave) throws Exception{
        if(root == null) return null;
        Nodo<T> actual = null;
	    Queue<Nodo<T>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            actual = queue.poll();
            
            if(actual.getClave().equals(clave)){
                return actual;
            }

            if(actual.getHijoIzquierdo() != null){
                queue.add(actual.getHijoIzquierdo());
            }

            if(actual.getHijoDerecho() != null){
                queue.add(actual.getHijoDerecho());
            }
        }

        throw new Exception("El nodo con dicho valor no existe");
    }
}
