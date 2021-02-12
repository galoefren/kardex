package seguridades;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;


import org.apache.log4j.Logger;

import facade.local.UsuarioFacadeLocal;
import modelo.seguridades.Usuario;
import recursos.Constantes;

@ManagedBean
@ViewScoped
public class LoginControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginControlador.class);
	private String usuario;
	private String password;
	private Usuario usuarioDAO;
	private boolean existeUsuario;
	private boolean validarPassword;
	
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private UsuarioFacadeLocal usuarioFacade;
	
	public LoginControlador() {}
	
	@PostConstruct
	public void init() {
		if (sesionControlador.getUsuarioSesion() != null) {
			redireccionarPagina("/faces/login.xhtml");
		}
	}
	
	public String login() {
		
		String urlDireccionamiento = "";
		int respuestaValidacion =0;
		
		/*Valida si el usuario existe en el sistema*/
		respuestaValidacion = validarUsuario();
		
		/*
		respuestaValidacion = 0; No existe usuario
		respuestaValidacion = 1; Existe usuario pero error en password
		respuestaValidacion = 2; Acceso concedido
		*/
		
		switch(respuestaValidacion){
		
		case 0:
			redireccionarPagina("/faces/login.xhtml");
			FacesContext.getCurrentInstance().addMessage("frmForm:messageInicio",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.NO_EXISTE_USUARIO, ""));
			break;
		case 1:
			redireccionarPagina("/faces/login.xhtml");
			FacesContext.getCurrentInstance().addMessage("frmForm:messageInicio",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.PASSWORD_INVALIDO, ""));
			break;
		case 2:
			urlDireccionamiento = "/kardex/principal.xhtml?faces-redirect=true";
			break;
		}
		return urlDireccionamiento;
	}
	
	/* Validar si el usuario esta registrado */
	private int validarUsuario() {

		/*
		usuarioValido = 0; No existe usuario
		usuarioValido = 1; Existe usuario pero error en password
		usuarioValido = 2; Acceso concedido
		 * */
		int usuarioValido = 0;

		try {

			/* valida la existencia del usuario */
			if (!usuario.isEmpty() && !password.isEmpty()) {

				existeUsuario = usuarioFacade.validarUsuario(usuario);

				if (existeUsuario) {
					/* Si el usuario esta registrado se valida el password */

					validarPassword = usuarioFacade.validarPassword(usuario, password);

					/*
					 * Se ha validado usuario y password y es TRUE accede al
					 * perfil
					 */
					if (validarPassword) {
						usuarioValido = 2;

					}else{
						usuarioValido = 1;
					}

				}else{
					/*No existe el usuario*/
					usuarioValido = 0;
				}
			}

		} catch (Exception ex) {
			usuarioValido = 0;
		}
		return usuarioValido;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void borrarCampos() {
		setUsuario("");
		setPassword("");
	}
	
}
