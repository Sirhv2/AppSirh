package co.gov.ideam.sirh.view.util;

import co.gov.ideam.sirh.view.StandarBean;

import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import oracle.adf.view.rich.component.rich.input.RichInputText;
/**
 * Permite validar una direccion de correo 
 */
public class EmailValidator implements Validator{
    /** It verifies that: - Only letters, numbers and email acceptable symbols (+, _, -, .) are allowed - No two different symbols may follow each other - Cannot begin with a symbol - Ending domain must be at least 2 letters - Supports subdomains - TLD must be between 2 and 6 letters (Ex: .ca, .museum) - Only (-) and (.) symbols are allowed in domain, but not consecutively. Problems: See comments below */
    private static  final String EMAIL_PATTERN = "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$";
    
    /** The pattern. */
    private Pattern pattern;
    
    /** The matcher. */
    private Matcher matcher;
    
    private boolean valid;
    
    public EmailValidator(){
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object object) {
        String strEmail = (String)object;            
        matcher = pattern.matcher(strEmail);
        valid = matcher.matches();            
        if(!valid ){
            String mensaje = StandarBean.getText("email_incorrecto");
            ((RichInputText)uiComponent).setValid(false);
            FacesMessage msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                mensaje, null);            
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(uiComponent.getClientId(ctx), msg);
            return;
            /*throw new ValidatorException(
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 mensaje, 
                                 null));*/
        }
        //Convertir a una internetaddress para asegurar que es una direccion valida
        InternetAddress email;
        try {
            email = new InternetAddress(strEmail);
        } catch (AddressException e) {
            String mensaje = StandarBean.getText("email_incorrecto");
            ((RichInputText)uiComponent).setValid(false);
            FacesMessage msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                mensaje, null);            
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(uiComponent.getClientId(ctx), msg);
        }
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
