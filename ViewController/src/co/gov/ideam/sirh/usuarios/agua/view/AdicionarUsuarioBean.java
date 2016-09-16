package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;

import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.EmailValidator;

import co.gov.ideam.sirh.view.util.NombreValidator;

import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

public class AdicionarUsuarioBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl12;
    private RichPanelGroupLayout pgl115;
    private RichPanelBox pb_paso_1;

    private List listaTiposUsuarios;
    private List listaTiposDocumento;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaClasificacionSuelo;
    private List listaSistemasReferencia;    
    private UsuarioAgua usuarioAgua;
    private Predio predio;
    private Integer contadorPasos;
    private Divipola departamentoSeleccionado;
    private Divipola municipioSeleccionado;
    private String cancelarAction;
    private Representante representanteLegal;    
    private List listaDeptosPredio;
    private List listaMunPredio;    
    private List listaDeptosRepresentante;
    private List listaMunRepresentante;
    private List listaTipoCentroPoblado;
                 
        
    private RichPanelStretchLayout psl_paso_1;
    private RichPanelGroupLayout pgl265;
    private RichCommandButton cb_next_paso_1;
    private RichSpacer s141;
    private RichPanelGroupLayout pgl398;
    private RichPanelFormLayout pfl178;
    private RichSelectOneChoice soc_tipo_usuario;
    private UISelectItems si1;
    private RichSelectOneChoice soc_tipo_id;
    private UISelectItems si2;
    private RichInputText it_numero_id;
    private RichOutputText ot174;
    private RichSpacer s245;
    private RichSpacer s3;
    private RichPanelBox pb_paso_2_natural;
    private RichPanelStretchLayout psl_paso2_natural;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichCommandButton cb_prev_paso_2_nat;
    private RichSpacer s4;
    private RichCommandButton cb_next_paso_2_nat;
    private RichSpacer s5;
    private RichSpacer s6;
    private RichSpacer s7;
    private RichOutputText ot236;
    private RichPanelFormLayout pfl2;
    private RichInputText it_nombres_nat;
    private RichInputText it_apellidos_nat;
    private RichInputText it_direccion_nat;
    private RichInputText it_telefono_nat;
    private RichInputText it_email_nat;
    private RichSelectOneChoice soc_depto_nat;
    private UISelectItems si3;
    private RichSelectOneChoice soc_municipio_nat;
    private UISelectItems si4;
    private RichPanelBox pb_paso_2_juridica;
    private RichPanelStretchLayout psl4;
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl7;
    private RichCommandButton cb_prev_paso_2_jur;
    private RichSpacer s8;
    private RichCommandButton cb_next_paso2_jur;
    private RichSpacer s9;
    private RichSpacer s10;
    private RichSpacer s11;
    private RichOutputText ot3;
    private RichPanelFormLayout pfl3;
    private RichInputText it_nombre_jur;
    private RichInputText it_direccion_jur;
    private RichSelectOneChoice soc_departamento_jur;
    private UISelectItems si5;
    private RichSelectOneChoice soc_municipio_jur;
    private UISelectItems si6;
    private RichInputText it_telefono_jur;
    private RichInputText it_email_jur;
    private RichPanelBox pb_paso2_mun;
    private RichPanelStretchLayout psl5;
    private RichPanelGroupLayout pgl8;
    private RichPanelGroupLayout pgl9;
    private RichCommandButton cb_prev_paso2_mun;
    private RichSpacer s12;
    private RichCommandButton cb_next_paso2_mun;
    private RichSpacer s13;
    private RichSpacer s14;
    private RichSpacer s15;
    private RichOutputText ot4;
    private RichPanelFormLayout pfl4;
    private RichInputText it_nombre_mun;
    private RichInputText it_direccion_mun;
    private RichSelectOneChoice soc_depto_mun;
    private UISelectItems si7;
    private RichSelectOneChoice soc_municipio_mun;
    private UISelectItems si8;
    private RichInputText it_telefono_mun;
    private RichInputText it_emanil_mun;
    private RichPanelBox pb_paso_predio;
    private RichPanelBox pb_paso_rep_legal;
    private RichPanelStretchLayout psl6;
    private RichPanelGroupLayout pgl10;
    private RichPanelGroupLayout pgl11;
    private RichCommandButton cb_prev_predio;
    private RichSpacer s16;
    private RichCommandButton cb_terminar;
    private RichSpacer s17;
    private RichSpacer s18;
    private RichSpacer s19;
    private RichOutputText ot5;
    private RichPanelFormLayout pfl5;
    private RichInputText it_nombre_predio;
    private RichInputText it_direccion_predio;
    private RichInputText it_cedula_predio;
    private RichSelectOneChoice soc_depto_predio;
    private UISelectItems si9;
    private RichSelectOneChoice soc_municipio_predio;
    private UISelectItems si10;
    private RichSelectOneChoice soc_clasifica_suelo;
    private UISelectItems si11;
    private RichSelectOneChoice soc_sistema_referencia;
    private UISelectItems si12;
    private RichInputText it_grados;
    private RichInputText it_minutos;
    private RichInputText it_segundos;
    private RichInputText it_altitud;
    private RichPanelFormLayout pfl6;
    private RichCommandButton cb_cancelar_predio;
    private RichSpacer s20;
    private RichPopup p_cancelar;
    private RichDialog d_cancelar;
    private RichOutputText ot6;
    private RichPanelGroupLayout pgl12;
    private RichCommandButton cb_si_cancelar;
    private RichSpacer s21;
    private RichCommandButton cb_no_cancelar;
    private RichPanelGroupLayout pgl13;
    private RichSpacer s22;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout pgl14;
    private RichCommandButton cb_aceptar;
    private RichPanelStretchLayout psl7;
    private RichImage i1;
    private RichOutputText ot8;
    private RichInputText it_ciiu;
    private RichPanelStretchLayout psl8;
    private RichPanelGroupLayout pgl15;
    private RichPanelGroupLayout pgl16;
    private RichCommandButton cb_prev_rep_legal;
    private RichSpacer s23;
    private RichCommandButton cb_next_rep_legal;
    private RichSpacer s24;
    private RichOutputText ot9;
    private RichSpacer s25;
    private RichSpacer s26;
    private RichPanelFormLayout pfl7;
    private RichInputText it_repl_nombres;
    private RichInputText it_repl_apellidos;
    private RichInputText it_repl_direccion;
    private RichSelectOneChoice soc_repl_depto;
    private UISelectItems si13;
    private RichSelectOneChoice soc_repl_municipio;
    private UISelectItems si14;
    private RichInputText it_repl_telefono;
    private RichInputText it_repl_email;
    private RichInputText it_repl_numero_id;
    private RichSelectOneChoice soc_rep_tipo_id;
    private UISelectItems si15;
    private RichInputText it_centro_poblado_mun;
    private RichCommandButton cb_terminar_mun;
    private RichInputText it_matricula;
    private RichPopup p_ciiu;
    private RichDialog d_ciiu;
    private RichPanelGroupLayout pgl19;
    private RichSpacer s29;
    private RichCommandButton cb_mostrar_ciiu;
    private RichInputText it_desc_ciiu;
    private UINamingContainer s27;
    private RichPanelStretchLayout psl9;
    private RichPanelFormLayout pfl8;
    private RichOutputLabel ol_invisible;
    private RichOutputLabel ol_mensaje;
    private RichMessage m2;
    private RichOutputLabel ol_ciiu;
    private RichMessage m3;
    private RichCommandButton cb_cancelar_rep_legal;
    private RichSpacer s28;
    private RichInputText it_observaciones;
    private RichPanelGroupLayout pgl17;
    private RichSelectOneChoice soc_tipo_centro_poblado;
    private UISelectItems si16;
    private RichSelectOneChoice soc_mun_tipo_suelo;
    private UISelectItems si17;
    private RichPanelFormLayout pfl9;
    private RichOutputLabel ol1;
    private RichOutputLabel ol2;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichSpacer s30;
    private RichInputText it_area;
    private RichInputText itarea;

    public AdicionarUsuarioBean(){
        FacesUtils.setActiveBean("adicionarUsuario",
                                 "Adicionar Del Agua",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            usuarioAgua = new UsuarioAgua();
            predio = new Predio();
            setRepresentanteLegal(new Representante());
            predio.setAutoridadAmbiental(usuarioConectado.getUsuario().
                                         getAutoridadAmbiental());
            contadorPasos = 1;
            
            this.listaTiposUsuarios = this.cargarParametro(Categoria.TIPO_USUARIO);
            this.listaTiposDocumento = this.cargarParametro(Categoria.TIPO_DOCUMENTO);
            this.listaClasificacionSuelo = this.cargarParametro(Categoria.CLASIFICACION_SUELO);
            this.listaSistemasReferencia = this.cargarParametro(Categoria.SISTEMAS_REFERENCIA_GEOGRAFICO);
            this.listaTipoCentroPoblado = this.cargarParametro(Categoria.TIPO_CENTRO_POBLADO);
            
            this.listaDepartamentos = this.cargarDivipolaSinRestriccion(null);
            this.listaDeptosPredio = this.cargarDivipola(null);
            this.listaDeptosRepresentante = this.cargarDivipolaSinRestriccion(null);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setD2(RichDocument d2) {
        this.d2 = d2;
    }

    public RichDocument getD2() {
        return d2;
    }

    public void setPsl12(RichPanelStretchLayout psl1) {
        this.psl12 = psl1;
    }

    public RichPanelStretchLayout getPsl12() {
        return psl12;
    }

    public void setPgl115(RichPanelGroupLayout pgl1) {
        this.pgl115 = pgl1;
    }

    public RichPanelGroupLayout getPgl115() {
        return pgl115;
    }

    public void setPb_paso_1(RichPanelBox pb1) {
        this.pb_paso_1 = pb1;
    }

    public RichPanelBox getPb_paso_1() {
        return pb_paso_1;
    }


    public List getListaTiposUsuarios() {
        return listaTiposUsuarios;
    }

    public void setListaTiposUsuarios(List listaTiposUsuarios) {
        this.listaTiposUsuarios = listaTiposUsuarios;
    }

    public List getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public void setPsl_paso_1(RichPanelStretchLayout psl2) {
        this.psl_paso_1 = psl2;
    }

    public RichPanelStretchLayout getPsl_paso_1() {
        return psl_paso_1;
    }

    public void setPgl265(RichPanelGroupLayout pgl2) {
        this.pgl265 = pgl2;
    }

    public RichPanelGroupLayout getPgl265() {
        return pgl265;
    }


    public void setCb_next_paso_1(RichCommandButton cb2) {
        this.cb_next_paso_1 = cb2;
    }

    public RichCommandButton getCb_next_paso_1() {
        return cb_next_paso_1;
    }

    public void setS141(RichSpacer s1) {
        this.s141 = s1;
    }

    public RichSpacer getS141() {
        return s141;
    }


    public void setPgl398(RichPanelGroupLayout pgl3) {
        this.pgl398 = pgl3;
    }

    public RichPanelGroupLayout getPgl398() {
        return pgl398;
    }

    public void setPfl178(RichPanelFormLayout pfl1) {
        this.pfl178 = pfl1;
    }

    public RichPanelFormLayout getPfl178() {
        return pfl178;
    }

    public void setSoc_tipo_usuario(RichSelectOneChoice soc1) {
        this.soc_tipo_usuario = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_usuario() {
        return soc_tipo_usuario;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSoc_tipo_id(RichSelectOneChoice soc2) {
        this.soc_tipo_id = soc2;
    }

    public RichSelectOneChoice getSoc_tipo_id() {
        return soc_tipo_id;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setIt_numero_id(RichInputText it1) {
        this.it_numero_id = it1;
    }

    public RichInputText getIt_numero_id() {
        return it_numero_id;
    }

    public void setOt174(RichOutputText ot1) {
        this.ot174 = ot1;
    }

    public RichOutputText getOt174() {
        return ot174;
    }

    public void setS245(RichSpacer s2) {
        this.s245 = s2;
    }

    public RichSpacer getS245() {
        return s245;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setPb_paso_2_natural(RichPanelBox pb1) {
        this.pb_paso_2_natural = pb1;
    }

    public RichPanelBox getPb_paso_2_natural() {
        return pb_paso_2_natural;
    }

    public void setPsl_paso2_natural(RichPanelStretchLayout psl3) {
        this.psl_paso2_natural = psl3;
    }

    public RichPanelStretchLayout getPsl_paso2_natural() {
        return psl_paso2_natural;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setCb_prev_paso_2_nat(RichCommandButton cb1) {
        this.cb_prev_paso_2_nat = cb1;
    }

    public RichCommandButton getCb_prev_paso_2_nat() {
        return cb_prev_paso_2_nat;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setCb_next_paso_2_nat(RichCommandButton cb2) {
        this.cb_next_paso_2_nat = cb2;
    }

    public RichCommandButton getCb_next_paso_2_nat() {
        return cb_next_paso_2_nat;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setOt236(RichOutputText ot2) {
        this.ot236 = ot2;
    }

    public RichOutputText getOt236() {
        return ot236;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setIt_nombres_nat(RichInputText it2) {
        this.it_nombres_nat = it2;
    }

    public RichInputText getIt_nombres_nat() {
        return it_nombres_nat;
    }

    public void setIt_apellidos_nat(RichInputText it3) {
        this.it_apellidos_nat = it3;
    }

    public RichInputText getIt_apellidos_nat() {
        return it_apellidos_nat;
    }

    public void setIt_direccion_nat(RichInputText it4) {
        this.it_direccion_nat = it4;
    }

    public RichInputText getIt_direccion_nat() {
        return it_direccion_nat;
    }

    public void setIt_telefono_nat(RichInputText it5) {
        this.it_telefono_nat = it5;
    }

    public RichInputText getIt_telefono_nat() {
        return it_telefono_nat;
    }

    public void setIt_email_nat(RichInputText it6) {
        this.it_email_nat = it6;
    }

    public RichInputText getIt_email_nat() {
        return it_email_nat;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public UsuarioAgua getUsuarioAgua() {
        return usuarioAgua;
    }

    public void setUsuarioAgua(UsuarioAgua usuarioAgua) {
        this.usuarioAgua = usuarioAgua;
    }

    public void setSoc_depto_nat(RichSelectOneChoice soc1) {
        this.soc_depto_nat = soc1;
    }

    public RichSelectOneChoice getSoc_depto_nat() {
        return soc_depto_nat;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSoc_municipio_nat(RichSelectOneChoice soc1) {
        this.soc_municipio_nat = soc1;
    }

    public RichSelectOneChoice getSoc_municipio_nat() {
        return soc_municipio_nat;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setPb_paso_2_juridica(RichPanelBox pb1) {
        this.pb_paso_2_juridica = pb1;
    }

    public RichPanelBox getPb_paso_2_juridica() {
        return pb_paso_2_juridica;
    }

    public void setPsl4(RichPanelStretchLayout psl4) {
        this.psl4 = psl4;
    }

    public RichPanelStretchLayout getPsl4() {
        return psl4;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
    }

    public void setCb_prev_paso_2_jur(RichCommandButton cb1) {
        this.cb_prev_paso_2_jur = cb1;
    }

    public RichCommandButton getCb_prev_paso_2_jur() {
        return cb_prev_paso_2_jur;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setCb_next_paso2_jur(RichCommandButton cb2) {
        this.cb_next_paso2_jur = cb2;
    }

    public RichCommandButton getCb_next_paso2_jur() {
        return cb_next_paso2_jur;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setIt_nombre_jur(RichInputText it1) {
        this.it_nombre_jur = it1;
    }

    public RichInputText getIt_nombre_jur() {
        return it_nombre_jur;
    }

    public void setIt_direccion_jur(RichInputText it2) {
        this.it_direccion_jur = it2;
    }

    public RichInputText getIt_direccion_jur() {
        return it_direccion_jur;
    }

    public void setSoc_departamento_jur(RichSelectOneChoice soc1) {
        this.soc_departamento_jur = soc1;
    }

    public RichSelectOneChoice getSoc_departamento_jur() {
        return soc_departamento_jur;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setSoc_municipio_jur(RichSelectOneChoice soc2) {
        this.soc_municipio_jur = soc2;
    }

    public RichSelectOneChoice getSoc_municipio_jur() {
        return soc_municipio_jur;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setIt_telefono_jur(RichInputText it3) {
        this.it_telefono_jur = it3;
    }

    public RichInputText getIt_telefono_jur() {
        return it_telefono_jur;
    }

    public void setIt_email_jur(RichInputText it4) {
        this.it_email_jur = it4;
    }

    public RichInputText getIt_email_jur() {
        return it_email_jur;
    }

    public void setPb_paso2_mun(RichPanelBox pb1) {
        this.pb_paso2_mun = pb1;
    }

    public RichPanelBox getPb_paso2_mun() {
        return pb_paso2_mun;
    }

    public void setPsl5(RichPanelStretchLayout psl5) {
        this.psl5 = psl5;
    }

    public RichPanelStretchLayout getPsl5() {
        return psl5;
    }

    public void setPgl8(RichPanelGroupLayout pgl8) {
        this.pgl8 = pgl8;
    }

    public RichPanelGroupLayout getPgl8() {
        return pgl8;
    }

    public void setPgl9(RichPanelGroupLayout pgl9) {
        this.pgl9 = pgl9;
    }

    public RichPanelGroupLayout getPgl9() {
        return pgl9;
    }

    public void setCb_prev_paso2_mun(RichCommandButton cb1) {
        this.cb_prev_paso2_mun = cb1;
    }

    public RichCommandButton getCb_prev_paso2_mun() {
        return cb_prev_paso2_mun;
    }

    public void setS12(RichSpacer s12) {
        this.s12 = s12;
    }

    public RichSpacer getS12() {
        return s12;
    }

    public void setCb_next_paso2_mun(RichCommandButton cb2) {
        this.cb_next_paso2_mun = cb2;
    }

    public RichCommandButton getCb_next_paso2_mun() {
        return cb_next_paso2_mun;
    }

    public void setS13(RichSpacer s13) {
        this.s13 = s13;
    }

    public RichSpacer getS13() {
        return s13;
    }

    public void setS14(RichSpacer s14) {
        this.s14 = s14;
    }

    public RichSpacer getS14() {
        return s14;
    }

    public void setS15(RichSpacer s15) {
        this.s15 = s15;
    }

    public RichSpacer getS15() {
        return s15;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }

    public void setIt_nombre_mun(RichInputText it1) {
        this.it_nombre_mun = it1;
    }

    public RichInputText getIt_nombre_mun() {
        return it_nombre_mun;
    }

    public void setIt_direccion_mun(RichInputText it2) {
        this.it_direccion_mun = it2;
    }

    public RichInputText getIt_direccion_mun() {
        return it_direccion_mun;
    }

    public void setSoc_depto_mun(RichSelectOneChoice soc1) {
        this.soc_depto_mun = soc1;
    }

    public RichSelectOneChoice getSoc_depto_mun() {
        return soc_depto_mun;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setSoc_municipio_mun(RichSelectOneChoice soc2) {
        this.soc_municipio_mun = soc2;
    }

    public RichSelectOneChoice getSoc_municipio_mun() {
        return soc_municipio_mun;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setIt_telefono_mun(RichInputText it1) {
        this.it_telefono_mun = it1;
    }

    public RichInputText getIt_telefono_mun() {
        return it_telefono_mun;
    }

    public void setIt_emanil_mun(RichInputText it3) {
        this.it_emanil_mun = it3;
    }

    public RichInputText getIt_emanil_mun() {
        return it_emanil_mun;
    }

    public void soc_tipo_usuario_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Segun el tipo de usuario seleccionado se cargan los datos
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.activarCamposTipoUsuario();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_juridica);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);
    }
    /**
     *  Limpia o carga los datos correspondientes segun el tipo de dato seleccionado
     */
    private void activarCamposTipoUsuario(){
        Lista tipoUsuario = this.usuarioAgua.getTipoUsuario();
        if(tipoUsuario!=null){
            if(tipoUsuario.getCodigo().intValue()==2 ||
               tipoUsuario.getCodigo().intValue()== 136 ||
               tipoUsuario.getCodigo().intValue()== 956){
                
                System.out.println("----------------------------estoy en 956-----------------------------------------------");
                this.usuarioAgua.setApellido(null);
            }else if (tipoUsuario.getCodigo().intValue()==3 ||
                      tipoUsuario.getCodigo().intValue()==4 ||
                      tipoUsuario.getCodigo().intValue()==1 ){
                this.usuarioAgua.setApellido(null);
            }else if(tipoUsuario.getCodigo().intValue()==5){
                
            }
        }else{
            this.usuarioAgua.setNombre(null);
            this.usuarioAgua.setApellido(null);
        }
    }

    public void cb_next_paso_1_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        // Validar que se haya seleccionado tipo y numero de id
        if(this.soc_tipo_usuario.getValue()==null){
            continuar = false;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_tipo_usuario);
        }
        if(this.soc_tipo_id.getValue()==null){
            continuar = false;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_tipo_id);
        }
        if(this.it_numero_id.getValue()==null || 
            this.it_numero_id.getValue().toString().length()==0){
            continuar = false;
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_numero_id);
        }
        if(continuar){            
            this.pb_paso_1.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_1);            
            Lista tipoUsuario = this.usuarioAgua.getTipoUsuario();
            if(tipoUsuario.getCodigo().intValue()== UsuarioAgua.MUNICIPIO ||
               tipoUsuario.getCodigo().intValue()== UsuarioAgua.ACUEDUCTO ||
               tipoUsuario.getCodigo().intValue()== UsuarioAgua.MEGAPROYECTO){
                this.pb_paso2_mun.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);
            }else if (tipoUsuario.getCodigo().intValue()== UsuarioAgua.JURIDICA_PRIVADA ||
                      tipoUsuario.getCodigo().intValue()== UsuarioAgua.JURIDICA_PUBLICA ||
                      tipoUsuario.getCodigo().intValue()== UsuarioAgua.CORPORACION  ){
                this.pb_paso_2_juridica.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_juridica);
            }else if(tipoUsuario.getCodigo().intValue()== UsuarioAgua.NATURAL){         
                this.pb_paso_2_natural.setVisible(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);
            }
        }
    }

    public void cb_prev_paso_2_nat_actionListener(ActionEvent actionEvent) {
        this.pb_paso_2_natural.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);     
        this.pb_paso_1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_1);                    
    }

    public void cb_prev_paso2_mun_actionListener(ActionEvent actionEvent) {
        this.pb_paso2_mun.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);        
        this.pb_paso_1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_1);                    
    }

    public void cb_prev_paso_2_jur_actionListener(ActionEvent actionEvent) {
        this.pb_paso_2_juridica.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_juridica);        
        this.pb_paso_1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_1);                    
    }

    public void soc_depto_nat_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try{
            if(depto!=null){
                this.listaMunicipios = this.cargarDivipolaSinRestriccion(depto.getId());                
            }else{
                this.listaMunicipios = new ArrayList();    
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_municipio_jur);                    
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_municipio_mun);                    
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_municipio_nat);                            
    }

    public void cb_next_paso_2_nat_actionListener(ActionEvent actionEvent) {
        // Validar campos
        boolean continuar = true;
        if(this.it_nombres_nat.getValue()==null || 
           this.it_nombres_nat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_nombres_nat);       
            continuar = false;
        }else{
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_nombres_nat,
                         this.it_nombres_nat.getValue());
            this.it_nombres_nat.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }
        if(this.it_apellidos_nat.getValue()==null || 
           this.it_apellidos_nat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_apellidos_nat);       
            continuar = false;
        }else{
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_apellidos_nat,
                         this.it_apellidos_nat.getValue());
            this.it_apellidos_nat.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }
        if(this.it_direccion_nat.getValue()==null || 
           this.it_direccion_nat.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_direccion_nat);       
            continuar = false;
        }        
        if(this.soc_depto_nat.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_depto_nat);       
            continuar = false;
        }
        if(this.soc_municipio_nat.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.soc_municipio_nat);       
            continuar = false;
        }
        if(this.it_telefono_nat.getValue()==null || 
           this.it_telefono_nat.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_telefono_nat);       
            //continuar = false;
        }        
        if(this.it_email_nat.getValue()==null || 
           this.it_email_nat.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.it_email_nat);       
            //continuar = false;
        }else{
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         it_email_nat,
                         it_email_nat.getValue());            
            it_email_nat.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }
        
        if(continuar){
            this.setDepartamentoSeleccionado((Divipola)this.soc_depto_nat.getValue());
            this.setMunicipioSeleccionado((Divipola)this.soc_municipio_nat.getValue());
            contadorPasos = 3;
            String params[] = {"" + contadorPasos};
            String titulo = getText("PASO___DETALLE_DEL_PREDIO", params);
            titulo = getText("ADICIONAR_USUARIO_DEL_AGUA") + " " + titulo;        
            this.pb_paso_2_natural.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);        
            this.pb_paso_predio.setText(titulo);
            this.pb_paso_predio.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_predio);                
        }
    }    

    public void setPb_paso_predio(RichPanelBox pb1) {
        this.pb_paso_predio = pb1;
    }

    public RichPanelBox getPb_paso_predio() {
        return pb_paso_predio;
    }

    public void setPb_paso_rep_legal(RichPanelBox pb1) {
        this.pb_paso_rep_legal = pb1;
    }

    public RichPanelBox getPb_paso_rep_legal() {
        return pb_paso_rep_legal;
    }

    public void setPsl6(RichPanelStretchLayout psl6) {
        this.psl6 = psl6;
    }

    public RichPanelStretchLayout getPsl6() {
        return psl6;
    }

    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }

    public void setCb_prev_predio(RichCommandButton cb1) {
        this.cb_prev_predio = cb1;
    }

    public RichCommandButton getCb_prev_predio() {
        return cb_prev_predio;
    }

    public void setS16(RichSpacer s16) {
        this.s16 = s16;
    }

    public RichSpacer getS16() {
        return s16;
    }

    public void setCb_terminar(RichCommandButton cb2) {
        this.cb_terminar = cb2;
    }

    public RichCommandButton getCb_terminar() {
        return cb_terminar;
    }

    public void setS17(RichSpacer s17) {
        this.s17 = s17;
    }

    public RichSpacer getS17() {
        return s17;
    }

    public void setS18(RichSpacer s18) {
        this.s18 = s18;
    }

    public RichSpacer getS18() {
        return s18;
    }

    public void setS19(RichSpacer s19) {
        this.s19 = s19;
    }

    public RichSpacer getS19() {
        return s19;
    }

    public void setOt5(RichOutputText ot5) {
        this.ot5 = ot5;
    }

    public RichOutputText getOt5() {
        return ot5;
    }

    public void setPfl5(RichPanelFormLayout pfl5) {
        this.pfl5 = pfl5;
    }

    public RichPanelFormLayout getPfl5() {
        return pfl5;
    }

    public void setIt_nombre_predio(RichInputText it1) {
        this.it_nombre_predio = it1;
    }

    public RichInputText getIt_nombre_predio() {
        return it_nombre_predio;
    }

    public void setIt_direccion_predio(RichInputText it2) {
        this.it_direccion_predio = it2;
    }

    public RichInputText getIt_direccion_predio() {
        return it_direccion_predio;
    }

    public void setIt_cedula_predio(RichInputText it1) {
        this.it_cedula_predio = it1;
    }

    public RichInputText getIt_cedula_predio() {
        return it_cedula_predio;
    }


    public void setSoc_depto_predio(RichSelectOneChoice soc1) {
        this.soc_depto_predio = soc1;
    }

    public RichSelectOneChoice getSoc_depto_predio() {
        return soc_depto_predio;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setSoc_municipio_predio(RichSelectOneChoice soc2) {
        this.soc_municipio_predio = soc2;
    }

    public RichSelectOneChoice getSoc_municipio_predio() {
        return soc_municipio_predio;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }

    public void setSoc_clasifica_suelo(RichSelectOneChoice soc1) {
        this.soc_clasifica_suelo = soc1;
    }

    public RichSelectOneChoice getSoc_clasifica_suelo() {
        return soc_clasifica_suelo;
    }

    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }

    public List getListaClasificacionSuelo() {
        return listaClasificacionSuelo;
    }

    public void setListaClasificacionSuelo(List listaClasificacionSuelo) {
        this.listaClasificacionSuelo = listaClasificacionSuelo;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public void cb_prev_predio_actionListener(ActionEvent actionEvent) {
        this.pb_paso_predio.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_predio);            
        Lista tipoUsuario = this.usuarioAgua.getTipoUsuario();
        if(tipoUsuario.getCodigo().intValue()==2 ||
           tipoUsuario.getCodigo().intValue()== 136 ||
               tipoUsuario.getCodigo().intValue()== 956){
            this.pb_paso2_mun.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);
        }else if (tipoUsuario.getCodigo().intValue()==3 ||
                  tipoUsuario.getCodigo().intValue()==4 ||
                  tipoUsuario.getCodigo().intValue()==1){
            this.pb_paso_rep_legal.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_rep_legal);
        }else if(tipoUsuario.getCodigo().intValue()==5){
            this.pb_paso_2_natural.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);
        }
    }

    public List getListaSistemasReferencia() {
        return listaSistemasReferencia;
    }

    public void setListaSistemasReferencia(List listaSistemasReferencia) {
        this.listaSistemasReferencia = listaSistemasReferencia;
    }

    public void setSoc_sistema_referencia(RichSelectOneChoice soc1) {
        this.soc_sistema_referencia = soc1;
    }

    public RichSelectOneChoice getSoc_sistema_referencia() {
        return soc_sistema_referencia;
    }

    public void setSi12(UISelectItems si12) {
        this.si12 = si12;
    }

    public UISelectItems getSi12() {
        return si12;
    }

    public void setIt_grados(RichInputText it1) {
        this.it_grados = it1;
    }

    public RichInputText getIt_grados() {
        return it_grados;
    }

    public void setIt_minutos(RichInputText it2) {
        this.it_minutos = it2;
    }

    public RichInputText getIt_minutos() {
        return it_minutos;
    }

    public void setIt_segundos(RichInputText it3) {
        this.it_segundos = it3;
    }

    public RichInputText getIt_segundos() {
        return it_segundos;
    }

    public void setIt_altitud(RichInputText it4) {
        this.it_altitud = it4;
    }

    public RichInputText getIt_altitud() {
        return it_altitud;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setCb_cancelar_predio(RichCommandButton cb1) {
        this.cb_cancelar_predio = cb1;
    }

    public RichCommandButton getCb_cancelar_predio() {
        return cb_cancelar_predio;
    }

    public void setS20(RichSpacer s20) {
        this.s20 = s20;
    }

    public RichSpacer getS20() {
        return s20;
    }

    public String getCancelarAction() {
        return cancelarAction;
    }

    public void setCancelarAction(String cancelarAction) {
        this.cancelarAction = cancelarAction;
    }

    public void cb_cancelar_predio_actionListener(ActionEvent actionEvent) {
        cancelarAction = "";
        showPopup(p_cancelar,true);        
    }

    public void setP_cancelar(RichPopup p1) {
        this.p_cancelar = p1;
    }

    public RichPopup getP_cancelar() {
        return p_cancelar;
    }

    public void setD_cancelar(RichDialog d1) {
        this.d_cancelar = d1;
    }

    public RichDialog getD_cancelar() {
        return d_cancelar;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }


    public void setPgl12(RichPanelGroupLayout pgl12) {
        this.pgl12 = pgl12;
    }

    public RichPanelGroupLayout getPgl12() {
        return pgl12;
    }

    public void setCb_si_cancelar(RichCommandButton cb1) {
        this.cb_si_cancelar = cb1;
    }

    public RichCommandButton getCb_si_cancelar() {
        return cb_si_cancelar;
    }

    public void setS21(RichSpacer s21) {
        this.s21 = s21;
    }

    public RichSpacer getS21() {
        return s21;
    }

    public void setCb_no_cancelar(RichCommandButton cb2) {
        this.cb_no_cancelar = cb2;
    }

    public RichCommandButton getCb_no_cancelar() {
        return cb_no_cancelar;
    }

    public void cb_si_cancelar_actionListener(ActionEvent actionEvent) {
        cancelarAction = "usuariosAgua";        
    }

    public void setPgl13(RichPanelGroupLayout pgl13) {
        this.pgl13 = pgl13;
    }

    public RichPanelGroupLayout getPgl13() {
        return pgl13;
    }


    public void setS22(RichSpacer s22) {
        this.s22 = s22;
    }

    public RichSpacer getS22() {
        return s22;
    }

    public void cb_terminar_actionListener(ActionEvent actionEvent) {
        this.setCancelarAction("");        
        boolean continuar = validarPredio();
        if(continuar){
            guardar();
        }
    }
    public boolean validarPredio(){
        boolean continuar = true;
        // validar datos obligatorios
        if(this.it_nombre_predio.getValue()==null ||
           this.it_nombre_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre_predio);
            continuar = false;
        }
        if(this.it_direccion_predio.getValue()==null ||
           this.it_direccion_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion_predio);
            continuar = false;
        }
        if(this.soc_depto_predio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto_predio);
            continuar = false;
        }
        if(this.soc_municipio_predio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio_predio);
            continuar = false;
        }        
        
        if(this.it_cedula_predio.getValue()==null ||
           this.it_cedula_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_cedula_predio);
            continuar = false;
        }
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            PrediosTO existe = uad.getPredioTO(
                                this.it_cedula_predio.getValue().toString(),this.it_numero_id.getValue().toString(), usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() );
            if(existe!=null){
                showMessage("CEDULA_EXISTE",FacesMessage.SEVERITY_ERROR,it_cedula_predio);
                continuar = false;
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        
        // Se elimina validacion de obligatoriedad por solicitud del usuario
        // funcional con email del 12/12/2013        
        if(this.it_matricula.getValue()==null ||
           this.it_matricula.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_matricula);
            //continuar = false;
        }        
        if(this.soc_clasifica_suelo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_clasifica_suelo);
            continuar = false;
        }        
      /*  if(this.soc_sistema_referencia.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_sistema_referencia);
            continuar = false;
        }        
        if(this.it_altitud.getValue()==null ||
           this.it_altitud.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_altitud);
            continuar = false;
        }
        if(this.it_grados.getValue()==null ||
           this.it_grados.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_grados);
            continuar = false;
        }        
        if(this.it_minutos.getValue()==null ||
           this.it_minutos.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_minutos);
            continuar = false;
        }         
        if(this.it_segundos.getValue()==null ||
           this.it_segundos.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_segundos);
            continuar = false;
        }*/               
        return continuar;
    }
    public void guardar(){
        if(this.predio.getNombre()==null ||
           this.predio.getNombre().toString().length() ==0){
            this.predio.setNombre(
                    this.usuarioAgua.getNombre());       
        }
        try{
            UsuariosAguaDelegate usd = UsuariosAguaDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            usuarioAgua.setDepartamento(this.getDepartamentoSeleccionado());
            usuarioAgua.setMunicipio(this.getMunicipioSeleccionado());            
            Autoridades autoridad = 
                usuarioConectado.getUsuario().getAutoridadAmbiental();            
            System.out.println("********++--------------------------------representanteLegal  continuar+ "+representanteLegal.getCodigoDepartamento());
            Object retorno[] = usd.registrarUsuarioPredio(this.usuarioAgua,
                                       this.predio,
                                       this.representanteLegal,
                                       autoridad);  
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                // UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("GUARDAR");
                auditoria.setObjeto("USUARIOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(new Long(retorno[0].toString()));
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                //System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            FacesUtils.removeFromSession("UsuariosTreeHandler");
            FacesUtils.setInSession("usuarioSeleccionado", retorno[0]);
            FacesUtils.setInSession("predioSeleccionado", retorno[1]);
            showPopup(p_registro_exitoso, true);
        }catch(IdeamException e){
            this.setCancelarAction("");
            showMessage(e);
        }
    }

    public void setP_registro_exitoso(RichPopup p1) {
        this.p_registro_exitoso = p1;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d1) {
        this.d_registro_exitoso = d1;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }


    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setCancelarAction("detalleUsuario");
    }

    public void setIt_ciiu(RichInputText it1) {
        this.it_ciiu = it1;
    }

    public RichInputText getIt_ciiu() {
        return it_ciiu;
    }

    public Divipola getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Divipola departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Divipola getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    public void setMunicipioSeleccionado(Divipola municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }

    public void cb_next_paso2_jur_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        // Validar campos obligatorios
        if(this.it_nombre_jur.getValue()==null ||
           this.it_nombre_jur.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre_jur);
            continuar = false;
        }
        if(this.it_direccion_jur.getValue()==null ||
           this.it_direccion_jur.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion_jur);
            continuar = false;
        }
        if(this.soc_departamento_jur.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_departamento_jur);
            continuar = false;
        }
        if(this.soc_municipio_jur.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio_jur);
            continuar = false;
        }
        if(this.it_telefono_jur.getValue()==null ||
           this.it_telefono_jur.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_telefono_jur);
            //continuar = false;
        }        
        if(this.it_email_jur.getValue()==null ||
           this.it_email_jur.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_email_jur);
            //continuar = false;
        }else{
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         it_email_jur,
                         it_email_jur.getValue());            
            it_email_jur.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }
        
        if(this.usuarioAgua.getActividadCiiu()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,ol_ciiu);
            continuar = false;
        }
        
        if(continuar){
            this.setDepartamentoSeleccionado((Divipola)this.soc_departamento_jur.getValue());
            this.setMunicipioSeleccionado((Divipola)this.soc_municipio_jur.getValue());
            contadorPasos = 3;
            String params[] = {"" + contadorPasos};
            String titulo = getText("REPRESENTANTE_LEGAL", params);
            titulo = getText("ADICIONAR_USUARIO_DEL_AGUA") + " " + titulo;        
            this.pb_paso_2_juridica.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_juridica);        
            this.pb_paso_rep_legal.setText(titulo);
            this.pb_paso_rep_legal.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_rep_legal);
        }        
        
    }

    public void setPsl8(RichPanelStretchLayout psl8) {
        this.psl8 = psl8;
    }

    public RichPanelStretchLayout getPsl8() {
        return psl8;
    }

    public void setPgl15(RichPanelGroupLayout pgl15) {
        this.pgl15 = pgl15;
    }

    public RichPanelGroupLayout getPgl15() {
        return pgl15;
    }

    public void setPgl16(RichPanelGroupLayout pgl16) {
        this.pgl16 = pgl16;
    }

    public RichPanelGroupLayout getPgl16() {
        return pgl16;
    }

    public void setCb_prev_rep_legal(RichCommandButton cb1) {
        this.cb_prev_rep_legal = cb1;
    }

    public RichCommandButton getCb_prev_rep_legal() {
        return cb_prev_rep_legal;
    }

    public void setS23(RichSpacer s23) {
        this.s23 = s23;
    }

    public RichSpacer getS23() {
        return s23;
    }

    public void setCb_next_rep_legal(RichCommandButton cb2) {
        this.cb_next_rep_legal = cb2;
    }

    public RichCommandButton getCb_next_rep_legal() {
        return cb_next_rep_legal;
    }

    public void setS24(RichSpacer s24) {
        this.s24 = s24;
    }

    public RichSpacer getS24() {
        return s24;
    }

    public void setOt9(RichOutputText ot9) {
        this.ot9 = ot9;
    }

    public RichOutputText getOt9() {
        return ot9;
    }

    public void setS25(RichSpacer s25) {
        this.s25 = s25;
    }

    public RichSpacer getS25() {
        return s25;
    }

    public void setS26(RichSpacer s26) {
        this.s26 = s26;
    }

    public RichSpacer getS26() {
        return s26;
    }

    public void setPfl7(RichPanelFormLayout pfl7) {
        this.pfl7 = pfl7;
    }

    public RichPanelFormLayout getPfl7() {
        return pfl7;
    }

    public void setIt_repl_nombres(RichInputText it1) {
        this.it_repl_nombres = it1;
    }

    public RichInputText getIt_repl_nombres() {
        return it_repl_nombres;
    }

    public void setIt_repl_apellidos(RichInputText it2) {
        this.it_repl_apellidos = it2;
    }

    public RichInputText getIt_repl_apellidos() {
        return it_repl_apellidos;
    }

    public void setIt_repl_direccion(RichInputText it3) {
        this.it_repl_direccion = it3;
    }

    public RichInputText getIt_repl_direccion() {
        return it_repl_direccion;
    }

    public void setSoc_repl_depto(RichSelectOneChoice soc1) {
        this.soc_repl_depto = soc1;
    }

    public RichSelectOneChoice getSoc_repl_depto() {
        return soc_repl_depto;
    }

    public void setSi13(UISelectItems si13) {
        this.si13 = si13;
    }

    public UISelectItems getSi13() {
        return si13;
    }

    public void setSoc_repl_municipio(RichSelectOneChoice soc2) {
        this.soc_repl_municipio = soc2;
    }

    public RichSelectOneChoice getSoc_repl_municipio() {
        return soc_repl_municipio;
    }

    public void setSi14(UISelectItems si14) {
        this.si14 = si14;
    }

    public UISelectItems getSi14() {
        return si14;
    }

    public void setIt_repl_telefono(RichInputText it4) {
        this.it_repl_telefono = it4;
    }

    public RichInputText getIt_repl_telefono() {
        return it_repl_telefono;
    }

    public void setIt_repl_email(RichInputText it5) {
        this.it_repl_email = it5;
    }

    public RichInputText getIt_repl_email() {
        return it_repl_email;
    }

    public Representante getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(Representante representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public void setIt_repl_numero_id(RichInputText it1) {
        this.it_repl_numero_id = it1;
    }

    public RichInputText getIt_repl_numero_id() {
        return it_repl_numero_id;
    }

    public void setSoc_rep_tipo_id(RichSelectOneChoice soc1) {
        this.soc_rep_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_rep_tipo_id() {
        return soc_rep_tipo_id;
    }

    public void setSi15(UISelectItems si15) {
        this.si15 = si15;
    }

    public UISelectItems getSi15() {
        return si15;
    }

    public void cb_next_rep_legal_actionListener(ActionEvent actionEvent) {
        boolean continuar = validarRepresentanteLegal();
        if(continuar){
            contadorPasos = 4;
            String params[] = {"" + contadorPasos};
            String titulo = getText("PASO___DETALLE_DEL_PREDIO", params);
            titulo = getText("ADICIONAR_USUARIO_DEL_AGUA") + " " + titulo;        
            this.pb_paso_rep_legal.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_rep_legal);        
            this.pb_paso_predio.setText(titulo);
            this.pb_paso_predio.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_predio);   
        }
    }
    public boolean validarRepresentanteLegal(){
        boolean continuar = true;
        if(soc_rep_tipo_id.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_rep_tipo_id);
            continuar = false;
        }
        if(it_repl_numero_id.getValue()==null ||
           it_repl_numero_id.getValue().toString().length()==0){
               showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_numero_id);
               continuar = false;
        }
        
        if(it_repl_nombres.getValue()==null ||
           it_repl_nombres.getValue().toString().length()==0){
               showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_nombres);
               continuar = false;
        }else{
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_repl_nombres,
                         this.it_repl_nombres.getValue());
            this.it_repl_nombres.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }     
        if(it_repl_apellidos.getValue()==null ||
           it_repl_apellidos.getValue().toString().length()==0){
               showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_apellidos);
               continuar = false;
        }else{
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_repl_apellidos,
                         this.it_repl_apellidos.getValue());
            this.it_repl_apellidos.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }      
        if(it_repl_direccion.getValue()==null ||
           it_repl_direccion.getValue().toString().length()==0){
               showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_direccion);
               continuar = false;
        }    
        if(soc_repl_depto.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_repl_depto);
            continuar = false;
        }        
        if(soc_repl_municipio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_repl_municipio);
            continuar = false;
        }           
        if(it_repl_telefono.getValue()==null ||
           it_repl_telefono.getValue().toString().length()==0){
               //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_telefono);
               //continuar = false;
        }            
        if(it_repl_email.getValue()==null ||
           it_repl_email.getValue().toString().length()==0){
               //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_repl_email);
               //continuar = false;
        }else{
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         it_repl_email,
                         it_repl_email.getValue());            
            it_repl_email.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }     
        
        
        System.out.println("********++--------------------------------holaaaaaaaa  continuar+ "+continuar);
        return continuar;
    }

    public void cb_next_paso2_mun_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        
        if(this.it_nombre_mun.getValue() == null ||
           this.it_nombre_mun.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre_mun);       
            continuar = false;
        }
        if(this.it_direccion_mun.getValue() == null ||
           this.it_direccion_mun.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion_mun);       
            continuar = false;
        }
        if(this.soc_depto_mun.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto_mun);       
            continuar = false;            
        }
        if(this.soc_municipio_mun.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio_mun);       
            continuar = false;            
        }        
        if(this.soc_mun_tipo_suelo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_mun_tipo_suelo);       
            continuar = false;            
        }        
        if(this.soc_tipo_centro_poblado.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_centro_poblado);       
            continuar = false;            
        }        
        
        if(this.it_telefono_mun.getValue() == null ||
           this.it_telefono_mun.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_telefono_mun);       
            //continuar = false;
        }
        if(this.it_emanil_mun.getValue() == null ||
           this.it_emanil_mun.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_emanil_mun);       
            //continuar = false;
        }else{
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         it_emanil_mun,
                         it_emanil_mun.getValue());            
            it_emanil_mun.setValid(val.isValid());
            if(!val.isValid()){
                continuar = false;
            }
        }
        
        
        if(continuar){
            this.setDepartamentoSeleccionado((Divipola)this.soc_depto_mun.getValue());
            this.setMunicipioSeleccionado((Divipola)this.soc_municipio_mun.getValue());
            this.predio.setTipoCentroPoblado((Lista)this.soc_tipo_centro_poblado.getValue());
            this.predio.setTipoSuelo((Lista)soc_mun_tipo_suelo.getValue());   
            contadorPasos = 3;
            String params[] = {"" + contadorPasos};
            String titulo = getText("REPRESENTANTE_LEGAL", params);
            titulo = getText("ADICIONAR_USUARIO_DEL_AGUA") + " " + titulo;        
            this.pb_paso2_mun.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);        
            this.pb_paso_rep_legal.setText(titulo);
            this.pb_paso_rep_legal.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_rep_legal);
        }  
    }

    public void setIt_centro_poblado_mun(RichInputText it1) {
        this.it_centro_poblado_mun = it1;
    }

    public RichInputText getIt_centro_poblado_mun() {
        return it_centro_poblado_mun;
    }

    public void cb_prev_rep_legal_actionListener(ActionEvent actionEvent) {
        this.pb_paso_rep_legal.setVisible(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_rep_legal);            
        Lista tipoUsuario = this.usuarioAgua.getTipoUsuario();
        if(tipoUsuario.getCodigo().intValue()==2 ||
           tipoUsuario.getCodigo().intValue()== 136 ||
               tipoUsuario.getCodigo().intValue()== 956){
            this.pb_paso2_mun.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso2_mun);
        }else if (tipoUsuario.getCodigo().intValue()==3 ||
                  tipoUsuario.getCodigo().intValue()==4 ||
                  tipoUsuario.getCodigo().intValue()==1){
            this.pb_paso_2_juridica.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_juridica);
        }else if(tipoUsuario.getCodigo().intValue()==5){
            this.pb_paso_2_natural.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.pb_paso_2_natural);
        }
    }

    public void setCb_terminar_mun(RichCommandButton cb1) {
        this.cb_terminar_mun = cb1;
    }

    public RichCommandButton getCb_terminar_mun() {
        return cb_terminar_mun;
    }

    public void cb_terminar_mun_actionListener(ActionEvent actionEvent) {
        System.out.println("********++--------------------------------holaaaaaaaa  cb_terminar_mun_actionListener ");
        boolean continuar = validarRepresentanteLegal();
        if(continuar){
            predio.setDepartamento((Divipola)soc_depto_mun.getValue());
            predio.setMunicipio((Divipola)soc_municipio_mun.getValue());
            predio.setTipoCentroPoblado((Lista)soc_tipo_centro_poblado.getValue());
            
            System.out.println("********++--------------------------------GUARDAR  continuar+ "+continuar);
            guardar();
        }
    }

    public void consultarUsuario(){
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        if(this.usuarioAgua.getTipoDocumento()!=null &&
           this.usuarioAgua.getNumeroDocumento() != null &&
           this.usuarioAgua.getNumeroDocumento().toString().length() > 0){
            
            Lista tipoDocumento = this.usuarioAgua.getTipoDocumento();
            String numeroDocumento = this.usuarioAgua.getNumeroDocumento();    
            Lista tipoUsuario = this.usuarioAgua.getTipoUsuario();
            
            try{                            
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                UsuarioAgua usuarioExiste = uad.getUsuario(
                    this.usuarioAgua.getTipoDocumento().getCodigo(),
                    this.usuarioAgua.getNumeroDocumento(),
                    usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                    if (usuarioExiste!=null){
                        this.usuarioAgua = usuarioExiste;
                        
                        this.departamentoSeleccionado = this.usuarioAgua.getDepartamento();                            
                        soc_depto_nat.setValue(this.departamentoSeleccionado);
                        soc_departamento_jur.setValue(this.departamentoSeleccionado);
                        soc_depto_mun.setValue(this.departamentoSeleccionado);
                        this.listaMunicipios = cargarDivipolaSinRestriccion(this.departamentoSeleccionado.getId());
                        this.municipioSeleccionado = usuarioAgua.getMunicipio(); 
                        //System.out.println("------  proceso de cargue de datos de usuario existente con municipio:"+ this.municipioSeleccionado+" -----");
                        soc_municipio_nat.setValue(this.municipioSeleccionado);
                        soc_municipio_jur.setValue(this.municipioSeleccionado);
                        soc_municipio_mun.setValue(this.municipioSeleccionado);
                        
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_2_natural);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_2_juridica);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso2_mun);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(this.soc_municipio_nat);   
                        
                        if(this.usuarioAgua.getRepresentanteLegal()!=null){
                            this.representanteLegal = this.usuarioAgua.getRepresentanteLegal();
                            this.soc_rep_tipo_id.setValue( this.representanteLegal.getTipoDocumento() );
                            this.soc_repl_depto.setValue( this.representanteLegal.getDepartamento() );
                            this.soc_repl_municipio.setValue( this.representanteLegal.getMunicipio() );
                            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_rep_legal);
                        }
                    }else{
                        System.out.println("------  proceso de cargue de datos de usuario NO existente -----");
                        this.usuarioAgua = new UsuarioAgua();
                        this.usuarioAgua.setTipoDocumento( tipoDocumento );
                        this.usuarioAgua.setNumeroDocumento( numeroDocumento );
                        this.usuarioAgua.setTipoUsuario( tipoUsuario );
                        
                        this.departamentoSeleccionado = null; 
                        soc_depto_nat.setValue(this.departamentoSeleccionado);
                        soc_departamento_jur.setValue(this.departamentoSeleccionado);
                        soc_depto_mun.setValue(this.departamentoSeleccionado);
                        
                        this.listaMunicipios = new ArrayList();
                        this.municipioSeleccionado = null; 
                        soc_municipio_nat.setValue(this.municipioSeleccionado);
                        soc_municipio_jur.setValue(this.municipioSeleccionado);
                        soc_municipio_mun.setValue(this.municipioSeleccionado);

                        setRepresentanteLegal(new Representante());

                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_rep_legal);                        
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_2_natural);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso_2_juridica);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_paso2_mun);                        
                    }
            }catch(IdeamException e){
                showMessage(e);
            }
        }
    }
    
    public void soc_tipo_id_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.consultarUsuario();
    }

    public void it_numero_id_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        this.consultarUsuario();
    }

    public void setIt_matricula(RichInputText it1) {
        this.it_matricula = it1;
    }

    public RichInputText getIt_matricula() {
        return it_matricula;
    }

    public void setP_ciiu(RichPopup p1) {
        this.p_ciiu = p1;
    }

    public RichPopup getP_ciiu() {
        return p_ciiu;
    }

    public void setD_ciiu(RichDialog d1) {
        this.d_ciiu = d1;
    }

    public RichDialog getD_ciiu() {
        return d_ciiu;
    }

    public void setPgl19(RichPanelGroupLayout pgl19) {
        this.pgl19 = pgl19;
    }

    public RichPanelGroupLayout getPgl19() {
        return pgl19;
    }

    public void setS29(RichSpacer s29) {
        this.s29 = s29;
    }

    public RichSpacer getS29() {
        return s29;
    }

    public void setCb_mostrar_ciiu(RichCommandButton cb2) {
        this.cb_mostrar_ciiu = cb2;
    }

    public RichCommandButton getCb_mostrar_ciiu() {
        return cb_mostrar_ciiu;
    }

    public void cb_mostrar_ciiu_actionListener(ActionEvent actionEvent) {        
        Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
        if(obj!=null){
            ((BuscarCiiuBean)obj).inicialize();
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_ciiu);
        showPopup(p_ciiu, true);
    }


    public void setIt_desc_ciiu(RichInputText it3) {
        this.it_desc_ciiu = it3;
    }

    public RichInputText getIt_desc_ciiu() {
        return it_desc_ciiu;
    }

    public void setS27(UINamingContainer s27) {
        this.s27 = s27;
    }

    public UINamingContainer getS27() {
        return s27;
    }

    public void d_ciiu_dialogListener(DialogEvent dialogEvent) {
        DialogEvent.Outcome oc = dialogEvent.getOutcome();
        if(oc == DialogEvent.Outcome.yes){
            Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
            BuscarCiiuBean ciiuBean = (BuscarCiiuBean)obj;
            try{
                ciiuBean.buscar();
                
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, ol_invisible);                
            }catch(IdeamException e){                
                showMessage(e.getMessage(),
                            FacesMessage.SEVERITY_ERROR,
                            ol_mensaje);
                //showMessage(e);
            }
        }
        if(oc == DialogEvent.Outcome.no){
            
            Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
            BuscarCiiuBean ciiuBean = (BuscarCiiuBean)obj;
            try{
                ActividadCIIU ciiuSelecciona = ciiuBean.getData();
                this.usuarioAgua.setActividadCiiu(ciiuSelecciona);
                this.usuarioAgua.setCodigoActividadCiiu(ciiuSelecciona.getId());                
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_ciiu);
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_desc_ciiu);
                
                FacesUtils.removeManagedBeanFromSession("buscarCiiu");
                showPopup(p_ciiu, false);       
            }catch(IdeamException e){                
                showMessage(e.getMessage(),
                            FacesMessage.SEVERITY_ERROR,
                            ol_mensaje);
            }
            
        }
    }

    public void setPsl9(RichPanelStretchLayout psl9) {
        this.psl9 = psl9;
    }

    public RichPanelStretchLayout getPsl9() {
        return psl9;
    }

    public void setPfl8(RichPanelFormLayout pfl8) {
        this.pfl8 = pfl8;
    }

    public RichPanelFormLayout getPfl8() {
        return pfl8;
    }

    public void setOl_invisible(RichOutputLabel ol1) {
        this.ol_invisible = ol1;
    }

    public RichOutputLabel getOl_invisible() {
        return ol_invisible;
    }


    public void setOl_mensaje(RichOutputLabel ol1) {
        this.ol_mensaje = ol1;
    }

    public RichOutputLabel getOl_mensaje() {
        return ol_mensaje;
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
    }

    public void setOl_ciiu(RichOutputLabel ol1) {
        this.ol_ciiu = ol1;
    }

    public RichOutputLabel getOl_ciiu() {
        return ol_ciiu;
    }

    public void setM3(RichMessage m3) {
        this.m3 = m3;
    }

    public RichMessage getM3() {
        return m3;
    }

    public void setCb_cancelar_rep_legal(RichCommandButton cb1) {
        this.cb_cancelar_rep_legal = cb1;
    }

    public RichCommandButton getCb_cancelar_rep_legal() {
        return cb_cancelar_rep_legal;
    }

    public void setS28(RichSpacer s28) {
        this.s28 = s28;
    }

    public RichSpacer getS28() {
        return s28;
    }

    public void setIt_observaciones(RichInputText it1) {
        this.it_observaciones = it1;
    }

    public RichInputText getIt_observaciones() {
        return it_observaciones;
    }

    public void setPgl17(RichPanelGroupLayout pgl17) {
        this.pgl17 = pgl17;
    }

    public RichPanelGroupLayout getPgl17() {
        return pgl17;
    }

    public List getListaDeptosPredio() {
        return listaDeptosPredio;
    }

    public void setListaDeptosPredio(List listaDeptosPredio) {
        this.listaDeptosPredio = listaDeptosPredio;
    }

    public List getListaMunPredio() {
        return listaMunPredio;
    }

    public void setListaMunPredio(List listaMunPredio) {
        this.listaMunPredio = listaMunPredio;
    }

    public List getListaDeptosRepresentante() {
        return listaDeptosRepresentante;
    }

    public void setListaDeptosRepresentante(List listaDeptosRepresentante) {
        this.listaDeptosRepresentante = listaDeptosRepresentante;
    }

    public List getListaMunRepresentante() {
        return listaMunRepresentante;
    }

    public void setListaMunRepresentante(List listaMunRepresentante) {
        this.listaMunRepresentante = listaMunRepresentante;
    }

    public void soc_depto_predio_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try{
            if(depto!=null){
                this.listaMunPredio = this.cargarDivipola(depto.getId());                
            }else{
                this.listaMunPredio = new ArrayList();    
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio_predio);
    }

    public void soc_repl_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try{
            if(depto!=null){
                this.listaMunRepresentante = this.cargarDivipolaSinRestriccion(depto.getId());                
            }else{
                this.listaMunRepresentante= new ArrayList();    
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_repl_municipio);
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }

    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public void setSoc_tipo_centro_poblado(RichSelectOneChoice soc1) {
        this.soc_tipo_centro_poblado = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_centro_poblado() {
        return soc_tipo_centro_poblado;
    }

    public void setSi16(UISelectItems si16) {
        this.si16 = si16;
    }

    public UISelectItems getSi16() {
        return si16;
    }

    public void setSoc_mun_tipo_suelo(RichSelectOneChoice soc1) {
        this.soc_mun_tipo_suelo = soc1;
    }

    public RichSelectOneChoice getSoc_mun_tipo_suelo() {
        return soc_mun_tipo_suelo;
    }

    public void setSi17(UISelectItems si17) {
        this.si17 = si17;
    }

    public UISelectItems getSi17() {
        return si17;
    }

    public void setPfl9(RichPanelFormLayout pfl9) {
        this.pfl9 = pfl9;
    }

    public RichPanelFormLayout getPfl9() {
        return pfl9;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setOl2(RichOutputLabel ol2) {
        this.ol2 = ol2;
    }

    public RichOutputLabel getOl2() {
        return ol2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setS30(RichSpacer s30) {
        this.s30 = s30;
    }

    public RichSpacer getS30() {
        return s30;
    }

    public void setIt_area(RichInputText it_area) {
        this.it_area = it_area;
    }

    public RichInputText getIt_area() {
        return it_area;
    }

    public void setItarea(RichInputText inputText1) {
        this.itarea = inputText1;
    }

    public RichInputText getItarea() {
        return itarea;
    }
}
