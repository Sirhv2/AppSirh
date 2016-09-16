package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatPersonaJuridicaPvV2MasivoConverter extends ModelConverter {
    public CmatPersonaJuridicaPvV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPersonaJuridicaPvV2) {
            return this.getCmatPersonaJuridicaPvV2((CmatPersonaJuridicaPvV2)model);
        }
        return null;
    }

    private CmatPersonaJuridicaPvV2 getCmatPersonaJuridicaPvV2(CmatPersonaJuridicaPvV2 cmatpersonajuridicapvv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatpersonajuridicapvv2;
    }
}
