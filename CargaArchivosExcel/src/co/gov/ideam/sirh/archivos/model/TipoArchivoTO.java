package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

public class TipoArchivoTO implements Serializable{
    
    private String nombre;
    private Class validator;
    private Class converter;
    
    public TipoArchivoTO(String nombre, Class validator, Class converter) {
        this.nombre = nombre;
        this.setValidator(validator);
        this.setConverter(converter);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            obj instanceof TipoArchivoTO &&
            ((TipoArchivoTO)obj).getNombre() != null &&
            this.getNombre() != null &&
            ((TipoArchivoTO)obj).getNombre().equals( getNombre() );
    }

    public Class getValidator() {
        return validator;
    }

    public void setValidator(Class validator) {
        this.validator = validator;
    }
    
    public AbstractValidator getValidatorInstance()throws IdeamException{
        AbstractValidator validator = null;
        try{
            validator = (AbstractValidator)this.getValidator().newInstance();
            return validator;
        }catch (InstantiationException e) {
            throw new IdeamException("Error creando validator " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IdeamException("Error creando validator " + e.getMessage());
        }    
    }
    public ModelConverter getConverterInstance()throws IdeamException{
        ModelConverter converter = null;
        try{
            converter = (ModelConverter)this.getConverter().newInstance();
            return converter;
        }catch (InstantiationException e) {
            throw new IdeamException("Error creando converter " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IdeamException("Error creando converter " + e.getMessage());
        }    
    }
    public Class getConverter() {
        return converter;
    }

    public void setConverter(Class converter) {
        this.converter = converter;
    }
}
