package facade.local;

import javax.ejb.Local;
import modelo.seguridades.Usuario;

@Local
public interface UsuarioFacadeLocal {

	public Usuario encontrarUsuario(String usuario, String password);
	public boolean validarUsuario(String usuario);
	public boolean validarPassword(String usuario,String password);
	
}
