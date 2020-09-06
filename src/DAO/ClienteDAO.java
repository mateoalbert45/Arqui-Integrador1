package DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import conexion.Conexion;
import model.Cliente;

public class ClienteDAO {
	private Conexion conexion; //Usamos un objeto Conexion para inicializarla y poder abrir y cerrar la conexión cuando sea necesario.

	public ClienteDAO(Conexion con) {
		super();
		this.conexion = con;
	}
	
	public void insertCSV(String archivo) throws SQLException { // Con este método leemos el archivo CSV, obtenemos los datos de los clientes y los subimos a la base de datos.
		String insert = "INSERT INTO cliente (id_cliente, nombre, email) VALUES(?,?,?)";
		Connection con = conexion.open();
		PreparedStatement ps = con.prepareStatement(insert);
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(archivo));
			for (CSVRecord row : parser) {
				ps.setInt(1, Integer.parseInt(row.get("idCliente")));
				ps.setString(2, row.get("nombre"));
				ps.setString(3, row.get("email"));
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
	
	
	public List<Cliente> getBestClientes() { //Con este método retornamos una lista de los clientes a los que más se le facturó
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String select = "SELECT * FROM cliente";
			Connection con = conexion.open();
			PreparedStatement ps;
			ResultSet rs;
			select = "SELECT c.id_cliente, c.nombre, sum(fp.cantidad * p.valor) AS recaudacion, count(c.id_cliente) FROM factura_producto fp JOIN producto p ON (fp.id_producto = p.id_producto) JOIN factura f ON (fp.id_factura = f.id_factura) "
					+ "JOIN cliente c ON (f.id_cliente = c.id_cliente) GROUP BY c.id_cliente, c.nombre ORDER BY recaudacion DESC";
			ps = con.prepareStatement(select);
			rs = ps.executeQuery();
			System.out.println("-----------------LISTA DE FACTURACION POR CLIENTE----------------");
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
				System.out.println(cliente.toString());
				clientes.add(cliente);
			}
			con.close();
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
