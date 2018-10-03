package modelo_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.Mensajes;

public class Conexion {

	public static Connection conexion;
		
	private Conexion(){}
		
	public static Connection getConexion(){
			
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.0.100:3306/halcones_diarios_a?autoReconnect=true&useSSL=false"
			//conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/halcones_diarios_a?autoReconnect=true&useSSL=false"
					
			//conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/halcones_diarios?autoReconnect=true&useSSL=false"
			
			, "root", "");
		} catch (Exception e) {
				// TODO Auto-generated catch block
			Mensajes.getMensaje_conexionError();
			e.printStackTrace();
			return null;
		}
			
		return conexion;
	}
	
	public Connection getSQLConnection(){
		
		return conexion;
	}
		
	public void cerrar_conexion(){
		
		if(conexion!=null){
			
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
