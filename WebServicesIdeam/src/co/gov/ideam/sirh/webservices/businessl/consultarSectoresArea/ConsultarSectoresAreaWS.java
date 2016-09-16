package co.gov.ideam.sirh.webservices.businessl.consultarSectoresArea;

import co.gov.ideam.sirh.webservices.model.consultarSectoresArea.OS_ConsultarSectoresArea;
import javax.ejb.Remote;

@Remote
public interface ConsultarSectoresAreaWS {
    public OS_ConsultarSectoresArea consultarSectoresArea();
}

