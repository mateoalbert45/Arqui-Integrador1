package DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import conexion.Conexion;
import model.Producto;

public class ProductoDAO {
	private Conexion conexion; //Usamos un objeto Conexion para inicializarla y poder abrir y cerrar la conexión cuando sea necesario.
	
	public ProductoDAO(Conexion con) {
		super();
		this.conexion = con;
	}
	
	public void insertCSV(String archivo) throws SQLException { // Con este método leemos el archivo CSV, obtenemos los datos de los productos y los subimos a la base de datos.
		String insert = "INSERT INTO producto (id_producto, nombre, valor) VALUES(?,?,?)";
		Connection con = conexion.open();
		PreparedStatement ps = con.prepareStatement(insert);
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(archivo));
			for (CSVRecord row : parser) {
				ps.setInt(1, Integer.parseInt(row.get("idProducto")));
				ps.setString(2, row.get("nombre"));
				ps.setFloat(3, Float.parseFloat(row.get("valor")));
				ps.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ps.close();
		con.close();

	}
	
	public Producto getBestProduct() { //Con este método obtenemos una lista de los productos que más se vendieron, siendo ordenados de mayor a menor por la cantidad facturada. 
		try {
			Connection con = conexion.open();
			PreparedStatement ps;
			ResultSet rs;
			String select = "SELECT fp.id_producto,p.nombre, sum(fp.cantidad * p.valor) AS recaudacion FROM factura_producto fp JOIN producto p ON (fp.id_producto = p.id_producto) GROUP BY fp.id_producto, p.nombre ORDER BY recaudacion DESC";
			ps = con.prepareStatement(select);
			rs = ps.executeQuery();
			rs.next();
			System.out.println("-----------------PRODUCTO MAS RECAUDADOR----------------");
			Producto producto = new Producto(rs.getInt(1),rs.getString(2),rs.getInt(3));
			System.out.println(producto.toString());
			con.close();
			return producto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
