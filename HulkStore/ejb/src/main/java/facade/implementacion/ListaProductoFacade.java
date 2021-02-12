package facade.implementacion;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.local.ListaProductoFacadeLocal;
import modelo.kardex.ListaProducto;

@Stateless
public class ListaProductoFacade extends AbstractFacade<ListaProducto> implements ListaProductoFacadeLocal {

	private String sql = "";
	private List<Object[]> listaProducto;
	
	public ListaProductoFacade(){
		super(ListaProducto.class);
	}
	
	public ListaProductoFacade(Class<ListaProducto> entityClass) {
		super(entityClass);
	}

	@PersistenceContext(unitName = "hulkPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public List<Object[]> listaProductos(){
		
		sql = "SELECT l FROM ListaProducto l where l.estado = 1"; 
		listaProducto =  em.createQuery(sql).getResultList();
		return listaProducto;
	}

}
