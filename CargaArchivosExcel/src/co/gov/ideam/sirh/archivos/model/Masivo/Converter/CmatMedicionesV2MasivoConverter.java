package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatMedicionesV2MasivoConverter extends ModelConverter {
    public CmatMedicionesV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatMedicionesV2) {
            return this.getCmatMedicionesV2((CmatMedicionesV2)model);
        }
        return null;
    }

    private CmatMedicionesV2 getCmatMedicionesV2(CmatMedicionesV2 cmatmedicionesv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatmedicionesv2;
    }
}
