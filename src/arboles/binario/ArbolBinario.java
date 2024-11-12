package arboles.binario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Clase de un árbol binario.
 * @param <T> el tipo de dato requerido para el árbol
 */
public class ArbolBinario<T> implements Serializable{
    protected Nodo<T> root;
    
    /**
     * Constructor vacío.
     */
    public ArbolBinario(){

    }
    
    /**
     * Constructor que inicializa con una clave en la raíz.
     * @param clave el valor de la raíz
     */
    public ArbolBinario(T clave){
        root = new Nodo<T>(clave);
    }
    
    /**
     * Constructor que inicializa con un nodo raíz.
     * @param root el nodo raíz del árbol
     */
    public ArbolBinario(Nodo<T> root){
        this.root = root;
    }

    /**
     * Obtiene la raíz del árbol.
     * @return el nodo raíz
     */
    public Nodo<T> getRoot(){
        return root;
    }
    
    /**
     * Establece la raíz del árbol.
     * @param root el nodo que se define como raíz
     */
    public void setRoot(Nodo<T> root){
        this.root = root;
    }
    
    /**
     * Agrega un nodo al árbol.
     * @param nodo el nodo agregado
     */
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

    /**
     * Elimina un nodo del árbol con una clave específica.
     * @param clave el valor del nodo eliminado
     * @throws Exception si el nodo no existe en el árbol
     */
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
    
    /**
     * Elimina un nodo hoja indicado del árbol.
     * @param hoja el nodo hoja eliminado
     */
    protected void eliminarHoja(Nodo<T> hoja){
        if(!hoja.isLeaf()) return;
        Nodo<T> padre = hoja.getPadre();
        if(padre == null) root = null;
        else if(hoja == padre.getHijoIzquierdo()) padre.setHijoIzquierdo(null);
        else padre.setHijoDerecho(null);
    }

    /**
     * Realiza el recorrido preorden y guarda las claves en una lista.
     * @param r el nodo incial del recorrido
     * @param lista la lista donde se guardan las claves
     */
    public static <T> void notPrefija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            lista.add(r.getClave());
            notPrefija(r.getHijoIzquierdo(), lista);
            notPrefija(r.getHijoDerecho(), lista);
        }   
    }

    /**
     * Realiza el recorrido inorden y guarda las claves en una lista.
     * @param r el nodo incial del recorrido
     * @param lista la lista donde se guardan las claves
     */
    public static <T> void notInfija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            notInfija(r.getHijoIzquierdo(), lista);
            lista.add(r.getClave());
            notInfija(r.getHijoDerecho(), lista);
        }  
    }

    /**
     * Realiza el recorrido postorden y guarda las claves en una lista.
     * @param r el nodo incial del recorrido
     * @param lista la lista donde se guardan las claves
     */
    public static <T> void notPostfija(Nodo<T> r, ArrayList<T> lista) {
        if (r != null) {
            notPostfija(r.getHijoIzquierdo(), lista);
            notPostfija(r.getHijoDerecho(), lista);
            lista.add(r.getClave());
        }  
    } 
    /**
     * Intercambia la posición de dos nodos.
     * @param origen el nodo a intercambiar
     * @param objetivo el nodo hoja que reemplazará al origen
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

    /**
     * Actualiza el padre de un nodo en el árbol.
     * @param origen el nodo original
     * @param objetivo el nodo que reemplazará al origen
     */
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

    /**
     * Obtiene el último nodo agregado al árbol en un recorrido por niveles.
     * @return el último nodo encontrado
     */
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


    /**
     * Busca un nodo en el árbol que contenga la clave indicada.
     * @param clave el valor del nodo a buscar
     * @return el nodo que contiene la clave
     * @throws Exception si el nodo no existe en el árbol
     */
    public Nodo<T> search(T clave) throws Exception{
        if(root == null) throw new Exception("El nodo con dicho valor no existe");
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
