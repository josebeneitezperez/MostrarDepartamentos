package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		sesion = sessionFactory.openSession();
		logger.info("Conexión con la BD realizada");
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
		
		
		
		
		
		
		res.println("<html>");
		res.println("<title>Servlet de pruebas :)</title>");
		res.println("<body>");
		res.println("<div>Hola Mundo (desde " + this.getClass().getSimpleName() +")</div>");
		parameterMap.keySet().forEach(x -> res.println("<div>Parámetro " + x + " = " + String.join(",", parameterMap.get(x)) +"</div>"));
		res.println("</body>");
		res.println("</html>");
		
		return res;
	}
}



