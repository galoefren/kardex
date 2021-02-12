package seguridades;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class SesionControlador implements Serializable {

	private static final long serialVersionUID = -5588239209059592228L;
	private Usuario usuarioSesion;

	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}

	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}
	
	
	
}
