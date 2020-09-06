package DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import conexion.Conexion;

public class FacturaDAO {
	private Conexion conexion; //Usamos un objeto Conexion para inicializarla y poder abrir y cerrar la conexión cuando sea necesario.
	
	

	public FacturaDAO(Conexion con) {
		super();
		this.conexion = con;
	}



	public void insertCSV(String archivo) throws SQLException { // Con este método leemos el archivo CSV, obtenemos los datos de las facturas y las subimos a la base de datos.
		String insert = "INSERT INTO factura (id_factura, id_cliente) VALUES(?,?)";
		Connection con = conexion.open();
		PreparedStatement ps = con.prepareStatement(insert);
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(archivo));
			for (CSVRecord row : parser) {
				ps.setInt(1, Integer.parseInt(row.get("idFactura")));
				ps.setInt(2, Integer.parseInt(row.get("idCliente")));
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
	
}
