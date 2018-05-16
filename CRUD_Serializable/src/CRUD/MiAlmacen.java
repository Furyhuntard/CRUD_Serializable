package CRUD;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

// Crear la clase Producto y completar los métodos

public class MiAlmacen
{
    static private ModeloAbs almacen;
    static Scanner sc;
    
    public static void main(String[] args){
        almacen=new ModeloArrayListFile();
        sc = new Scanner(System.in);
        int opcion=0;
        do{
        	
		mostrarMenu();
                opcion=leerOpcion(1,9);
                switch(opcion){
                    case 1: crear();break;
                    case 2: consultar();break;
                    case 3: borrar();break;
                    case 4: modificarPrecio();break;
                    case 5: comprar();break;
                    case 6: vender();break;
                    case 7: listar();break;
                    case 8: listarPocoStock();break;
                }
                System.out.println("\n---------------------------- ");
                System.out.print("Pulse enter para continuar");
                sc.nextLine();
        }while(opcion!=9);
        sc.close();
        
    }
    
    
    private static void mostrarMenu(){
        System.out.println("\n\n    MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al mínimo");
        System.out.println("9. Terminar ");
        System.out.print("Elige una opción (1-9)");        
    }
    
    // Lee un entero del System.in que este comprendido entre primero y ultimo
    private static int leerOpcion(int primero, int ultimo){
        int valor = leerEntero();
        while ( valor <primero || valor > ultimo){
            valor = leerEntero();
        }
        return valor;
    }
      
    
    // Metodos Auxiliares leerFloat y LeerEntero, 
    // Lee de la System.in con el scanner sc y controlan la excepcion de NumberFormatException
    static private float leerFloat(){
        
        boolean error = false;
        float valor =0;
        String cadena;
        do 
        {
        error = false;  
          try {
             // Intento leer directamente un entero  
             cadena = sc.nextLine();
             valor = Float.parseFloat(cadena);
             
            } catch(NumberFormatException e){
              System.out.println("Error en formato.");
              error = true;
            }
        }
       while ( error);
       return valor;
    }
    
    
    static private int leerEntero(){
    	boolean error = false;
        int valor =0;
        String cadena;
        do 
        {
        error = false;  
          try 
          {
             cadena = sc.nextLine();
             valor = Integer.parseInt(cadena); 
          } 
          catch(NumberFormatException e)
          {
              System.out.println("Error en formato.");
              error = true;
          }
        }
       while (error);
       return valor;
    }

    
    private static void consultar(){        
       System.out.println("<CONSULTA>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if ( p == null){
    	   System.out.println("No hay ningún producto asociado a el código introducido");
        }
       else {
           System.out.println("PRODUCTO "+p);
        }
       
    }
    
    
    private static void borrar(){       
      System.out.println("<ELIMINAR>");
      System.out.println("Introduzca código del producto a eliminar del almacén");
      int codigo = leerEntero();
      if (almacen.buscarProducto(codigo)!=null)
      {
    	  System.out.println("Esta seguro de que quiere borrar el producto?");
          String respuesta = sc.nextLine();
          if(respuesta.equals("Si"))
        	{
        	almacen.borrarProducto(codigo);
        	System.out.println("Producto eliminado");
        	}
      }
      else
      {
    	  System.out.println("No hay ningún producto asociado a el código introducido");
      }
   }
    
 
    private static void modificarPrecio (){
       System.out.println("<MODIFICAR PRECIO>");
       System.out.println("Inserte el codigo del producto que quiera modificar su precio");
       int codigo = leerEntero();
       Producto b = almacen.buscarProducto(codigo);
       if (b!=null)
       {
    	    System.out.println("Inserte el nuevo precio que quiere para su producto");
            float precio = leerFloat();
    	    if (precio>0)
    	    {
    	    	b.setPrecio(precio);
    	    	System.out.println("Precio modificado");
    	    }
    	    else
    	    {
    	    	System.out.println("El precio debe ser mayor de 0");
    	    }
      
       }
        else
       {
        	System.out.println("No se encuentra un producto asociado a el código introducido");
       }
    }

    private static void comprar(){     
       System.out.println("<COMPRAR>");
       System.out.println("Inserte el codigo del producto que quiera comprar");
       int codigo = leerEntero();
       System.out.println("Inserte la cantidad de stock que desea aumentar");
       int cantidad = leerEntero();
       Producto b = almacen.buscarProducto(codigo);
       if (b!=null)
       {
        	b.setStock(b.getStock() + cantidad);
        	System.out.println("Producto comprado satisfactoriamente");
       }
        else
       {
        	System.out.println("No se encuentra un producto asociado a el código introducido");
       }
       
    }
    

    private static void vender(){
        System.out.println("<VENDER>");
        System.out.println("Inserte el codigo del producto que quiera vender");
        int codigo = leerEntero();
        System.out.println("Inserte la cantidad de stock que desea decrementar");
        int cantidad = leerEntero();
        if (almacen.buscarProducto(codigo)!=null)
        {
         	Producto b = almacen.buscarProducto(codigo);
         	int stockActual = b.getStock();
         	b.setStock(b.getStock() - cantidad);
         	if (b.getStock()<0)
         	{
         		System.out.println("No existen tantas existencias en el almacen");
         		b.setStock(stockActual);
         	}
         	else
         	{
         		System.out.println("Producto vendido satisfactoriamente");
         	}
        }
         else
        {
         	System.out.println("No se encuentra un producto asociado a el código introducido");
        }    
    }
    

    private static void listar(){        
         System.out.println("<LISTAR>");
         almacen.listarProductos();
    }
    

    private static void listarPocoStock(){
        System.out.println("<LISTAR STOCK BAJO MINIMOS>");
        almacen.listarPocoStock();
    }
    
    // Solicita datos al usuario para dar de alta un nuevo producto 
    // El codigo no se puede repetir
    private static void crear(){
       System.out.println("<NUEVO PRODUCTO>");
       System.out.println("Introduce el código");
       int codigo = leerEntero();
       System.out.println("Introduce el nombre");
       String nombre = sc.nextLine();
       if(almacen.buscarProducto(codigo)==null)
       {
    	   System.out.println("Introduce stock actual para la" + nombre);
    	   int stockActual = leerEntero();  
    	   System.out.println("Introduce stock minimo para la" + nombre);
    	   int stockMinimo = leerEntero();
    	   System.out.println("Introduce precio para la" + nombre);
    	   float precio = leerFloat();
    	   Producto nuevo = new Producto(codigo,nombre,stockActual,stockMinimo,precio);
    	   almacen.insertarProducto(nuevo);
       }
    }    
}