package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatSubteRegCuerpoAguaV2MasivoConverter extends ModelConverter {
    public CmatSubteRegCuerpoAguaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatSubteRegCuerpoAguaV2) {
            return this.getCmatSubteRegCuerpoAguaV2((CmatSubteRegCuerpoAguaV2)model);
        }
        return null;
    }

    private CmatSubteRegCuerpoAguaV2 getCmatSubteRegCuerpoAguaV2(CmatSubteRegCuerpoAguaV2 cmatsubteregcuerpoaguav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatsubteregcuerpoaguav2;
    }
}
