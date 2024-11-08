package Redblack;

public class Nodo {
    private int dato;
    private Nodo izquierda, derecha, padre;
    private boolean rojo; // true = rojo, false = negro

    public Nodo(int dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
        this.padre = null;
        this.rojo = true; 
    }

    boolean esRojo(){
        return rojo;
    }

    static boolean color(Nodo nodo){
        if(nodo == null){
            return false; // Ya que el false representa el color negro y todos los nodos NULL son negros
        }
        return nodo.esRojo(); // Detecta finalmente el color 
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public boolean esHijoIzquierdo(){
        if(this.padre.izquierda == this){
            return true;
        }else{
            return false;
        }
    }
    public int getDato(){
        return dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public void setColor(boolean color) {
        this.rojo = color;
    }

    public static Nodo getTio(Nodo nodo){
        Nodo tio;
        if (nodo.getPadre().esHijoIzquierdo()) {
            tio = nodo.getPadre().getPadre().getDerecha();
        }else{
           tio = nodo.getPadre().getPadre().getIzquierda();
        }
        return tio;
    }
}
