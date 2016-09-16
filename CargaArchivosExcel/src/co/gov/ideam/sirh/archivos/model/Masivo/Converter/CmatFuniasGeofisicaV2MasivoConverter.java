package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasGeofisicaV2MasivoConverter extends ModelConverter {
    public CmatFuniasGeofisicaV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasGeofisicaV2) {
            return this.getCmatFuniasGeofisicaV2((CmatFuniasGeofisicaV2)model);
        }
        return null;
    }

    private CmatFuniasGeofisicaV2 getCmatFuniasGeofisicaV2(CmatFuniasGeofisicaV2 cmatfuniasgeofisicav2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasgeofisicav2;
    }
}
