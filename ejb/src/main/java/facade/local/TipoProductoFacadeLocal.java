package facade.local;

import java.util.List;
import javax.ejb.Local;
import facade.implementacion.AbstractFacade;
import modelo.kardex.ListaProducto;
import modelo.kardex.TipoProducto;

@Local
public interface TipoProductoFacadeLocal  {

	void create(TipoProducto tipopProducto);

	void edit(TipoProducto tipoProducto);

	void remove(TipoProducto tipoProducto);
	
	public TipoProducto buscarTipoProducto(int id);
	
	public List<Object[]> listaTotalTipoProducto();
	
	
}
