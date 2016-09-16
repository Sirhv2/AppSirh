package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaJuridicaCaV2MasivoConverter extends ModelConverter {
    public CmatPersonaJuridicaCaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaJuridicaCaV2) {
            return this.getCmatPersonaJuridicaCaV2((CmatPersonaJuridicaCaV2)model);
        }
        return null;
    }

    private CmatPersonaJuridicaCaV2 getCmatPersonaJuridicaCaV2(CmatPersonaJuridicaCaV2 cmatpersonajuridicacav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonajuridicacav2;
    }
}
