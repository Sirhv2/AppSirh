package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaNaturalCaV1MasivoConverter extends ModelConverter {
    public CmatPersonaNaturalCaV1MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaNaturalCaV1) {
            return this.getCmatPersonaNaturalCaV1((CmatPersonaNaturalCaV1)model);
        }
        return null;
    }

    private CmatPersonaNaturalCaV1 getCmatPersonaNaturalCaV1(CmatPersonaNaturalCaV1 cmatpersonanaturalcav1) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonanaturalcav1;
    }
}
