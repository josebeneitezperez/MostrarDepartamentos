package clasesDAO;

import java.text.SimpleDateFormat; 
import java.util.Calendar;
import java.util.List;  

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;



import Vista.Principal;
import clasesVO.Empleado;

public class EmpleadoDAO {

	private static Logger logger = LogManager.getLogger(EmpleadoDAO.class);
	private static Session sesion = null;

	public static List<Empleado> findAll() {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();

		List<Empleado> lista = null;
		String query = "from Empleado order by codigo";
		try {
			lista = sesion.createQuery(query).list();
			tx.commit();
		} catch (Exception e) {
			logger.error("Error al ejecutar la Query " + query + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return lista;
	}

	public static void insert(Empleado empleado) {

		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		try {
			sesion.save(empleado);
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo insertar el empleado con el codigo: " + empleado.getCodigo() + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static void remove(Empleado empleado) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		try {
			sesion.remove(empleado);
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo borrar el empleado con el codigo: " + empleado.getCodigo() + " error: ", e);
		}
	}

	public static void update(Empleado empleado) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		try {
			sesion.update(empleado);
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo actualizar el empleado " + empleado.getCodigo() + " error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
	}

	public static Empleado get(int id) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		Empleado unEmpleado = null;
		try {
			unEmpleado = sesion.get(Empleado.class, id);
			tx.commit();
		} catch (Exception e) {
			logger.error("No se pudo obtener el empleado con el id: " + id + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return unEmpleado;
	}
	
	//Consulta HQL (lenguaje de Hibernate)
	public static List<Empleado> getEmpleados(int idDepartamento) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		String hqlQuery = "from Empleado e where e.codDepartamento = :idDepartamento";	//OJO, "e.codDepartamento" es el nombre en Empleado.class, no en la BD
		List<Empleado> listaEmpleados = null;
		try {
			listaEmpleados = sesion.createQuery(hqlQuery, Empleado.class).setParameter("idDepartamento", idDepartamento).list();	
			tx.commit();
			
		} catch (Exception e) {
			logger.error("Error al ejecutar la Query " + hqlQuery + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return listaEmpleados;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Empleado> getEmpleadosPorEdad(int edad) {
		sesion = Principal.sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();
		
		List<Empleado> listaEmpleados = null;
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-YY"); //dd-MM-YYYY
			Calendar calendar = Calendar.getInstance();
			
			calendar.add(Calendar.YEAR, -edad); //indicamos que vamos a cambiar los años, restan
			String fechaNacimientoMinima = formateador.format(calendar.getTime());
			System.out.println("fechaNacimientoMinima vale : "+fechaNacimientoMinima);
			
			//Sin Criteria
		    //Conjunction and = Restrictions.conjunction();
			//and.add( Restrictions.lt("fechaNacimiento", fechaNacimientoMinima) ); 

			
			//Con Criteria (deprecated)
			Criteria criteria = sesion.createCriteria(Empleado.class);
			criteria.add(Restrictions.lt("fechaNacimiento", fechaNacimientoMinima));
			listaEmpleados = (List<Empleado>) criteria.list();
			
			
			//Con CriteriaQuery (no deprecated)
			
			/*=============comentario para tapar error, voy por aquí=================
			 * 
			CriteriaBuilder cb = em.getCriteriaBuilder();
			  CriteriaQuery<Empleado> query = cb.createQuery(Empleado.class);

			  Root<Empleado> p = query.from(Empleado.class);
			  query.select(p);
			  query.where(cb.equal(p.get("oid"), oid)
			       .and(cb.between(p.get("dateCreated"), Utils.getDateMinus(1), new Date())));
			*/
			
			tx.commit();
			
		} catch (Exception e) {
			logger.error("No se pudieron obtener los empleados mayores de "+ edad + ", error: ", e);
		} finally {
			try {
				sesion.close();
			} catch (HibernateException e) {
				logger.error("No se pudo cerrar la conexión con la BD, error: ", e);
			}
		}
		return listaEmpleados;
	} 
}








