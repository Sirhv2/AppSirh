package co.gov.ideam.sirh.archivos.model.Masivo.Converter;

import co.gov.ideam.sirh.archivos.model.ModelConverter;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.archivos.model.Masivo.Entidad.*;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.ConstantesCalidad;

public class CmatPuntosMonitoreoV2MasivoConverter extends ModelConverter {
    public CmatPuntosMonitoreoV2MasivoConverter() {
        super();
    }

    public Object getModel(Object model) throws IdeamException {
        if (model instanceof CmatPuntosMonitoreoV2) {
            return this.getCmatPuntosMonitoreoV2((CmatPuntosMonitoreoV2)model);
        }
        return null;
    }

    private CmatPuntosMonitoreoV2 getCmatPuntosMonitoreoV2(CmatPuntosMonitoreoV2 cmatpuntosmonitoreov2) throws IdeamException {
        ParametrosEJB parametrosService = this.getParametrosService();

        //id control cargue
        //Codigo de rechazo
        //Id_Registro

        cmatpuntosmonitoreov2.setNombrePunto(row.getCellValue("Nombre Punto").toString());
        cmatpuntosmonitoreov2.setDescripcionAcceso(row.getCellValue("Descripcion de Acceso").toString());

        String tipoPuntoTxt = row.getCellValue("Tipo Punto").toString();
        Lista tipoPunto =
            parametrosService.getListaByDescripcion(tipoPuntoTxt, ConstantesCalidad.TIPOS_PUNTO_MONITOREO);

        cmatpuntosmonitoreov2.setTipoPunto(tipoPuntoTxt);
        //cmatpuntosmonitoreov2.setIdTipo_punto(tipoPunto.getCodigo());


        String ubicacionTxt = row.getCellValue("Ubicacion").toString();
        Lista ubicacion =
            parametrosService.getListaByDescripcion(ubicacionTxt, ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
        cmatpuntosmonitoreov2.setUbicacion(ubicacionTxt);
        //cmatpuntosmonitoreov2.setIdubicacion(ubicacion.getCodigo());


        String sistRefTxt = row.getCellValue("Sistema Referencia").toString();
        Lista sistRef =
            parametrosService.getListaByDescripcion(sistRefTxt, ConstantesCalidad.SISTEMA_REFERENCIA);
        cmatpuntosmonitoreov2.setSistemaReferencia(sistRefTxt);
        //cmatpuntosmonitoreov2.setIdSist_ref(sistRef.getCodigo());


        String nombreDeptoTxt =
            this.row.getCellValue("Departamento").toString();
        Divipola depto =
            parametrosService.getDivipolaByName(nombreDeptoTxt, "DEP");
        cmatpuntosmonitoreov2.setDepartamento(nombreDeptoTxt);
        //cmatpuntosmonitoreov2.setIdDepartamento(depto.getId());

        String nombreMunTxt = this.row.getCellValue("Municipio").toString();

        Divipola mun =
            parametrosService.getDivipolaByName(nombreMunTxt, depto);
        cmatpuntosmonitoreov2.setMunicipio(nombreMunTxt);
        //cmatpuntosmonitoreov2.setIdmunicipio(mun.getId());


        String nombreArea = row.getCellValue("Area").toString();
        PartZonificListas area =
            parametrosService.getZonificacionByName(nombreArea, null);
        cmatpuntosmonitoreov2.setArea(nombreArea);
        //cmatpuntosmonitoreov2.setaIdArea(nombreArea);

        String nombreZona = row.getCellValue("Zona").toString();
        PartZonificListas zona =
            parametrosService.getZonificacionByName(nombreZona, area);
        if (zona == null) {
            throw new IdeamException("No es posible encontrar una Zona con nombre " +
                                     nombreZona + ", asociada al area " +
                                     nombreArea);
        }
        cmatpuntosmonitoreov2.setZona(nombreZona);

        String nombreSubzona = row.getCellValue("Subzona").toString();
        PartZonificListas subZona =
            parametrosService.getZonificacionByName(nombreSubzona, zona);
        if (subZona == null) {
            throw new IdeamException("No es posible encontrar una Subzona con nombre " +
                                     nombreSubzona + ", asociada a la zona " +
                                     nombreZona);
        }
        cmatpuntosmonitoreov2.setSubzona(nombreSubzona);


        cmatpuntosmonitoreov2.setTipo(row.getCellValue("Tipo").toString());
        cmatpuntosmonitoreov2.setNombrePunto(row.getCellValue("Fuente").toString());
        cmatpuntosmonitoreov2.setTramo(row.getCellValue("Tramo").toString());

        String alt = this.row.getCellValue("Altitud").toString();
        //alt = replaceFloatingSeparator(alt);
        cmatpuntosmonitoreov2.setAltitud(alt);


        cmatpuntosmonitoreov2.setGradosLatitud("Grados Latitud");
        cmatpuntosmonitoreov2.setMinutosLatitud("Minutos Latitud");

        String segundosLat =
            this.row.getCellValue("Segundos Latitud").toString();
        //segundosLat = replaceFloatingSeparator(segundosLat);
        cmatpuntosmonitoreov2.setSegundosLatitud(segundosLat);

        cmatpuntosmonitoreov2.setGradosLongitud("Grados Longitud");
        cmatpuntosmonitoreov2.setMinutosLongitud("Minutos Longitud");

        String segundosLong =
            this.row.getCellValue("Segundos Longitud").toString();
        //segundosLong = replaceFloatingSeparator(segundosLong);
        cmatpuntosmonitoreov2.setSegundosLongitud(segundosLong);
        return cmatpuntosmonitoreov2;
    }
}

