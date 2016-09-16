package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasDiagSanitarioV2MasivoConverter extends ModelConverter {
    public CmatFuniasDiagSanitarioV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasDiagSanitarioV2) {
            return this.getCmatFuniasDiagSanitarioV2((CmatFuniasDiagSanitarioV2)model);
        }
        return null;
    }

    private CmatFuniasDiagSanitarioV2 getCmatFuniasDiagSanitarioV2(CmatFuniasDiagSanitarioV2 cmatfuniasdiagsanitariov2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasdiagsanitariov2;
    }
}
