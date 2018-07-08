package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.ConexionJDBC;
import Modelo.*;

public class EmpleadoDaoJdbc implements EmpleadoDao {

	private final String INSERT_EMPLEADO = "INSERT INTO EMPLEADOS "
			+ "(NOMBRE, CORREO, CUIL, "
			+ "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
			+ "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private final String UPDATE_EMPLEADO = "UPDATE EMPLEADOS SET "
			+ "NOMBRE = ?, CORREO = ?, CUIL = ?, "
			+ "FECHA_INGRESO = ?, HS_TRABAJADAS = ?, SUELDO_BASICO = ?, "
			+ "COMISIONES = ?, HS_MINIMAS = ?, COSTO_HORA = ?, TIPO_EMPLEADO = ? "
			+ "WHERE ID = ?";
	
	private final String DELETE_EMPLEADO = "DELETE FROM EMPLEADOS WHERE ID = ?";
	
	private final String SEARCH_EMPLEADO_ALL = "SELECT * FROM EMPLEADOS";
	
	private final String SEARCH_EMPLEADO_BY_ID = SEARCH_EMPLEADO_ALL+" WHERE id=?";
	
	
	
	public void crear(Empleado e) {
		Connection conn = ConexionJDBC.getConexion();
		try(PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO)){
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getCorreoElectronico());
			ps.setString(3, e.getCuil());
			ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
			ps.setInt(5, e.getHorasTrabajados());
			if(e.esEfectivo()){
				Efectivo empEf = (Efectivo) e;
				ps.setDouble(6, empEf.getSueldoBasico());
				ps.setDouble(7, empEf.getComisiones());
				ps.setInt(8, empEf.getHorasMinimasObligatorias());
				ps.setDouble(9, 0);
				ps.setInt(10, 1);
			}
			if(e.esContratado()){
				Contratado c = (Contratado) e;
				ps.setDouble(6, 0);
				ps.setDouble(7, 0);
				ps.setInt(8, 0);
				ps.setDouble(9, c.getMontoPorHoras());
				ps.setInt(10, 2);
			}
			ps.executeUpdate();
			System.out.println("Empleado creado");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		ConexionJDBC.liberarConexion();
	}

	public void actualizar(Empleado e) {
		Connection conn = ConexionJDBC.getConexion();
		
		try(PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLEADO)){
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getCorreoElectronico());
			ps.setString(3, e.getCuil());
			ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
			ps.setInt(5, e.getHorasTrabajados());
			if(e.esEfectivo()){
				Efectivo empEf = (Efectivo) e;
				ps.setDouble(6, empEf.getSueldoBasico());
				ps.setDouble(7, empEf.getComisiones());
				ps.setInt(8, empEf.getHorasMinimasObligatorias());
				ps.setDouble(9, 0);
				ps.setInt(10, 1);
			}
			if(e.esContratado()){
				Contratado c = (Contratado) e;
				ps.setDouble(6, 0);
				ps.setDouble(7, 0);
				ps.setInt(8, 0);
				ps.setDouble(9, c.getMontoPorHoras());
				ps.setInt(10, 2);
			}
			ps.setInt(11, e.getId());
			ps.executeUpdate();
			System.out.println("Empleado editado");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		
		ConexionJDBC.liberarConexion();
	}

	public void eliminar(Empleado e) {
		Connection conn = ConexionJDBC.getConexion();
		
		try(PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO)){
			ps.setInt(1, e.getId());
			ps.executeUpdate();
			System.out.println("Empleado eliminado");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		ConexionJDBC.liberarConexion();
	}

	public Empleado buscarPorId(Integer id) {
		Empleado e_search = null;
		Connection conn = ConexionJDBC.getConexion();
		try(PreparedStatement ps = conn.prepareStatement(SEARCH_EMPLEADO_BY_ID)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				if(rs.getInt(11)==1) { //EFECTIVO
					e_search = new Efectivo(rs.getInt(1), //ID 
												rs.getString(2), //NOMBRE
												rs.getString(3), //CORREO
												rs.getString(4), //CUIL
												rs.getDate(5), //FECHA_INGRESO
												rs.getInt(6), //HS_TRABAJADAS
												rs.getDouble(7), //SUELDO_BASICO
												rs.getDouble(8), //COMISIONES
												rs.getInt(9)); //HS_MINIMAS
				}
				else { //CONTRATADO
					e_search = new Contratado(rs.getInt(1), //ID 
										rs.getString(2), //NOMBRE
										rs.getString(3), //CORREO
										rs.getString(4), //CUIL
										rs.getDate(5), //FECHA_INGRESO
										rs.getInt(6), //HS_TRABAJADAS
										rs.getDouble(10)); //COSTO_HORA
				}
			
			System.out.println("Empleado encontrado");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		ConexionJDBC.liberarConexion();
		return e_search;
	}

	public List<Empleado> buscarTodos() {
		Connection conn = ConexionJDBC.getConexion();
		List<Empleado> lista = null;
		try(PreparedStatement ps = conn.prepareStatement(SEARCH_EMPLEADO_ALL)){
			lista = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Empleado e = null;
				if(rs.getInt(11)==1) { //EFECTIVO
					e = new Efectivo(rs.getInt(1), //ID 
												rs.getString(2), //NOMBRE
												rs.getString(3), //CORREO
												rs.getString(4), //CUIL
												rs.getDate(5), //FECHA_INGRESO
												rs.getInt(6), //HS_TRABAJADAS
												rs.getDouble(7), //SUELDO_BASICO
												rs.getDouble(8), //COMISIONES
												rs.getInt(9)); //HS_MINIMAS
				}
				else { //CONTRATADO
					e = new Contratado(rs.getInt(1), //ID 
										rs.getString(2), //NOMBRE
										rs.getString(3), //CORREO
										rs.getString(4), //CUIL
										rs.getDate(5), //FECHA_INGRESO
										rs.getInt(6), //HS_TRABAJADAS
										rs.getDouble(10)); //COSTO_HORA
				}
				lista.add(e);
			}
			System.out.println("Empleados listados");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		ConexionJDBC.liberarConexion();
		return lista;
	}

}
