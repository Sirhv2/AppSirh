package co.gov.ideam.sirh.view.util;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

import oracle.adf.view.rich.component.rich.input.RichInputText;
/**
 * Permite vlidar el login asignado a un usuario.
 * No permite numeros
 * No permite caracteres especiales
 * No permite espacio 
 * No se permite un espacio al inicio o al final del campo
 */
public class LoginValidator implements Validator{
    
    //private static final String NOMBRE_PATTERN = "(([A-Za-z])+(\\s[A-Za-z]+))" ;        
    private static final String NOMBRE_PATTERN = "([A-Za-z0-9])+";        
    
    private boolean valid;
    
    public LoginValidator() {
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {        
        String strNombre = (String)object;            
        valid = strNombre.matches(NOMBRE_PATTERN);        
        if(!valid || strNombre.endsWith(" ")){
            valid = false;
            String mensaje = StandarBean.getText("nombre_incorrecto");
            ((RichInputText)uiComponent).setValid(false);
            FacesMessage msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                mensaje, null);            
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(uiComponent.getClientId(ctx), msg);
            return;
        }                
    }
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
