package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RowTO implements Serializable{
    
    private List<CellTO> celdas;
    private int indice;
    private boolean valido;
    
    public RowTO() {
        this.celdas = new ArrayList<CellTO>();
    }
    public List getCeldas(){
        return celdas;
    }
    public void addCell(ColumnTO column, Object value, Autoridades autoridad){
        CellTO cell = new CellTO(column.getTitulo(), column.getIndice());
        cell.setValidadores( column.getValidadores() );
        cell.setValue(value);
        cell.validar(autoridad, this.celdas);
        if(!cell.isValido()){
            this.setValido( false );
        }
        this.celdas.add(cell);
    }
    public Object getCellValue(String columnName){
        ColumnTO col = new ColumnTO(columnName);
        return this.getCellValue(col);
    }
    
    public Object getCellValue(int indice){
        ColumnTO col = new ColumnTO("", indice);
        return this.getCellValue(col);
    }
    public Object getCellValue(ColumnTO column){
        CellTO cell = new CellTO(column.getTitulo(), column.getIndice());
        int pos = this.celdas.indexOf(cell);
        if (pos<0){
            throw new RuntimeException("No existe la celda " + 
                                       cell.getTitulo() + 
                                       " Indice " + 
                                       cell.getIndice());
        }
        CellTO existe = this.celdas.get(pos);
        return existe.getValue();
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            ((RowTO)obj).getIndice() == this.getIndice();
    }

    public boolean isValido() {
        boolean correcto = true;
        Iterator<CellTO> it = this.celdas.iterator();
        while(it.hasNext()){
            CellTO celda = it.next();
            if(!celda.isValido()){
                correcto = false;
                break;
            }
        }        
        return correcto;

    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }
    
    public String getMensaje(){
        String mensaje = "";
        Iterator<CellTO> it = this.celdas.iterator();
        while(it.hasNext()){
            CellTO celda = it.next();
            if(!celda.isValido()){
                mensaje += celda.getTitulo() + "\t" +
                        celda.getDetalleError();
            }
        }
        //System.out.println("Desde Row " + mensaje);
        return mensaje;
    }
}
