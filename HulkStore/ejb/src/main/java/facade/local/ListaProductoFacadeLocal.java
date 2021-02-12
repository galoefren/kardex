package facade.local;

import java.util.List;

import javax.ejb.Local;

import modelo.kardex.ListaProducto;

@Local
public interface ListaProductoFacadeLocal {

	void create(ListaProducto producto);

	void edit(ListaProducto producto);

	void remove(ListaProducto prodcuto);
	
	public List<Object[]> listaProductos();
	
	
	
}
