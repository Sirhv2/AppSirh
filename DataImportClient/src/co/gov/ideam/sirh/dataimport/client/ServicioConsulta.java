package co.gov.ideam.sirh.dataimport.client;

import co.gov.ideam.sirh.dataimport.client.generate.FntvFuentesTramos;
import co.gov.ideam.sirh.dataimport.client.generate.ObjectFactory;

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

@WebService(wsdlLocation="ImportDatosSirh.wsdl", targetNamespace="http://servicio.sirh.ideam.gov.co/",
  name="ServicioConsulta")
@XmlSeeAlso(
  { ObjectFactory.class })
public interface ServicioConsulta
{
  @WebMethod
  @Action(input="http://servicio.sirh.ideam.gov.co/ServicioConsulta/getAllFuentes", fault =
      { @FaultAction(value="http://servicio.sirh.ideam.gov.co/ServicioConsulta/getAllFuentes/Fault/Exception",
          className = Exception.class)
        } , output="http://servicio.sirh.ideam.gov.co/ServicioConsulta/getAllFuentesResponse")
  @ResponseWrapper(localName="getAllFuentesResponse", targetNamespace="http://servicio.sirh.ideam.gov.co/",
    className="co.gov.ideam.sirh.dataimport.client.generate.GetAllFuentesResponse")
  @RequestWrapper(localName="getAllFuentes", targetNamespace="http://servicio.sirh.ideam.gov.co/",
    className="co.gov.ideam.sirh.dataimport.client.generate.GetAllFuentes")
  @WebResult(targetNamespace="")
  public List<FntvFuentesTramos> getAllFuentes()
    throws Exception;
}