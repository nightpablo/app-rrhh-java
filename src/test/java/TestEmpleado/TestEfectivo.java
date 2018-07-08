package TestEmpleado;

import static org.junit.Assert.*;

import org.junit.Test;
import Modelo.Efectivo;

public class TestEfectivo {

	/**
	* Test of salario method, of class Efectivo.
	*/
	@Test
	public void testSalarioSinHorasExtras() {
		System.out.println("salario");
		Efectivo empleado = new Efectivo();
		empleado.setHorasTrabajados(40);
		empleado.setHorasMinimasObligatorias(40);
		empleado.setComisiones(2000.0);
		empleado.setSueldoBasico(30000.0);
		Double expResult = 30000.0+2000.0;
		Double result = empleado.salario();
		assertEquals(expResult, result);
	}
	/**
	* Test of salario method, of class Efectivo.
	*/
	@Test
	public void testSalario2HorasExtras() {
		System.out.println("salario");
		Efectivo empleado = new Efectivo();
		empleado.setHorasTrabajados(42);
		empleado.setHorasMinimasObligatorias(40);
		empleado.setComisiones(2000.0);
		empleado.setSueldoBasico(30000.0);
		Double expResult = 30000.0+2000.0+3000.0;
		Double result = empleado.salario();
		assertEquals(expResult, result);
	}

}
