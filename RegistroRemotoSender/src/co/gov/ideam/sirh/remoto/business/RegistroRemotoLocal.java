package co.gov.ideam.sirh.remoto.business;

import co.gov.ideam.sirh.util.IdeamException;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface RegistroRemotoLocal {
        
    public void registrarEvento(String serviceName, String methodName, Object... data)throws IdeamException;
}
