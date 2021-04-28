package main.java.controlador;

import java.util.List; 

//import org.hibernate.Session; uso solo si mandamos por parametro la session

import main.java.clasesDAO.EmpleadoDAO;
import main.java.clasesVO.Empleado;

public class ControladorEmpleado {

	public static List<Empleado> buscarTodos() {
		return EmpleadoDAO.findAll();
	}

	public static void insertar(Empleado empleado) {
		EmpleadoDAO.insert(empleado);
	}

	public static void Eliminar(Empleado empleado) {
		EmpleadoDAO.remove(empleado);
	}

	public static void modificar(Empleado empleado) {
		EmpleadoDAO.update(empleado);
	}
	
	public static Empleado get(int id) {
		return EmpleadoDAO.get(id);
	}
	
	public static List<Empleado> getEmpleados(int idDepartamento){
		return EmpleadoDAO.getEmpleados(idDepartamento);
	}
	
	public static List<Empleado> getEmpleadosPorEdad(int edad){
		
		EmpleadoDAO.getEmpleadosPorEdad(edad);
		return null;
	}
}
