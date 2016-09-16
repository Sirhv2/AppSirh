package co.gov.ideam.sirh.dataimport;

import co.gov.ideam.sirh.dataimport.client.DataSirh;
import co.gov.ideam.sirh.dataimport.client.Exception;
import co.gov.ideam.sirh.dataimport.client.ImportDatosSirh;
import co.gov.ideam.sirh.dataimport.client.generate.FntvFuentesTramos;
import co.gov.ideam.sirh.dataimport.client.generate.MuestrasMediciones;
import co.gov.ideam.sirh.dataimport.client.generate.PuntosMonitoreoCalidad;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoVertimiento;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalConcesion;

import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalVertimiento;

import co.gov.ideam.sirh.util.IdeamException;

//Cliente CORPOCALDAS
import co.gov.corpocaldas.cliente.DataSirhSoap;
import co.gov.corpocaldas.cliente.DataSirhSoapClient;
import co.gov.corpocaldas.generated.ArrayOfFntvFuentesTramos;

import java.net.URL;

import java.util.List;

import javax.persistence.Query;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;

import org.opensaml.xml.io.UnmarshallingException;

public class ImportProxy {

    @WebServiceRef
    private static ImportDatosSirh importDatosSirh;
    private static co.gov.corpocaldas.cliente.DataSirh dataSirh21;

    /**
     * Este metodo se usa para cargar toda la información relacionada a las
     * fuentes: fuente y tramo, georeferenciación del tramo.
     *
     * */
    public static List<FntvFuentesTramos> getAllFuentes(Integer idAutoridad) throws Exception,
                                                                                    IdeamException {
        List<FntvFuentesTramos> fuentesTramos = null;
        System.out.println("Autoridad getAllFUENTES: " +
                           idAutoridad.toString());
        switch (idAutoridad) {
        case 7:
            System.out.println("CAR consume desde corporacion 7: " +
                               idAutoridad.toString());
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh = importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            fuentesTramos = dataSirh.getAllFuentes();
            
            break;

        case 15:
            try {
                System.out.println("CORANTIOQUIA consume  Fuentes_Proxy: " +
                                   idAutoridad.toString());
                importDatosSirh =
                        new ImportDatosSirh(new URL("http://200.35.34.179:18090/WebServiceCAR/ImportDatosSirh/DataSirh?wsdl"),
                                            new QName("http://servicio.sirh.corantioquia.gov.co/",
                                                      "ImportDatosSirh"));

                DataSirh dataSirh15 = importDatosSirh.getDataSirhPort("http://servicio.sirh.corantioquia.gov.co/");
                fuentesTramos = dataSirh15.getAllFuentes();
                System.out.println("CORANTIOQUIA Fuentes_Proxy: " +
                                   fuentesTramos.size());
            } catch (MalformedURLException e) {
                System.out.println("ERROR en WS: " + e.getMessage());
                throw new IdeamException(e.getMessage());
            }
            break;

        }
        return fuentesTramos;
    }

    /**  SOLO PARA CORPORCALDAS
     * Este metodo se usa para cargar toda la información relacionada a las
     * fuentes: fuente y tramo, georeferenciación del tramo.
     *
     * */

    public static List<co.gov.corpocaldas.generated.FntvFuentesTramos> getAllFuentesCorpocaldas() throws Exception {
        List<co.gov.corpocaldas.generated.FntvFuentesTramos> fuentesTramos21 =
            null;

        System.out.println("CORPOCALDAS consume  Fuentes_Proxy.....");

        dataSirh21 = new co.gov.corpocaldas.cliente.DataSirh();

        System.out.println("dataSirh21 NEW: ");
        DataSirhSoap dataSirhSoap = dataSirh21.getDataSirhSoap();

        System.out.println("OBJ_dataSirhSoap: " + dataSirhSoap);

        ArrayOfFntvFuentesTramos result = new ArrayOfFntvFuentesTramos();
        result = dataSirhSoap.getAllFuentes();

        System.out.println("CORPOCALDAS dataSirhSoap.getAllFuentes(): " +
                           result.getFntvFuentesTramos());

        fuentesTramos21 = result.getFntvFuentesTramos();

        System.out.println("CORPOCALDAS Fuentes_Proxy: " +
                           fuentesTramos21.size());

        return fuentesTramos21;
    }

