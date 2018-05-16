package CRUD;
import java.util.ArrayList;
public class ModeloArrayList extends ModeloAbs
{
     ArrayList <Producto> lista;
    
    public ModeloArrayList()
    {
       lista=new ArrayList <Producto>();
    }

    
    public boolean insertarProducto (Producto p){
       boolean insertado = true;
       lista.add(p);
       return insertado;
    }
 
    
    public boolean borrarProducto ( int codigo ){
      Producto productoABorrar = buscarProducto(codigo);
      if (productoABorrar != null)
      {
    	  return lista.remove(productoABorrar);
      }
      return false;
    }
    
    
    public Producto buscarProducto ( int codigo) {
    	for (int i = 0 ; i < lista.size() ; i++)
        {
      	  if(lista.get(i).codigo==codigo)
      	  {
      		  return lista.get(i);
      	  }
        }
        return null;
    }
    
    
    public void listarProductos (){
    	for (int i = 0;i<lista.size();i++)
    	{
    		System.out.println(lista.get(i));
    	}
        
    }
    
    public void listarPocoStock() {
    	for (int i = 0; i<lista.size() ; i++)
    	{
    		if(lista.get(i).getStock()<lista.get(i).getStock_min())
    		{
    			System.out.println(lista.get(i));
    		}
    	}
    }
    
    
    public boolean modificarProducto (Producto nuevo){
       return false;
       
    }
   
}  