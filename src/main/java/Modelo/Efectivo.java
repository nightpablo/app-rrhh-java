package Modelo;

import java.util.Date;

public class Efectivo extends Empleado {

	
	private Double sueldoBasico;
	private Double comisiones;
	private Integer horasMinimasObligatorias;
	public Efectivo() {super();}
	
	public Efectivo(Integer id, String nombre, String correo, String cuil, Date fecha, Integer horas, Double sueldo, Double comision, Integer hsMinimas) {
		super(id, nombre, correo, cuil, fecha, horas);
		this.sueldoBasico = sueldo;
		this.comisiones = comision;
		this.horasMinimasObligatorias = hsMinimas;
	}

	@Override
	public Double salario() {
		return sueldoBasico + comisiones + ((horasMinimasObligatorias<getHorasTrabajados())? getHorasTrabajados()-horasMinimasObligatorias: 0)*sueldoBasico/20;
	}

	public Double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(Double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public Double getComisiones() {
		return comisiones;
	}

	public void setComisiones(Double comisiones) {
		this.comisiones = comisiones;
	}

	public Integer getHorasMinimasObligatorias() {
		return horasMinimasObligatorias;
	}

	public void setHorasMinimasObligatorias(Integer horasMinimasObligatorias) {
		this.horasMinimasObligatorias = horasMinimasObligatorias;
	}

	public boolean esEfectivo() {
		return true;
	}

	@Override
	public boolean esContratado() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		Efectivo e = (Efectivo) obj;
		return super.equals(obj) 
				&& this.sueldoBasico.equals(e.sueldoBasico)
				&& this.comisiones.equals(e.comisiones)
				&& this.horasMinimasObligatorias == e.horasMinimasObligatorias;
	}

	@Override
	public String toString() {
		return super.toString()+"\n"+
				String.format("---> EFECTIVO { sueldoBasico, comisiones, horasMinimasObligatorias } = { %s, %s, %d }",this.sueldoBasico, this.comisiones, this.horasMinimasObligatorias);
	}
	
	
	
}
