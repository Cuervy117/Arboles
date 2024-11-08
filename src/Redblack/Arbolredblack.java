package Redblack;

public class Arbolredblack extends ArbolBinarioBusqueda{
    public Arbolredblack(){
        super();
    }
    public Arbolredblack(Nodo raiz){
        super(raiz);
    }

    public void insertar(Nodo añadido){
        //Recordando que true = rojo, false = negro
        super.añadir(añadido);
        Nodo auxiliar = añadido;
        while(Nodo.color(auxiliar.getPadre()) == true ){
            this.inser
            int caso = IdentificarCaso(añadido);
            switch (caso) {
                case 1 -> {
                    System.out.println("Caso 1 detectado");
                    auxiliar = corregirCaso1(añadido);
                }
                case 2 -> {
                    System.out.println("Caso 2 detectado");
                    auxiliar = corregirCaso2(añadido);
                }
                case 3 -> {
                    System.out.println("Caso 3 detectado");
                    auxiliar = corregirCaso3(añadido);
                }
                default -> System.out.println("Hubo error");
            }
        }
    }

    private int IdentificarCaso(Nodo añadido){
        Nodo tio = Nodo.getTio(añadido);
        if(tio.esRojo() && !añadido.getPadre().esRojo()){  
            return 1;
        }else{
            if(añadido.getPadre().esRojo() && !añadido.esHijoIzquierdo()){
                return 2;
            }else{
                return 3;
            }
        }

    }

    private Nodo corregirCaso1(Nodo añadido){
        Nodo padre = añadido.getPadre();
        padre.getDerecha().setColor(false);
        padre.getIzquierda().setColor(false);
        return añadido;
    }

    private Nodo corregirCaso2(Nodo añadido){
        Nodo apuntador = añadido.getPadre();
        añadido.setPadre(apuntador.getPadre());
        añadido.setIzquierda(apuntador);
        apuntador.setPadre(añadido);
        return apuntador;
    }

    private Nodo corregirCaso3(Nodo añadido){
        //Cambio de colores
        añadido.getPadre().getPadre().setColor(true);
        añadido.getPadre().setColor(false);
        //Rotando a la derecha
        Nodo abuelo = añadido.getPadre().getPadre();
        Nodo huerfano = añadido.getPadre().getDerecha();
        huerfano.setPadre(abuelo);
        abuelo.setIzquierda(huerfano);

        abuelo.setPadre(añadido.getPadre());
        abuelo.getPadre().setDerecha(abuelo);
        return añadido;
    }

}
