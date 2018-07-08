package TestBD;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;


import Controlador.EmpleadoDaoJdbc;
import Modelo.*;

public class TestEmpleado {

	Empleado e1;
	Empleado e2;
	EmpleadoDaoJdbc empDao;
	
	@Before
	public void initialize() throws Exception {
		e1 = new Efectivo();
		e1.setNombre("Martin Dominguez");
		e1.setCorreoElectronico("mdomingu@gmail.com");
		e1.setCuil("000000000000");
		e1.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2005"));
		e1.setHorasTrabajados(80);
		((Efectivo) e1).setHorasMinimasObligatorias(60);
		((Efectivo) e1).setComisiones(5000.0);
		((Efectivo) e1).setSueldoBasico(80000.0);
		
		e2 = new Contratado();
		e2.setNombre("Jose Maria Listorti");
		e2.setCorreoElectronico("jml1966@hotmail.com");
		e2.setCuil("4564645877");
		e2.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
		e2.setHorasTrabajados(30);
		((Contratado) e2).setMontoPorHoras(2000.0);
		
		empDao = new EmpleadoDaoJdbc();
		
	}
	
	
	@Test
	public void testEfectivo() {
		assertFalse(e1.esContratado());
		assertTrue(e1.esEfectivo());
		Double salario = 80000.0 + 5000.0 + (80-60)*1/20*80000.0;
		assertEquals(e1.salario(), salario);
		empDao.crear(e1);
		Empleado buscado = empDao.buscarPorId(1); // Si se crea por primera vez el empleado, sino toma el empleado con id = 1
		assertNotNull(buscado);
		empDao.crear(e1); //vuelvo a crearlo para eliminarlo
		e1.setId(2);
		empDao.eliminar(e1);
		buscado = empDao.buscarPorId(2); // el empleado creado con id = 2 debe estar eliminado
		assertNull(buscado);
	}
	
	@Test
	public void testContratado() {
		assertFalse(e2.esEfectivo());
		assertTrue(e2.esContratado());
		Double salario = 2000.0*30;
		assertEquals(e2.salario(), salario);
		empDao.crear(e2); // se deberia generar con id = 3
		e2.setId(3);
		e2.setNombre("El pasayo de VIDEOMATCH");
		empDao.actualizar(e2);
		Empleado buscado = empDao.buscarPorId(3);
		assertEquals(buscado.getNombre(),"El pasayo de VIDEOMATCH");
		//-------------------------- Para el resto de todos los empleados
		List<Empleado> lista = empDao.buscarTodos(); 
		assertFalse(lista.size()==0);
		for(Empleado i : lista) {
			empDao.eliminar(i);
		}
		assertTrue(empDao.buscarTodos().size()==0);
	}

}
