package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;

import java.io.Serializable;

import java.math.BigDecimal;

public class FuentesConverter extends ModelConverter{
    public FuentesConverter() {
        super();
    }

    public Object getModel(Object model)throws IdeamException {
        if(model instanceof FnttFuente){
            return this.getFuente( (FnttFuente)model );
        }else if(model instanceof FnttTramo){
            return this.getTramo( (FnttTramo)model );
        }
        return null;
    }
    private FnttTramo getTramo(FnttTramo tramo)throws IdeamException{
        System.out.println("--------------------------- Procesando tramo   ----------------------------");
        ParametrosEJB parametrosService = this.getParametrosService();

        tramo.setNombre( row.getCellValue("Nombre Tramo").toString().toUpperCase().trim() );
        tramo.setDescripcion( row.getCellValue("Descripcion Tramo").toString() );


        String longitud= this.row.getCellValue("Longitud").toString();
        System.out.println("longitud del tramo:"+longitud);
        longitud = replaceFloatingSeparator(longitud);
        tramo.setLongitud( new BigDecimal(longitud) );
        System.out.println("longitud del tramo:"+tramo.getLongitud());

        String tipoFlujoTxt= row.getCellValue("Flujo").toString();
        Lista tipoFlujo = parametrosService.getListaByDescripcion(
                           tipoFlujoTxt, 13L);
        tramo.setTipoFlujo(tipoFlujo);

        String nombreArea = row.getCellValue("Area").toString();
        PartZonificListas area = parametrosService.getZonificacionByName(
                              nombreArea, null);
        tramo.setIdArea(area);

        String nombreZona = row.getCellValue("Zona").toString();
        PartZonificListas zona = parametrosService.getZonificacionByName(
                                     nombreZona, area);
        if(zona==null){
            throw new IdeamException("No es posible encontrar una Zona con nombre " +
                                     nombreZona + ", asociada al area " + nombreArea);
        }
        tramo.setIdZona(zona);

        String nombreSubzona = row.getCellValue("Subzona").toString();
        PartZonificListas subZona = parametrosService.getZonificacionByName(
                                        nombreSubzona, zona);
        if(subZona==null){
            throw new IdeamException("No es posible encontrar una Subzona con nombre " +
                                     nombreSubzona + ", asociada a la zona " + nombreZona);
        }
        tramo.setIdSubzona(subZona);

        String nombreSistema = row.getCellValue("Sistema Referencia Inicial").toString();
        Lista sistemaReferencia = parametrosService.getListaByDescripcion(
                                      nombreSistema, 6L);
        tramo.setSistemaReferencia(sistemaReferencia);

        tramo.setGradosPi( this.parseValor("Grados Latitud Inicial") );
        tramo.setMinutosPi( this.parseValor("Minutos Latitud Inicial") );
        //tramo.setSegundosPi( new BigDecimal(this.parseValor("Segundos Longitud Inicial")) );
        String segundosLatPi = this.row.getCellValue("Segundos Latitud Inicial").toString();
        segundosLatPi = replaceFloatingSeparator(segundosLatPi);
        tramo.setSegundosPi(new BigDecimal(segundosLatPi));

        tramo.setGradosLongPi( this.parseValor("Grados Longitud Inicial") );
        tramo.setMinutosLongPi( this.parseValor("Minutos Longitud Inicial") );
        //tramo.setSegundosPf( new BigDecimal( this.parseValor("Segundos Longitud Inicial") ) );
        String segundosLongPi= this.row.getCellValue("Segundos Longitud Inicial").toString();
        segundosLongPi = replaceFloatingSeparator(segundosLongPi);
        tramo.setSegundosLongPi(new BigDecimal(segundosLongPi));

        String altitudInicial= this.row.getCellValue("Altitud Inicial").toString();
        altitudInicial = replaceFloatingSeparator(altitudInicial);
        tramo.setAltitudPi( new BigDecimal(altitudInicial) );


        tramo.setGradosPf( this.parseValor("Grados Latitud Final") );
        tramo.setMinutosPf( this.parseValor("Minutos Latitud Final") );
        //tramo.setSegundosPi( new BigDecimal(this.parseValor("Segundos Longitud Inicial")) );
        String segundosLatPf= this.row.getCellValue("Segundos Latitud Final").toString();
        segundosLatPf = replaceFloatingSeparator(segundosLatPf);
        tramo.setSegundosPf(new BigDecimal(segundosLatPf));

        tramo.setGradosLongPf( this.parseValor("Grados Longitud Final") );
        tramo.setMinutosLongPf( this.parseValor("Minutos Longitud Final") );
        //tramo.setSegundosPf( new BigDecimal( this.parseValor("Segundos Longitud Inicial") ) );
        String segundosLongPf= this.row.getCellValue("Segundos Longitud Final").toString();
        segundosLongPf = replaceFloatingSeparator(segundosLongPf);
        tramo.setSegundosLongPf(new BigDecimal(segundosLongPf));

        String altitudFinal = this.row.getCellValue("Altitud Final").toString();
        altitudFinal = replaceFloatingSeparator(altitudFinal);
        tramo.setAltitudPf( new BigDecimal( altitudFinal ) );
        return tramo;
    }

    private FnttFuente getFuente(FnttFuente fuente)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();

        String nombreTipo = row.getCellValue("Tipo").toString();
        Lista tipoFuente = parametrosService.
            getListaByDescripcion(nombreTipo, 12L);

        if(tipoFuente==null){
            throw new IdeamException("No existe el tipo de fuente " +
                                     nombreTipo);
        }
        fuente.setIdTipoFuente(tipoFuente);
        fuente.setNombre( row.getCellValue("Nombre").toString().toUpperCase().trim());
        fuente.setDescripcion( row.getCellValue("Descripcion").toString() );
        if(row.getCellValue("NumCAR").toString().length()>0){
        fuente.setCodigoCuencaAA( row.getCellValue("NumCAR").toString() );
        }
        fuente.setEsCompartida(Constantes.FALSE_TO_INTEGER);
        return fuente;
    }

    private int parseValor(String nombreColumna){
        String texto =
            row.getCellValue(nombreColumna).toString();
        Double valor = Double.parseDouble( texto );
        return valor.intValue();
    }

    private String replaceFloatingSeparator(String tmp){
        if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
            if(tmp.indexOf(",")!=-1)
            tmp = tmp.replaceAll(",",".");
        }else{
            tmp=null;
        }
        return tmp;
    }
}
