package co.gov.ideam.sirh.view;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

public class DashBoardBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1_2;
    private RichPanelDashboard pd1;
    private RichPanelGroupLayout pgl1_;
    private RichOutputText ot1_;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl3;
    
    private List listaGraficas;
    private RichSelectManyListbox sml1;
    private UISelectItems si1;
    private RichOutputText ot1;
    private RichPanelBox pb2;
    private RichOutputText ot2;
    private RichPanelFormLayout pfl1;
    private RichPanelBox pb3;
    private RichOutputText ot3;

    public DashBoardBean(){
        listaGraficas = new ArrayList();
        TipoGraficoTO grafico1 = new TipoGraficoTO("Usuarios por Departamento",
                                                   this.pb1);
        SelectItem item = new SelectItem(grafico1, grafico1.getNombre());
        listaGraficas.add(item);

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

    public void setPsl1_2(RichPanelStretchLayout psl1) {
        this.psl1_2 = psl1;
    }

    public RichPanelStretchLayout getPsl1_2() {
        return psl1_2;
    }

    public void setPd1(RichPanelDashboard pd1) {
        this.pd1 = pd1;
    }

    public RichPanelDashboard getPd1() {
        return pd1;
    }

    public void setPgl1_(RichPanelGroupLayout pgl1) {
        this.pgl1_ = pgl1;
    }

    public RichPanelGroupLayout getPgl1_() {
        return pgl1_;
    }

    public void setOt1_(RichOutputText ot1) {
        this.ot1_ = ot1;
    }

    public RichOutputText getOt1_() {
        return ot1_;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public List getListaGraficas() {
        return listaGraficas;
    }

    public void setListaGraficas(List listaGraficas) {
        this.listaGraficas = listaGraficas;
    }

    public void setSml1(RichSelectManyListbox sml1) {
        this.sml1 = sml1;
    }

    public RichSelectManyListbox getSml1() {
        return sml1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

   
}
