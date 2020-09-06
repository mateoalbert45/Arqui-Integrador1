package main;

import java.sql.SQLException;

import DAO.ClienteDAO;
import DAO.ProductoDAO;
import conexion.Conexion;

public class Main {

	public static void main(String[] args) throws SQLException {
		Conexion con = new Conexion("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/Arqui?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		con.inicializar();
		ClienteDAO cliente = new ClienteDAO(con);
		ProductoDAO producto = new ProductoDAO(con);
		producto.getBestProduct();
		cliente.getBestClientes();
		con.close();
	}

}
