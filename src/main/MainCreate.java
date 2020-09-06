package main;

import java.sql.SQLException;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import conexion.Conexion;
import crearEsquemas.creacionEsquemas;

public class MainCreate {

	public static void main(String[] args) throws SQLException {
		Conexion con = new Conexion("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost/Arqui?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		con.inicializar();
		creacionEsquemas carga = new creacionEsquemas(con);
		carga.crearTablas();
		ClienteDAO cliente = new ClienteDAO(con);
		cliente.insertCSV("src/clientes.csv");
		FacturaDAO factura = new FacturaDAO(con);
     	factura.insertCSV("src/facturas.csv");
		ProductoDAO producto = new ProductoDAO(con);
		producto.insertCSV("src/productos.csv");
		Factura_ProductoDAO factura_producto = new Factura_ProductoDAO(con);
		factura_producto.insertCSV("src/facturas-productos.csv");
	}

}
