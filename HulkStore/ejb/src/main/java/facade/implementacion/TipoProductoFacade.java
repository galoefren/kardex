package facade.implementacion;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.local.TipoProductoFacadeLocal;
import modelo.kardex.TipoProducto;
import modelo.seguridades.Usuario;

@Stateless
public class TipoProductoFacade extends AbstractFacade<TipoProducto> implements TipoProductoFacadeLocal{

	private String sql = "";
	private List<Object[]> listaTipoProducto;
	private TipoProducto tipoProducto;
	
	
	public TipoProductoFacade(){
		super(TipoProducto.class);
	}
	
	public TipoProductoFacade(Class<TipoProducto> entityClass) {
		super(entityClass);
	}

	@PersistenceContext(unitName = "hulkPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<Object[]> listaTotalTipoProducto(){
		
		sql = "SELECT t FROM TipoProducto t where t.estado = 1"; 
		listaTipoProducto =  em.createQuery(sql).getResultList();
		return listaTipoProducto;
	}

	@Override
	public TipoProducto buscarTipoProducto(int id) {
		
		sql = "SELECT t FROM TipoProducto t where t.estado = 1 and t.id=:id"; 
		tipoProducto =  (TipoProducto) em.createQuery(sql).setParameter("id",id).getSingleResult();
		return tipoProducto;
	}

}
