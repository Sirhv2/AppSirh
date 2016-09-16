package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasExplotacionV2MasivoConverter extends ModelConverter {
    public CmatFuniasExplotacionV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasExplotacionV2) {
            return this.getCmatFuniasExplotacionV2((CmatFuniasExplotacionV2)model);
        }
        return null;
    }

    private CmatFuniasExplotacionV2 getCmatFuniasExplotacionV2(CmatFuniasExplotacionV2 cmatfuniasexplotacionv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasexplotacionv2;
    }
}
