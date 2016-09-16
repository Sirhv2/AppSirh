package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaNaturalCaV2MasivoConverter extends ModelConverter {
    public CmatPersonaNaturalCaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaNaturalCaV2) {
            return this.getCmatPersonaNaturalCaV2((CmatPersonaNaturalCaV2)model);
        }
        return null;
    }

    private CmatPersonaNaturalCaV2 getCmatPersonaNaturalCaV2(CmatPersonaNaturalCaV2 cmatpersonanaturalcav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonanaturalcav2;
    }
}
