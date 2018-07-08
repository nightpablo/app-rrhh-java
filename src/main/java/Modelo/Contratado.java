package Modelo;

import java.util.Date;

public class Contratado extends Empleado{

	private Double montoPorHoras;
	public Contratado() {super();}
	
	public Contratado(Integer id, String nombre, String correo, String cuil, Date fecha, Integer horas, Double montoxhs) {
		super(id, nombre, correo, cuil, fecha, horas);
		this.montoPorHoras = montoxhs;
	}

	@Override
	public Double salario() {
		return montoPorHoras * getHorasTrabajados();
	}

	public Double getMontoPorHoras() {
		return montoPorHoras;
	}

	public void setMontoPorHoras(Double montoPorHoras) {
		this.montoPorHoras = montoPorHoras;
	}

	@Override
	public boolean esContratado() {
		return true;
	}

	@Override
	public boolean esEfectivo() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		Contratado c = (Contratado) obj;
		return super.equals(obj)
				&& this.montoPorHoras.equals(c.montoPorHoras);
	}

	@Override
	public String toString() {
		return super.toString()+"\n"+
				String.format("---> CONTRATADO { montoPorHoras } = { %s }",this.montoPorHoras);
	}
	
	

}
