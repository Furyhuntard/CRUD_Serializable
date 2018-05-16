package CRUD;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class ModeloArrayListFile extends ModeloArrayList
{
    static final String nombrefichero = "productos.csv";
    
    public ModeloArrayListFile()
    {
       super();
       leerFicheroObjetos();
       
    }
    
    private void leerFicheroObjetos(){
    	try
    	{
        	FileInputStream fis = new FileInputStream("productos.objetos");
        	ObjectInputStream ois = new ObjectInputStream(fis);
        	Producto aux = (Producto) ois.readObject();
        	while (true)
        	{
        		lista.add(aux);
        		aux = (Producto) ois.readObject();
        	}

    	}
    	catch (IOException e)
    	{
    		System.out.println("Error en E/S sobre fichero");
    	}
    	catch (ClassNotFoundException cnfe)
    	{
    		System.out.println("No se encuentra el item");
    	}
   
    }
    
  
    
    public void guardarEnObjetos() {
    	
    	try 
    	{	
        	FileOutputStream fos=new FileOutputStream("productos.objetos");
        	ObjectOutputStream oos=new ObjectOutputStream(fos);
        	
        	for(Producto p: lista) 
        	{
        		oos.writeObject(p);
        	}
        	fos.close();
        	oos.close();
        }
        catch(IOException ioe) 
    	{
        	
        	System.err.println("Error en E/S sobre fichero");
        }
    }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      if ( resu ){
          guardarEnObjetos();
      }
      return resu;
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if ( resu ){
          guardarEnObjetos();
        }
      return resu;
    }
    
    
    public boolean modificarProducto (Producto nuevo){
       boolean resu = super.modificarProducto(nuevo);
       if ( resu ){
           guardarEnObjetos();
        }
       return (resu);
    }
    
        
}    