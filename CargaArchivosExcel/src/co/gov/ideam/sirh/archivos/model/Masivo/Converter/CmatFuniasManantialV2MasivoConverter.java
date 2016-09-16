package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;

public class CmatFuniasManantialV2MasivoConverter extends ModelConverter {
    public CmatFuniasManantialV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatFuniasManantialV2) {
            return this.getCmatFuniasManantialV2((CmatFuniasManantialV2)model);
        }
        return null;
    }

    private CmatFuniasManantialV2 getCmatFuniasManantialV2(CmatFuniasManantialV2 cmatfuniasmanantialv2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();
        return cmatfuniasmanantialv2;
    }
}
