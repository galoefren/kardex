package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import facade.local.ListaProductoFacadeLocal;
import facade.local.TipoProductoFacadeLocal;
import modelo.kardex.ListaProducto;
import modelo.kardex.TipoProducto;
import seguridades.BaseControlador;
import seguridades.SesionControlador;

@ManagedBean
@ViewScoped
public class PrincipalControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private ListaProductoFacadeLocal listaProducto;
	@EJB
	private TipoProductoFacadeLocal tipoProducto;

	private String nombreUsuario;
	private List<Object[]> listaProductos;
	private List<Object[]> litaTipoProducto;
	private ListaProducto listaProductoEdit;

	/* Campos para guardar un Producto */
	private String codigoControlador;
	private Integer cantidadControlador;
	private String nombreProductoControlador;
	private String ubicacionControlador;
	private Integer tipoProductoControlador;
	private TipoProducto tipoProductoItem;

	// private List<Ubicacion> listaUbicacion;
	private List<String> listaUbicacion;

	private ListaProducto producto;

	@PostConstruct
	public void init() {

		if (sesionControlador.getUsuarioSesion() != null) {
			nombreUsuario = sesionControlador.getUsuarioSesion().getNombre();
		}
		inicializarVariables();
		cargarListaProductos();
	}

	public void productoSeleccionadoEditar(Object[] object) {

		listaProductoEdit = (ListaProducto) (object[0]);
		codigoControlador = listaProductoEdit.getCodigo();
		cantidadControlador = listaProductoEdit.getCantidad();
		nombreProductoControlador = listaProductoEdit.getNombreItem();
		ubicacionControlador = listaProductoEdit.getUbicacion();

	}

	public void productoeleccionadoActivar(Object[] object) {

	}

	public void productoeleccionadoBorrar(Object[] object) {

	}

	public void productoeleccionadoEliminar(Object[] object) {

	}

	private void inicializarVariables() {
		
		listaProductos = new ArrayList<Object[]>();
		listaUbicacion = new ArrayList<String>();
		litaTipoProducto = new ArrayList<Object[]>();
		producto = new ListaProducto();
		
	}

	private void cargarListaProductos() {

		listaProductos = listaProducto.listaProductos();
		litaTipoProducto = tipoProducto.listaTotalTipoProducto();
		/* Cargar ubicacion de los diferentes productos */
		listaUbicacion.add("Sector 1");
		listaUbicacion.add("Sector 2");
		listaUbicacion.add("Sector 3");
		listaUbicacion.add("Sector 4");
		listaUbicacion.add("Sector 5");
		listaUbicacion.add("Sector 6");
		
	}

	public void guardarProducto() {

		producto.setCodigo(codigoControlador);
		producto.setCantidad(cantidadControlador);
		producto.setNombreItem(nombreProductoControlador);
		tipoProductoItem = tipoProducto.buscarTipoProducto(tipoProductoControlador);
		producto.setTipoProducto(tipoProductoItem);
		producto.setUbicacion(ubicacionControlador);
		producto.setEstado(1);
		listaProducto.create(producto);

	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<Object[]> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Object[]> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public SesionControlador getSesionControlador() {
		return sesionControlador;
	}

	public void setSesionControlador(SesionControlador sesionControlador) {
		this.sesionControlador = sesionControlador;
	}

	public String getCodigoControlador() {
		return codigoControlador;
	}

	public void setCodigoControlador(String codigoControlador) {
		this.codigoControlador = codigoControlador;
	}

	public String getNombreProductoControlador() {
		return nombreProductoControlador;
	}

	public void setNombreProductoControlador(String nombreProductoControlador) {
		this.nombreProductoControlador = nombreProductoControlador;
	}

	public String getUbicacionControlador() {
		return ubicacionControlador;
	}

	public void setUbicacionControlador(String ubicacionControlador) {
		this.ubicacionControlador = ubicacionControlador;
	}

	public Integer getCantidadControlador() {
		return cantidadControlador;
	}

	public void setCantidadControlador(Integer cantidadControlador) {
		this.cantidadControlador = cantidadControlador;
	}

	public List<String> getListaUbicacion() {
		return listaUbicacion;
	}

	public void setListaUbicacion(List<String> listaUbicacion) {
		this.listaUbicacion = listaUbicacion;
	}

	public Integer getTipoProductoControlador() {
		return tipoProductoControlador;
	}

	public void setTipoProductoControlador(Integer tipoProductoControlador) {
		this.tipoProductoControlador = tipoProductoControlador;
	}

	public List<Object[]> getLitaTipoProducto() {
		return litaTipoProducto;
	}

	public void setLitaTipoProducto(List<Object[]> litaTipoProducto) {
		this.litaTipoProducto = litaTipoProducto;
	}

	public ListaProducto getListaProductoEdit() {
		return listaProductoEdit;
	}

	public void setListaProductoEdit(ListaProducto listaProductoEdit) {
		this.listaProductoEdit = listaProductoEdit;
	}

}
