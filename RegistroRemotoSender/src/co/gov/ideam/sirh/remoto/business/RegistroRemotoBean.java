package co.gov.ideam.sirh.remoto.business;

import co.gov.ideam.sirh.remoto.model.EventoSalida;
import co.gov.ideam.sirh.util.IdeamException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;


import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import javax.jms.Session;


@Stateless(name = "RegistroRemoto", mappedName = "Sirh-RegistroRemoto")
@Remote
public class RegistroRemotoBean implements RegistroRemotoLocal {
    
    private static Boolean IDEAM = null;
    /**
     * Referencia a la fabrica de conexiones JMS
     */
    @Resource(mappedName="java:jboss/java:RegistroConnectionFactory") private ConnectionFactory connectionFactory;
    /**
     * Referencia a la cola
     */
    @Resource(mappedName="java:/RegistroSIRHQueue") private Queue queue;    
    
    public RegistroRemotoBean() {
        
    }
    @PostConstruct
    public void init() {
        if (IDEAM==null){
            System.out.println("::::::::::::  Verificando Instancia IDEAM ::::::::::::");
            String valorIdeam = System.getProperty("sirh.ideam");
            if(valorIdeam!= null && valorIdeam.endsWith("1")){
                IDEAM = true;
                System.out.println("::::::::::::  Encontrado Instancia IDEAM ::::::::::::");
            }else{
                IDEAM = false;
                System.out.println("::::::::::::  No existe Parametro Instancia IDEAM ::::::::::::");
            }        
        }
    }    
    public void registrarEvento(String serviceName, String methodName, Object... data)throws IdeamException{ 
        init();
        if (IDEAM){
            return;
        }
        // Registrar en la cola para que sea procesado por el MDB
        try{            
            Connection con = connectionFactory.createConnection("ideam","pwd12345#");
            
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            MessageProducer mp = session.createProducer( queue );
            ObjectMessage obj = session.createObjectMessage();
            obj.setJMSExpiration(0L);
            
            EventoSalida evento = new EventoSalida(serviceName,methodName,data);
            obj.setObject(evento); 
            mp.send( obj );
            mp.close();
            session.close();
            con.close();            
        }catch(JMSException e){
            throw new IdeamException(e);
        }
    }         
}
