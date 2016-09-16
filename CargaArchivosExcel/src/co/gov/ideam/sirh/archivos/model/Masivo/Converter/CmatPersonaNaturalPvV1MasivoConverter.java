package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaNaturalPvV1MasivoConverter extends ModelConverter {
    public CmatPersonaNaturalPvV1MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaNaturalPvV1) {
            return this.getCmatPersonaNaturalPvV1((CmatPersonaNaturalPvV1)model);
        }
        return null;
    }

    private CmatPersonaNaturalPvV1 getCmatPersonaNaturalPvV1(CmatPersonaNaturalPvV1 cmatpersonanaturalpvv1) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonanaturalpvv1;
    }
}
