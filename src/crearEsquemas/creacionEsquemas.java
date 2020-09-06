package crearEsquemas;

import java.sql.Connection;
import java.sql.SQLException;

import conexion.Conexion;

public class creacionEsquemas {
	private Conexion conexion;
	
	
	public creacionEsquemas(Conexion con) {
		super();
		this.conexion = con;
	}
	
	
	public void crearTablas() throws SQLException { //Con este método realizamos la creación de las tablas SQl
		Connection con = conexion.open();
		String table = "CREATE TABLE cliente(" + "id_cliente INT," + "nombre VARCHAR(500)," + "email VARCHAR(150),"
				+ "PRIMARY KEY(id_cliente))";
		try {
			con.prepareStatement(table).execute();
			con.commit();
		} catch (SQLException e) {
		}
		table = "CREATE TABLE  factura(" + "id_factura INT," + " id_cliente INT NOT NULL,"
				+ " PRIMARY KEY(id_factura), " + " FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente))";
		try {
			con.prepareStatement(table).execute();
			con.commit();
		} catch (SQLException e) {
		}
		table = "CREATE TABLE producto(id_producto int NOT NULL," + " nombre varchar(45) NOT NULL,"
				+ " valor double NOT NULL," + " PRIMARY KEY(id_producto))";
		try {
			con.prepareStatement(table).execute();
			con.commit();
		} catch (SQLException e) {
		}
		table = "CREATE TABLE factura_producto(cantidad int NOT NULL," + " id_producto int NOT NULL,"
				+ " id_factura int NOT NULL," + " FOREIGN KEY(id_producto) REFERENCES producto(id_producto),"
				+ " FOREIGN KEY(id_factura) REFERENCES factura(id_factura))";
		try {
			con.prepareStatement(table).execute();
			con.commit();
		} catch (SQLException e) {
		
	}
		con.close();

	}
}
