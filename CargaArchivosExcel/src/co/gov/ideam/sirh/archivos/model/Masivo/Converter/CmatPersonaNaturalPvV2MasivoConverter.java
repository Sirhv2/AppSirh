package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaNaturalPvV2MasivoConverter extends ModelConverter {
    public CmatPersonaNaturalPvV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaNaturalPvV2) {
            return this.getCmatPersonaNaturalPvV2((CmatPersonaNaturalPvV2)model);
        }
        return null;
    }

    private CmatPersonaNaturalPvV2 getCmatPersonaNaturalPvV2(CmatPersonaNaturalPvV2 cmatpersonanaturalpvv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonanaturalpvv2;
    }
}
