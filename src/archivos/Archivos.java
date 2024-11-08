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
public static void leerBaseDeDatos(ArrayList<ArbolAVL> database){
        ObjectInputStream archivo;
        ArbolAVL arbol;
        try{
            archivo = new ObjectInputStream(new FileInputStream("DataBase"));
            do{
                arbol = (ArbolAVL) archivo.readObject();
                database.add(arbol);
            }while(arbol != null);
        }catch(EOFException e){
            System.out.println("Base de datos actualizada");
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public static void guardarDatos(ArrayList<ArbolAVL> database){
        ObjectOutputStream archivo;
        try{
            archivo = new ObjectOutputStream(new FileOutputStream("DataBase"));
            for(ArbolAVL e : database){
                archivo.writeObject(e);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
