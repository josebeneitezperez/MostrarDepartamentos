package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clasesVO.Departamento;
import main.java.controlador.ControladorDepartamento;
import main.java.controlador.HibernateUtil;

/**
 * Servlet implementation class MostrarDatos
 */
@WebServlet("/MostrarDatos")
public class MostrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(MostrarDatos.class);
	//private static String ruta = "C:\\Users\\josec\\workspace CursoJava\\EjercicioServlet\\resources\\log4j.properties"; //casa
	private static String ruta = "C:\\Users\\Formacion\\workspace Jose\\EjercicioServlet\\resources\\log4j.properties";
	private static SessionFactory sessionFactory=null;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarDatos() {
		super();
		PropertyConfigurator.configure(ruta);
		logger.info("Aplicación iniciada");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}
	
	public static Session abrirSesion() {
		if(sessionFactory==null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		return sessionFactory.openSession();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out;
		String parametro = null;
		if ((parametro = request.getParameter("table")) != null) { 	// table es el nombre del parametro, 
																	//y el valor es lo de despues del "=" en la url)
			if (parametro.equalsIgnoreCase("empleados")) {

				logger.info("Redirigiendo a " + parametro);
				request.getRequestDispatcher("MostrarEmpleados").forward(request, response);
			} else if (parametro.equalsIgnoreCase("departamentos")) {

				logger.info("Redirigiendo a " + parametro);
				request.getRequestDispatcher("EjercicioServlet").forward(request, response);
			} else {
				out = response.getWriter();
				printResponse(out);
				out.close();
			}
		} else {
			logger.info("Se entró en http://localhost:8080/EjercicioServlet/MostrarDatos sin un parametro \"table\"");
			out = response.getWriter();
			printResponse(out);
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private PrintWriter printResponse(PrintWriter out) {

		PrintWriter res = out;
		
		res.println("<html>");
		res.println("<title>Mostrar datos :)</title>");
		res.println("<body>");
		// res.println("<div>Hola Mundo (desde " + this.getClass().getSimpleName()
		// +")</div>");
		// parameterMap.keySet().forEach(x -> res.println("<div>Parámetro " + x + " = "
		// + String.join(",", parameterMap.get(x)) +"</div>"));

		res.println("Bienvenido a Mostrar datos");
		res.println("<br>");
		res.println("Para ir a la tabla empleados inserte: ");
		res.println("<br>");
		res.println("http://localhost:8080/EjercicioServlet/MostrarDatos?table=empleados");
		res.println("<br>");
		res.println("Para ir a la tabla departamentos inserte: ");
		res.println("<br>");
		res.println("http://localhost:8080/EjercicioServlet/MostrarDatos?table=departamentos");
		res.println("</body>");
		res.println("</html>");

		return res;
	}
}
