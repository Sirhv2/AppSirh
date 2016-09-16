package co.gov.ideam.sirh.archivos.model.validator;

import java.io.Serializable;

/**
 * Metodos que deben ser implemntadops par validar lña informacion
 * encapculada en ColumnTO
 *
 * Todas las clases que implementen esta interfaz deben tener un metodo
 * constructor sin parametros
 */
public interface ColumnValidatorInterface {
    
    public boolean valid(Object value);
    
    public String getMensajeError();
    
}
