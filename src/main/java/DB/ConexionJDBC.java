package DB;

import java.sql.*;


public class ConexionJDBC {
	
	private static final String USUARIO = "root";
	private static final String PASSWORD = "1234";
	private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/app-rrhh?autoReconnect=true&useSSL=false";
	
	private static Connection _CONEXION;
	
	public static Connection getConexion() {
		 if (_CONEXION == null) {
			 try {
				_CONEXION = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return _CONEXION;
	}
	
	public static void liberarConexion() {
		 if (_CONEXION != null) {
			 try {
				_CONEXION.close();
				_CONEXION = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
}
