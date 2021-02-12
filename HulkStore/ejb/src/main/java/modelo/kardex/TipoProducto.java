package modelo.kardex;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tipo_producto",schema="kardex")
@NamedQuery(name="TipoProducto.findAll", query="SELECT t FROM TipoProducto t")
public class TipoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String codigo;

	private Integer estado;

	@Column(name="nombre_item")
	private String nombreItem;

	//bi-directional many-to-one association to ListaProducto
	@OneToMany(mappedBy="tipoProducto")
	private List<ListaProducto> listaProductos;

	public TipoProducto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getNombreItem() {
		return this.nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public List<ListaProducto> getListaProductos() {
		return this.listaProductos;
	}

	public void setListaProductos(List<ListaProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ListaProducto addListaProducto(ListaProducto listaProducto) {
		getListaProductos().add(listaProducto);
		listaProducto.setTipoProducto(this);

		return listaProducto;
	}

	public ListaProducto removeListaProducto(ListaProducto listaProducto) {
		getListaProductos().remove(listaProducto);
		listaProducto.setTipoProducto(null);

		return listaProducto;
	}

}