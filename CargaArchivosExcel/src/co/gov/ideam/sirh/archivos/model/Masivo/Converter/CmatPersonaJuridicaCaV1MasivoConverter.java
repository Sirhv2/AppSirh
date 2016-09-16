package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaJuridicaCaV1MasivoConverter extends ModelConverter {
    public CmatPersonaJuridicaCaV1MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaJuridicaCaV1) {
            return this.getCmatPersonaJuridicaCaV1((CmatPersonaJuridicaCaV1)model);
        }
        return null;
    }

    private CmatPersonaJuridicaCaV1 getCmatPersonaJuridicaCaV1(CmatPersonaJuridicaCaV1 cmatpersonajuridicacav1) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonajuridicacav1;
    }
}
