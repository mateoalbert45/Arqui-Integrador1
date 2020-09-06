package conexion;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String driver;
	private String uri;
	private Connection con;
	private String user;
	private String pass;
	
	public Conexion(String driver, String uri, String user, String pass) {
		super();
		this.driver = driver;
		this.uri = uri;
		this.con = null;
		this.user = user;
		this.pass = pass;
	}



	public void inicializar() { //Se inicializa la conexión una sola vez en todo el proceso
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Connection open() { //Se llama a este método cada vez que se necesita abrir la conexión 
		try {
			this.con = DriverManager.getConnection(uri, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.con;
	}
	
	
	public Connection getCon() {
		return this.con;
	}



	public void close() { //Y con éste método la cerramos.
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	


}
