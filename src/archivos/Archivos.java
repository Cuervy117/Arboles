package archivos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import AVL.*;
public class Archivos {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void leerBaseDeDatos(ArrayList<ArbolAVL<T>> database){
        ObjectInputStream archivo;
        ArbolAVL<T> arbol;
        try{
            archivo = new ObjectInputStream(new FileInputStream("DataBase"));
            while(true){
                arbol = (ArbolAVL<T>) archivo.readObject();
                System.out.println("Cargando árbol con raíz: " + (arbol.getRoot() != null ? arbol.getRoot().getClave() : "null"));
                database.add(arbol);
            }
        }catch(EOFException e){
            System.out.println("Base de datos actualizada");
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public static <T extends Comparable<T>> void guardarDatos(ArrayList<ArbolAVL<T>> database){
        ObjectOutputStream archivo;
        try{
            archivo = new ObjectOutputStream(new FileOutputStream("DataBase"));
            for(ArbolAVL<T> e : database){
                archivo.writeObject(e);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
