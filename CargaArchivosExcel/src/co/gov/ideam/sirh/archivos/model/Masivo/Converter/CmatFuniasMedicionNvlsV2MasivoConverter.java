package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasMedicionNvlsV2MasivoConverter extends ModelConverter {
    public CmatFuniasMedicionNvlsV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasMedicionNvlsV2) {
            return this.getCmatFuniasMedicionNvlsV2((CmatFuniasMedicionNvlsV2)model);
        }
        return null;
    }

    private CmatFuniasMedicionNvlsV2 getCmatFuniasMedicionNvlsV2(CmatFuniasMedicionNvlsV2 cmatfuniasmedicionnvlsv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasmedicionnvlsv2;
    }
}
