package modelo.kardex;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="lista_productos",schema="kardex")
@NamedQuery(name="ListaProducto.findAll", query="SELECT l FROM ListaProducto l")
public class ListaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer cantidad;

	private String codigo;

	private Integer estado;

	@Column(name="nombre_item")
	private String nombreItem;

	private String ubicacion;

	//bi-directional many-to-one association to TipoProducto
	@ManyToOne
	@JoinColumn(name="id_tipo_producto")
	private TipoProducto tipoProducto;

	public ListaProducto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public TipoProducto getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}