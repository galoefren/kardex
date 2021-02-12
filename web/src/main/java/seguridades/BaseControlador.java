package seguridades;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BaseControlador {

	protected void redireccionarPagina(String pagina) {
        try {
            getExternalContext().redirect(getHttpRequest().getContextPath().concat(pagina));         
        } catch (IOException e) {
            agregarMensajeError("No se puede redireccionar a " + pagina, e.getLocalizedMessage());
        }
    }
	
	 protected ExternalContext getExternalContext() {
	        return FacesContext.getCurrentInstance().getExternalContext();
	    }
	 public HttpServletRequest getHttpRequest() {
	        return (HttpServletRequest) getExternalContext().getRequest();
	    }
	 protected void agregarMensajeError(String resumen, String detalle) {
	        FacesMessage errorMessage = new FacesMessage();
	        errorMessage.setSummary(resumen);
	        errorMessage.setDetail(detalle);
	        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
	    }
	
}
