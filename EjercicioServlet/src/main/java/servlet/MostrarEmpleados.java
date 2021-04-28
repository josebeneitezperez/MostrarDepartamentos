package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import main.java.clasesVO.Empleado;
import main.java.controlador.ControladorEmpleado;

/**
 * Servlet implementation class MostrarEmpleados
 */
@WebServlet("/MostrarEmpleados")
public class MostrarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(MostrarEmpleados.class);
	//private static String ruta = "C:\\Users\\josec\\workspace CursoJava\\EjercicioServlet\\resources\\log4j.properties"; //casa
	private static String ruta = "C:\\Users\\Formacion\\workspace Jose\\EjercicioServlet\\resources\\log4j.properties";	
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarEmpleados() {
        super();
        PropertyConfigurator.configure(ruta);
		logger.info("Aplicación iniciada");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Map<String, String[]> parameterMap = request.getParameterMap();
		printResponse(out, parameterMap);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

private PrintWriter printResponse(PrintWriter out, Map<String, String[]> parameterMap) {
		
		PrintWriter res = out;
		
		List<Empleado> listaEmpleados = null;
		listaEmpleados = ControladorEmpleado.buscarTodos();
		logger.info("ListaDepartamentos leída de la BD");
		
		res.println("<html>");
		res.println("<title>Mostrar Empleados :)</title>");
		res.println("<body>");
		//res.println("<div>Hola Mundo (desde " + this.getClass().getSimpleName() +")</div>");
		//parameterMap.keySet().forEach(x -> res.println("<div>Parámetro " + x + " = " + String.join(",", parameterMap.get(x)) +"</div>"));
		
		res.println("<table border=\"2\">");
		res.println("<tr>");
		res.println("<th>Código</th>");
		res.println("<th>Nombre</th>");
		res.println("<th>Primer apellido</th>");
		res.println("<th>Segundo apellido</th>");
		res.println("<th>Lugar de nacimiento</th>");
		res.println("<th>Fecha de nacimiento</th>");
		res.println("<th>Dirección</th>");
		res.println("<th>Teléfono</th>");
		res.println("<th>Puesto</th>");
		res.println("<th>Código departamento</th>");
		res.println("</tr>");
		
		if(listaEmpleados!=null) {
			logger.info("Leída tabla de la BD");

			for (Empleado empleado: listaEmpleados) {
				res.println("<tr>");
				
				res.println("<td>"+empleado.getCodigo()+"</td>");
				res.println("<td>"+empleado.getNombre()+"</td>");
				res.println("<td>"+empleado.getApellido1()+"</td>");
				res.println("<td>"+empleado.getApellido2()+"</td>");
				res.println("<td>"+empleado.getLugarNacimiento()+"</td>");
				res.println("<td>"+empleado.getFechaNacimiento()+"</td>");
				res.println("<td>"+empleado.getDireccion()+"</td>");
				res.println("<td>"+empleado.getTelefono()+"</td>");
				res.println("<td>"+empleado.getPuesto()+"</td>");
				res.println("<td>"+empleado.getCodDepartamento()+"</td>");
				
				res.println("</tr>");
			}
			res.println("</tr>");
		}
		
		res.println("</body>");
		res.println("</html>");
		
		return res;
	}
}
