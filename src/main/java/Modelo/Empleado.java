package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Empleado {
	private Integer id;
	private String nombre;
	private String correoElectronico;
	private String cuil;
	private Date fechaIngreso;
	private Integer horasTrabajados;
	
	public Empleado() {}
	
	public Empleado(Integer id, String nombre, String correo, String cuil, Date fecha, Integer horas) {
		this.id = id;
		this.nombre = nombre;
		this.correoElectronico = correo;
		this.cuil = cuil;
		this.fechaIngreso = fecha;
		this.horasTrabajados = horas;
	}
	
	public abstract Double salario();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Integer getHorasTrabajados() {
		return horasTrabajados;
	}
	public void setHorasTrabajados(Integer horasTrabajados) {
		this.horasTrabajados = horasTrabajados;
	}

	public abstract boolean esContratado();

	public abstract boolean esEfectivo();

	@Override
	public boolean equals(Object obj) {
		Empleado e = (Empleado) obj;
		return this.id == e.id
				&& this.nombre.equals(e.nombre)
				&& this.correoElectronico.equals(e.correoElectronico)
				&& this.cuil.equals(e.cuil)
				&& this.fechaIngreso.equals(e.fechaIngreso)
				&& this.horasTrabajados.equals(horasTrabajados);
	}
	
	private String tomarFechaEnFormato() {
		return new SimpleDateFormat("dd/MM/yyyy").format(fechaIngreso);
	}

	@Override
	public String toString() {
		return String.format("EMPLEADO { id, nombre, correoElectronico, cuil, fechaIngreso, horasTrabajadas } = { %d ,%s ,%s ,%s ,%s ,%d }",this.id,this.nombre,this.correoElectronico,this.cuil,this.tomarFechaEnFormato(),this.horasTrabajados);
	}
	
	
	
	

}
