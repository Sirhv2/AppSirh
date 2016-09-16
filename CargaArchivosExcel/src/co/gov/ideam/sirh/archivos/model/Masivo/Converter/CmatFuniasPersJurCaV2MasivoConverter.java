package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasPersJurCaV2MasivoConverter extends ModelConverter {
    public CmatFuniasPersJurCaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasPersJurCaV2) {
            return this.getCmatFuniasPersJurCaV2((CmatFuniasPersJurCaV2)model);
        }
        return null;
    }

    private CmatFuniasPersJurCaV2 getCmatFuniasPersJurCaV2(CmatFuniasPersJurCaV2 cmatfuniaspersjurcav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniaspersjurcav2;
    }
}
