package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasPersNatCaV2MasivoConverter extends ModelConverter {
    public CmatFuniasPersNatCaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasPersNatCaV2) {
            return this.getCmatFuniasPersNatCaV2((CmatFuniasPersNatCaV2)model);
        }
        return null;
    }

    private CmatFuniasPersNatCaV2 getCmatFuniasPersNatCaV2(CmatFuniasPersNatCaV2 cmatfuniaspersnatcav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniaspersnatcav2;
    }
}
