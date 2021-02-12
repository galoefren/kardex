package facade.implementacion;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;
import facade.local.UsuarioFacadeLocal;
import modelo.seguridades.Usuario;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal{
	
	private String sql = "";
	private Usuario usuarioDAO;
	private List<Object> listaObjetos;
	
	/*Mensajes para desplegar en la consola del servidor*/
	private static final Logger LOGGER = Logger.getLogger(UsuarioFacade.class.getName());
	
	public UsuarioFacade() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	} 
	
	public UsuarioFacade(Class<Usuario> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	} 
	
	@PersistenceContext(unitName = "hulkPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	/*Metodo para buscar usuario del sistema*/
	public Usuario encontrarUsuario(String usuario, String password){
		
		usuarioDAO = null;
		try {
			sql = "SELECT u FROM Usuario u where u.usuario = :usuario and u.password = :password"; 
			listaObjetos = em.createQuery(sql).setParameter("usuario", usuario).setParameter("password", password).getResultList();
			if(!listaObjetos.isEmpty()){
				usuarioDAO = (Usuario)listaObjetos.get(0);
			}
		}catch (Exception ex){
			LOGGER.log(Level.SEVERE, "ERROR EL CONSULTAR USUARIO Y PASSWORD", ex);
		}
		return usuarioDAO;
	}
	
	/*Metodo para validar el usuario en el sistema*/
	public boolean validarUsuario(String usuario){
		
		usuarioDAO = null;
		try {
			sql = "SELECT u FROM Usuario u where u.usuario = :usuario "; 
			listaObjetos = em.createQuery(sql).setParameter("usuario", usuario).getResultList();
			if(!listaObjetos.isEmpty()){
				return true;
			}
		}catch (Exception ex){
			LOGGER.log(Level.SEVERE, "ERROR EL CONSULTAR AL VALIDAR USUARIO", ex);
		}
		return false;
	}
	
	/*Metodo para validar el password de un usuario en el sistema*/
	public boolean validarPassword(String usuario,String password){
		
		usuarioDAO = null;
		try {
			sql = "SELECT u FROM Usuario u where u.usuario = :usuario and u.password = :password"; 
			listaObjetos = em.createQuery(sql).setParameter("usuario", usuario).setParameter("password", password).getResultList();
			if(!listaObjetos.isEmpty()){
				return true;
			}
		}catch (Exception ex){
			LOGGER.log(Level.SEVERE, "ERROR EL CONSULTAR AL VALIDAR USUARIO", ex);
		}
		return false;
	}



}
