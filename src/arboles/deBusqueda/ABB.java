package arboles.deBusqueda;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;

public class ABB<T extends Comparable<T>> extends ArbolBinario<T> {

    public ABB(){
        super();
    }
    
    public ABB(T clave){
        super(clave);
    }
    
    public ABB(Nodo<T> root){
        super(root);
    }

    @Override
    public void add(T clave){
        Nodo<T> nodo = new Nodo<>(clave);
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

    @Override
    public void delete(T clave){
        Nodo<T> nodo;
        nodo = search(clave);
        if(nodo == null){
            System.out.println("La clave no se encuentra");
            return;
        }
        if(nodo.isLeaf()){ //Caso de nodo Hoja
            eliminarHoja(nodo);
        }else{
            Nodo<T> reemplazo = nodo.getNodoSucesor();
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

    @Override
    public Nodo<T> search(T clave){
        Nodo<T> auxRoot = this.root;
        while (auxRoot != null) {
            int comparacion = auxRoot.getClave().compareTo(clave);
            System.out.println("comparando " + auxRoot.getClave() + " con " + clave + " comparacion = " + comparacion);
            if(comparacion == 0) return auxRoot;
            else if(comparacion > 0) auxRoot = auxRoot.getHijoIzquierdo();
            else auxRoot = auxRoot.getHijoDerecho();
            System.out.println(auxRoot.getClave());
        }
        return null;
    }

}
