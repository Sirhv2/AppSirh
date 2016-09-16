package co.gov.ideam.sirh.mail;

import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Properties;

import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Envia los mail a las cuentas correspondientes
 */
public class IdeamMail {
        
    private String SubjectMail;
    String host;
    private MimeMessage message;
    private Multipart mp;
          
    public IdeamMail(String body,
                     String to,
                     String subject)throws IdeamException{                
        List lista = new ArrayList();
        lista.add(to);
        setHost(MailConfigurator.getHost());
        System.out.println(body);
        sendMessage(lista,
                    new ArrayList(),
                    body,
                    MailConfigurator.getHost(),
                    MailConfigurator.getUsuario(),
                    MailConfigurator.getPassword(),
                    subject);        
    }
    public IdeamMail(List to, 
                     List cc,
                     String body,
                     String host,
                     String userMail,
                     String password,
                     String subject)throws IdeamException{
        this.sendMessage(to,
                         cc,
                         body,
                         host,
                         userMail,
                         password,
                         subject);           
    }
    public void sendMessage(List to, 
                     List cc,
                     String body,
                     String host,
                     String userMail,
                     String password,
                     String subject)throws IdeamException{
        
        this.setSubjectMail(subject);
        
        if (MailConfigurator.getUsuario() == null || MailConfigurator.getUsuario().length()==0 || 
            MailConfigurator.getPassword() == null || MailConfigurator.getPassword().length()==0){
            throw new IdeamException("No se han establecido los valores para conectarse al servidor SMTP");
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.host", MailConfigurator.getHost());
        props.put("mail.smtp.auth", "true");

        SMTPAuthentication auth = new SMTPAuthentication(MailConfigurator.getUsuario(), 
                                                         MailConfigurator.getPassword()); 
        Session session = Session.getInstance(props,auth);
            
        message = new MimeMessage(session);
        mp = new MimeMultipart();
        
        try{
            message.setSubject(getSubjectMail());
            MimeBodyPart mbp1 = new MimeBodyPart();            
            mbp1.setText(body);
            
            mp.addBodyPart(mbp1);
            
        }catch(MessagingException e){
            throw new IdeamException(e.getMessage());
        }

        // Establecer las direcciones
        try{
            message.setFrom(new InternetAddress(MailConfigurator.getUsuario()));
            
            if (to==null || to.size()==0){
                throw new IdeamException("No existen direcciones registradas, no es posible enviar el mensaje");
            }
            
            Iterator it = to.iterator();            
            while(it.hasNext()){
                String dir = (String)it.next();
                InternetAddress email = new InternetAddress(dir);                
                message.addRecipient(Message.RecipientType.TO, email);
            }

            it = cc.iterator();            
            while(it.hasNext()){
                String dir = (String)it.next();
                InternetAddress email = new InternetAddress(dir);                
                message.addRecipient(Message.RecipientType.CC, email);
            }            
            
        }catch(AddressException e){
            throw new IdeamException(e.getMessage());
        }catch(MessagingException e){
            throw new IdeamException(e.getMessage());
        }        
    }
        
    /** Permite adicionar un archivo al mensaje */
    public void addFile(String archivo)throws IdeamException{
        try{
            MimeBodyPart mbp2 = new MimeBodyPart();   
            FileDataSource fds = new FileDataSource(archivo);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            mp.addBodyPart(mbp2);                
        }catch(MessagingException e){
            throw new IdeamException(e.getMessage());
        }
    }        
        
    public void send()throws IdeamException{
        
        try{            
            message.setSentDate(new Date());            
            message.setContent(mp);
            Transport.send(message);
        }catch(MessagingException e){
            throw new IdeamException(e.getMessage());
        }
    }

    public String getSubjectMail() {
        return SubjectMail;
    }

    public void setSubjectMail(String SubjectMail) {
        this.SubjectMail = SubjectMail;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}

