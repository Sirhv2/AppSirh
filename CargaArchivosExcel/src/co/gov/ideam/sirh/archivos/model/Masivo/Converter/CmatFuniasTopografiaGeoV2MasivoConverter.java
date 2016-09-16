package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasTopografiaGeoV2MasivoConverter extends ModelConverter {
    public CmatFuniasTopografiaGeoV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasTopografiaGeoV2) {
            return this.getCmatFuniasTopografiaGeoV2((CmatFuniasTopografiaGeoV2)model);
        }
        return null;
    }

    private CmatFuniasTopografiaGeoV2 getCmatFuniasTopografiaGeoV2(CmatFuniasTopografiaGeoV2 cmatfuniastopografiageov2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniastopografiageov2;
    }
}
