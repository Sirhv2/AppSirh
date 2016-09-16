package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

/*
 Importaciones
 */
import co.gov.ideam.sirh.archivos.model.*;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * Clase encargada de convertir los datos recibidos al momento de realizar la carga
 */
public class CmatControlCargueV1MasivoConverter extends ModelConverter {
    //Constructor

    public CmatControlCargueV1MasivoConverter() {
        super();
    }
    //Metodo Hererado de ModelConverter

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatControlCargueV1) {
            return this.getCmatControlCargueV1((CmatControlCargueV1)model);
        }
        return null;
    }

    private CmatControlCargueV1 getCmatControlCargueV1(CmatControlCargueV1 cargueControlV1) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cargueControlV1;
    }
}
