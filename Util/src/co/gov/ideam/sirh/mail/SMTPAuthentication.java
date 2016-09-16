package co.gov.ideam.sirh.mail;


import java.util.ResourceBundle;

import javax.mail.PasswordAuthentication;

public class SMTPAuthentication extends javax.mail.Authenticator{

    private  String username;
    private  String password;
    
    public SMTPAuthentication(String user, String passwd){
        username = user;
        password = passwd;
    }
    
    public PasswordAuthentication getPasswordAuthentication(){
        if (this.username == null || this.password ==null){
            throw new RuntimeException("No se han establecido los valores para conectarse con el servidor SMTP");
        }
        return new PasswordAuthentication(username, password);
    }
}
