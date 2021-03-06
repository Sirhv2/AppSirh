package co.gov.ideam.sirh.remoto.sender.modelo;

import co.gov.ideam.sirh.remoto.sender.modelo.types.ObjectFactory;
import co.gov.ideam.sirh.remoto.sender.modelo.types.RecibirEvento;
import co.gov.ideam.sirh.remoto.sender.modelo.types.RecibirEventoResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 081105.0700.56405)

@WebService(wsdlLocation="receiver.wsdl", targetNamespace="http://sirh.ideam.gov.co/",
  name="RegistroRemotoSirh")
@XmlSeeAlso(
  { ObjectFactory.class })
@SOAPBinding(style=Style.DOCUMENT, parameterStyle=ParameterStyle.BARE)
public interface RegistroRemotoSirh
{
  @WebMethod
  @SOAPBinding(parameterStyle=ParameterStyle.BARE)
  @Action(input="", fault =
      { @FaultAction(value="http://sirh.ideam.gov.co/RegistroRemotoSirh/recibirEvento/Fault/IdeamException",
          className = IdeamException.class)
        } , output="")
  @WebResult(targetNamespace="http://sirh.ideam.gov.co/", partName="parameters",
    name="recibirEventoResponse")
  public RecibirEventoResponse recibirEvento(@WebParam(targetNamespace="http://sirh.ideam.gov.co/",
      partName="parameters", name="recibirEvento")
    RecibirEvento parameters)
    throws IdeamException;
}
