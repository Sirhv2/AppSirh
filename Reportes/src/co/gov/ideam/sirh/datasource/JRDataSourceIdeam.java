package co.gov.ideam.sirh.datasource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
/**
 * Data source utilizado para llenar un reporte con base en ls objetos 
 * que se reciben en una lista.
 */
public class JRDataSourceIdeam implements JRDataSource{
    private List data;
    private int indiceActual = -1;

    /**
     * DataSource para llenar los reportes con la informacin recibida
     * como parametro.
     * @param data
     */
    public JRDataSourceIdeam(List data){
        this.data = data;
    }
    
    /**
     * Retorna un valor boolean que indica si hay o no mas objetos en la
     * lista.
     * @return boolean
     * @throws net.sf.jasperreports.engine.JRException
     */
    public boolean next() throws JRException {
        if (data == null){
            return false;
        }
        indiceActual++;
        return (indiceActual < data.size());
    }

    /**
     * Retorna un valor del objeto que viene en la lista, utiliza reflexion
     * para ejecutar el metodo getXXXX correspondiente.
     * @param field
     * @return Object
     * @throws net.sf.jasperreports.engine.JRException
     */
    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;
        String atributo = field.getName();
        String nombreAtributo = "get" + atributo.substring(0,1).toUpperCase()
                + atributo.substring(1);

        Object objData = this.data.get(indiceActual);
        if (objData == null){
            throw new JRException("No existe un registro con indice " + indiceActual);
        } 

        try {
            Method metodo = objData.getClass().getMethod(nombreAtributo, null);
            Object retorno = metodo.invoke(objData, null);
            return retorno;
        } catch (NoSuchMethodException ex) {
            throw new JRException(ex.getMessage());
        } catch (SecurityException ex) {
            throw new JRException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            throw new JRException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new JRException(ex.getMessage());
        } catch (InvocationTargetException ex) {
            throw new JRException(ex.getMessage());
        }
    }
}
