package co.gov.ideam.sirh.dataimport.client;

import co.gov.ideam.sirh.dataimport.client.generate.FntvFuentesTramos;
import co.gov.ideam.sirh.dataimport.client.generate.MuestrasMediciones;
import co.gov.ideam.sirh.dataimport.client.generate.ObjectFactory;
import co.gov.ideam.sirh.dataimport.client.generate.PuntosMonitoreoCalidad;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserJuridicoVertimiento;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalConcesion;
import co.gov.ideam.sirh.dataimport.client.generate.RegUserNaturalVertimiento;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 081105.0700.56405)

@WebService(wsdlLocation =
            "http://localhost:18081/WebServiceCAR/ImportDatosSirh/DataSirh?wsdl",
            targetNamespace = "http://servicio.sirh.ideam.gov.co/",
            name = "DataSirh")
@XmlSeeAlso( { ObjectFactory.class })
public interface DataSirh {
    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalConcesion",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalConcesion/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalConcesionResponse")
    @ResponseWrapper(localName = "getAllUsuariosNaturalConcesionResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosNaturalConcesionResponse")
    @RequestWrapper(localName = "getAllUsuariosNaturalConcesion",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosNaturalConcesion")
    @WebResult(targetNamespace = "")
    public List<RegUserNaturalConcesion> getAllUsuariosNaturalConcesion() throws Exception;

    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllPuntosMonitoreo",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllPuntosMonitoreo/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllPuntosMonitoreoResponse")
    @ResponseWrapper(localName = "getAllPuntosMonitoreoResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllPuntosMonitoreoResponse")
    @RequestWrapper(localName = "getAllPuntosMonitoreo",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllPuntosMonitoreo")
    @WebResult(targetNamespace = "")
    public List<PuntosMonitoreoCalidad> getAllPuntosMonitoreo() throws Exception;

    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoVertimiento",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoVertimiento/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoVertimientoResponse")
    @ResponseWrapper(localName = "getAllUsuariosJuridicoVertimientoResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosJuridicoVertimientoResponse")
    @RequestWrapper(localName = "getAllUsuariosJuridicoVertimiento",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosJuridicoVertimiento")
    @WebResult(targetNamespace = "")
    public List<RegUserJuridicoVertimiento> getAllUsuariosJuridicoVertimiento() throws Exception;

    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllMediciones",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllMediciones/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllMedicionesResponse")
    @ResponseWrapper(localName = "getAllMedicionesResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllMedicionesResponse")
    @RequestWrapper(localName = "getAllMediciones",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllMediciones")
    @WebResult(targetNamespace = "")
    public List<MuestrasMediciones> getAllMediciones() throws Exception;

    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoConcesion",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoConcesion/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosJuridicoConcesionResponse")
    @ResponseWrapper(localName = "getAllUsuariosJuridicoConcesionResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosJuridicoConcesionResponse")
    @RequestWrapper(localName = "getAllUsuariosJuridicoConcesion",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosJuridicoConcesion")
    @WebResult(targetNamespace = "")
    public List<RegUserJuridicoConcesion> getAllUsuariosJuridicoConcesion() throws Exception;

    @WebMethod
    @Action(input = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllFuentes",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllFuentes/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllFuentesResponse")
    @ResponseWrapper(localName = "getAllFuentesResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllFuentesResponse")
    @RequestWrapper(localName = "getAllFuentes",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllFuentes")
    @WebResult(targetNamespace = "")
    public List<FntvFuentesTramos> getAllFuentes() throws Exception;

    @WebMethod
    @Action(input =
            "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalVertimiento",
            fault =
            { @FaultAction(value = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalVertimiento/Fault/Exception",
                           className = Exception.class)
                } ,
        output = "http://servicio.sirh.ideam.gov.co/DataSirh/getAllUsuariosNaturalVertimientoResponse")
    @ResponseWrapper(localName = "getAllUsuariosNaturalVertimientoResponse",
                     targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                     className =
                     "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosNaturalVertimientoResponse")
    @RequestWrapper(localName = "getAllUsuariosNaturalVertimiento",
                    targetNamespace = "http://servicio.sirh.ideam.gov.co/",
                    className =
                    "co.gov.ideam.sirh.dataimport.client.generate.GetAllUsuariosNaturalVertimiento")
    @WebResult(targetNamespace = "")
    public List<RegUserNaturalVertimiento> getAllUsuariosNaturalVertimiento() throws Exception;
}