    /**
     * Este metodo se usa para cargar toda la información relacionada a los
     * Usuarios del Agua Persona Natural para Concesiones y Captaciones
     *
     * */
    public static List<RegUserNaturalConcesion> getAllUsuariosNaturalConcesion(Integer idAutoridad) throws Exception,
                                                                                                           IdeamException {
        List<RegUserNaturalConcesion> usersConcesionNatural = null;
        System.out.println("Autoridad getAllUsuariosNaturalConcesion: " +
                           idAutoridad.toString());
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            usersConcesionNatural = dataSirh.getAllUsuariosNaturalConcesion();
            break;

        case 15:
            System.out.println("CORANTIOQUIA consume usersConcesionNatural_Proxy: " +
                               idAutoridad.toString());
            try {
                importDatosSirh =
                        new ImportDatosSirh(new URL("http://200.35.34.179:18090/WebServiceCAR/ImportDatosSirh/DataSirh?wsdl"),
                                            new QName("http://servicio.sirh.corantioquia.gov.co/",
                                                      "ImportDatosSirh"));

                DataSirh dataSirh15 =
                    importDatosSirh.getDataSirhPort("http://servicio.sirh.corantioquia.gov.co/");
                System.out.println("DATASIRH15 OBJ " + dataSirh15);
                usersConcesionNatural =
                        dataSirh15.getAllUsuariosNaturalConcesion();
                System.out.println("CORANTIOQUIA usersConcesionNatural_Proxy: " +
                                   usersConcesionNatural.size());
            } catch (MalformedURLException e) {
                System.out.println("ERROR en WS: " + e.getMessage());
                throw new IdeamException(e.getMessage());
            }
            break;

        case 21:
            System.out.println("CORPOCALDAS consume usersConcesionNatural_Proxy: " +
                               idAutoridad.toString());
            /*
            try{
                importDatosSirh = new ImportDatosSirh(new URL("http://190.0.61.202:1780/WebServiceCAR/ImportDatosSirh/DataSirh.asmx?wsdl"),
                                                      new QName("WebServiceCAR", "ImportDatosSirh"));

              DataSirh dataSirh15 = importDatosSirh.getDataSirhPort("WebServiceCAR");
                System.out.println("DATASIRH15 OBJ "+dataSirh15);
              usersConcesionNatural = dataSirh15.getAllUsuariosNaturalConcesion();
          System.out.println("CORPOCALDAS usersConcesionNatural_Proxy: "+usersConcesionNatural.size());
            } catch(MalformedURLException e){
                 System.out.println("ERROR en WS: "+e.getMessage());
                 throw new IdeamException(e.getMessage());
            }
*/
            break;
        }
        return usersConcesionNatural;

    }

    /**
     * Este metodo se usa para cargar toda la información relacionada a los
     * Usuarios del Agua Persona Juridica para Concesiones y Captaciones
     *
     * */
    public static List<RegUserJuridicoConcesion> getAllUsuariosJuridicoConcesion(Integer idAutoridad) throws Exception {
        List<RegUserJuridicoConcesion> usersConcesionJuridico = null;
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            usersConcesionJuridico =
                    dataSirh.getAllUsuariosJuridicoConcesion();
            break;
        }
        return usersConcesionJuridico;
    }


    /**
     * Este metodo se usa para cargar toda la información relacionada a los
     * Usuarios del Agua Persona Natural para Permisos de Vertimientos
     *
     * */
    public static List<RegUserNaturalVertimiento> getAllUsuariosNaturalVertimiento(Integer idAutoridad) throws Exception {
        List<RegUserNaturalVertimiento> userVertimientoNatural = null;
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            userVertimientoNatural =
                    dataSirh.getAllUsuariosNaturalVertimiento();
            break;
        }
        return userVertimientoNatural;
    }

    /**
     * Este metodo se usa para cargar toda la información relacionada a los
     * Usuarios del Agua Persona Juridica para Permisos de Vertimientos
     *
     * */
    public static List<RegUserJuridicoVertimiento> getAllUsuariosJuridicoVertimiento(Integer idAutoridad) throws Exception {
        List<RegUserJuridicoVertimiento> userVertimientoJuridico = null;
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            userVertimientoJuridico =
                    dataSirh.getAllUsuariosJuridicoVertimiento();
            break;
        }
        return userVertimientoJuridico;
    }

    /**
     * Este metodo se usa para cargar toda la información relacionada a los
     * Puntos de Monitoreo de Calidad
     *
     * */
    public static List<PuntosMonitoreoCalidad> getAllPuntosMonitoreo(Integer idAutoridad) throws Exception {
        List<PuntosMonitoreoCalidad> puntosMonitoreo = null;
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            puntosMonitoreo = dataSirh.getAllPuntosMonitoreo();
            break;
        }
        return puntosMonitoreo;
    }

    /**
     * Este metodo se usa para cargar toda la información relacionada a las
     * Mediciones de Calidad
     *
     * */
    public static List<MuestrasMediciones> getAllMediciones(Integer idAutoridad) throws Exception {
        List<MuestrasMediciones> muestrasMediciones = null;
        switch (idAutoridad) {
        case 7:
            importDatosSirh = new ImportDatosSirh();
            DataSirh dataSirh =
                importDatosSirh.getDataSirhPort("http://servicio.sirh.ideam.gov.co/");
            muestrasMediciones = dataSirh.getAllMediciones();
            break;
        }
        return muestrasMediciones;
    }
}

