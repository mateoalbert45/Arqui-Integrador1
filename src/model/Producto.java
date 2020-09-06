package model;
//Entidad Producto con sus respectivos métodos y atributos


public class Producto {
	private int id_producto;
	private String nombre;
	private int valor;



	public Producto(int id_producto, String nombre, int valor) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.valor = valor;
	}



	public int getId_producto() {
		return id_producto;
	}



	public String getNombre() {
		return nombre;
	}



	public int getValor() {
		return valor;
	}


	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", valor=" + valor + "]";
	}

	
}
