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
 * Permite vlidar nombres.
 * No permite numeros
 * No permite caracteres especiales
 * Solo se permite un espacio entre palabras
 * No se permite un espacio al inicio o al final del campo
 */
public class NombreValidator implements Validator{
    
    //private static final String NOMBRE_PATTERN = "(([A-Za-z])+(\\s[A-Za-z]+))" ;        
    private static final String NOMBRE_PATTERN = "([A-Za-z·ÈÌÛ˙¡…Õ”⁄]\\s?)+";        
    
    private boolean valid;
    
    public NombreValidator() {
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        // Validar que no contenga no contenga nuemros        
        // No permite ingreso de caracteres especiales
        // No debe iniciar con espacio en blanco
        // No debe terminar con espacio en blanco        
        // Solo se permite un espacio en blanco enter palabras
        
        String strNombre = (String)object;    
        strNombre = strNombre.trim();
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
