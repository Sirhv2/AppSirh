package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatControlCargueV1;
import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatControlCargueV2MasivoConverter extends ModelConverter {

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatControlCargueV2) {
            return this.getCmatControlCargueV2((CmatControlCargueV2)model);
        }
        return null;
    }

    private CmatControlCargueV2 getCmatControlCargueV2(CmatControlCargueV2 cargueControlV2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cargueControlV2;
    }
}



