package Controlador;

import java.util.List;

import Modelo.Empleado;

public interface EmpleadoDao {
	public void crear(Empleado e);
	public void actualizar(Empleado e);
	public void eliminar(Empleado e);
	public Empleado buscarPorId(Integer id);
	public List<Empleado> buscarTodos();
}
