package co.gov.ideam.sirh.webservices.businessl.consultarVertimientoXFuente;
import co.gov.ideam.sirh.webservices.model.consultarVertimientoXFuente.OE_ConsultarVertimientoXFuente;
import co.gov.ideam.sirh.webservices.model.consultarVertimientoXFuente.OS_ConsultarVertimientoXFuente;
import javax.ejb.Remote;

@Remote
public interface ConsultarVertimientoXFuenteWS {
    public OS_ConsultarVertimientoXFuente consultarVertimientoXFuente(OE_ConsultarVertimientoXFuente oe );
}

