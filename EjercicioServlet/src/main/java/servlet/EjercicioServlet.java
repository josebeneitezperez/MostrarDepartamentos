package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class EjercicioServlet
 */
@WebServlet("/EjercicioServlet")
public class EjercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(EjercicioServlet.class);
	//private static String ruta = "C:\\Users\\josec\\workspace CursoJava\\EjercicioServlet\\resources\\log4j.properties"; //casa
	private static String ruta = "C:\\Users\\Formacion\\workspace Jose\\EjercicioServlet\\resources\\log4j.properties";	
	
	public static SessionFactory sessionFactory = null;
	private static Session sesion = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EjercicioServlet() {
        super();
        PropertyConfigurator.configure(ruta);
		logger.info("Aplicación iniciada");
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
		
		List<Departamento> listaDepartamentos = null;
		listaDepartamentos = ControladorDepartamento.buscarTodos();
		logger.info("ListaDepartamentos leída de la BD");
		
		res.println("<html>");
		res.println("<title>Servlet de pruebas :)</title>");
		res.println("<body>");
		//res.println("<div>Hola Mundo (desde " + this.getClass().getSimpleName() +")</div>");
		//parameterMap.keySet().forEach(x -> res.println("<div>Parámetro " + x + " = " + String.join(",", parameterMap.get(x)) +"</div>"));
		
		res.println("<table border=\"2\">");
		res.println("<tr>");
		res.println("<th>Código</th>");
		res.println("<th>Nombre</th>");
		res.println("<th>Código responsable</th>");
		res.println("</tr>");
		
		if(listaDepartamentos!=null) {
			logger.info("Leída tabla de la BD");

			for (Departamento departamento: listaDepartamentos) {
				res.println("<tr>");
				
				res.println("<td>"+departamento.getCodigo()+"</td>");
				res.println("<td>"+departamento.getNombre()+"</td>");
				res.println("<td>"+departamento.getCodResponsable()+"</td>");
				
				res.println("</tr>");
			}
			res.println("</tr>");
		}
		
		res.println("</body>");
		res.println("</html>");
		
		return res;
	}
}



