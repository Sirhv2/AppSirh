package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.CmatFuniasConstruccionV2;
import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;

public class CmatFuniasConstruccionV2MasivoConverter extends ModelConverter {
    public CmatFuniasConstruccionV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasConstruccionV2) {
            return this.getCmatFuniasConstruccionV2((CmatFuniasConstruccionV2)model);
        }
        return null;
    }

    private CmatFuniasConstruccionV2 getCmatFuniasConstruccionV2(CmatFuniasConstruccionV2 cmatfuniasconstruccionv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasconstruccionv2;
    }
}

