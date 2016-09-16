package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatRegistroFuentesV2MasivoConverter extends ModelConverter {
    public CmatRegistroFuentesV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatRegistroFuentesV2) {
            return this.getCmatRegistroFuentesV2((CmatRegistroFuentesV2)model);
        }
        return null;
    }

    private CmatRegistroFuentesV2 getCmatRegistroFuentesV2(CmatRegistroFuentesV2 cmatregistrofuentesv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatregistrofuentesv2;
    }
}

