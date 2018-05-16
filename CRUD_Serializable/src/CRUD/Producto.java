package CRUD;
import java.util.*;
import java.io.*;
public class Producto implements Serializable
{
    // instance variables - replace the example below with your own
    int codigo;    // Código del producto, se utiliza para buscar
    String nombre; // Nombre un texto
    int stock;    // existencia actuales
    int stock_min; // Número mínimo de existencias recomedadas
    float precio;  // Precio

    /**
     * Constructor for objects of class Producto
     */
    
    public Producto (int codigo, String nombre,int stock,int stock_min,float precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.stock_min = stock_min;
        this.precio = precio;
    }
    
    public Producto() {
    	this.codigo = 0;
    	this.nombre = null;
    	this.stock = 0;
    	this.stock_min = 0;
    	this.precio = 0;
    	
    }

    
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCodigo (){
        return codigo;
    }
    
    @Override
    public String toString(){
       return  nombre +" con el código "+ codigo +" tiene un stock actual de "+ stock +" y un coste de "+precio;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock(int valor){
        stock = valor;
    }
    
    public int getStock_min(){
        return stock_min;
    }
    
    public void setStock_min(int valor){
        stock_min = valor;
    }
    
    
    public float getPrecio(){
        return precio;
    }
    
    public void setPrecio(float valor){
        precio = valor;
    }
}