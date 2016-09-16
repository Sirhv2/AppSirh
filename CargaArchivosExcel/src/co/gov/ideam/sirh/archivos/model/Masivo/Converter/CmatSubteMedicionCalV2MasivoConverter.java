package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatSubteMedicionCalV2MasivoConverter extends ModelConverter {
    public CmatSubteMedicionCalV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatSubteMedicionCalV2) {
            return this.getCmatSubteMedicionCalV2((CmatSubteMedicionCalV2)model);
        }
        return null;
    }

    private CmatSubteMedicionCalV2 getCmatSubteMedicionCalV2(CmatSubteMedicionCalV2 cmatsubtemedicioncalv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatsubtemedicioncalv2;
    }
}
