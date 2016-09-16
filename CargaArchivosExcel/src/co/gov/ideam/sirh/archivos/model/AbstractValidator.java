package co.gov.ideam.sirh.archivos.model;


import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;


public abstract class AbstractValidator {
        
    protected List<ColumnTO> columnas;    
        
    public Object getValue(HSSFRow row, ColumnTO col){
        if(col==null){
            throw new RuntimeException("Columna no puede ser nula");
        }
        if(row == null){
            throw new RuntimeException("Row no puede ser nula");
        }
        
        HSSFCell cell = row.getCell(col.getIndice());                
        
        if(cell==null){
            //System.out.println( "Cell no puede ser nula " + col.getIndice() );
            return "";
        }
        
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){        
            String value = cell.getStringCellValue();
            return value;
        }else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ 
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
                Date date = cell.getDateCellValue();
                return sdf.format(date);
            }else{
                double valor = cell.getNumericCellValue();
                return BigDecimal.valueOf(valor).toPlainString();
            }
        }
        return "";
    }
    
    public Object getValue(XSSFRow row, ColumnTO col){
        if(col==null){
            throw new RuntimeException("Columna no puede ser nula");
        }
        if(row == null){
            throw new RuntimeException("Row no puede ser nula");
        }
        
        XSSFCell cell = row.getCell(col.getIndice());                
        
        if(cell==null){
            //System.out.println( "Cell no puede ser nula " + col.getIndice() );
            return "";
        }
        
        if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){        
            String value = cell.getStringCellValue();
            return value;
        }else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){ 
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
                Date date = cell.getDateCellValue();
                return sdf.format(date);
            }else{
                double valor = cell.getNumericCellValue();
                return BigDecimal.valueOf(valor).toPlainString();
            }
        }
        return "";
    }
    
    public List<ColumnTO> getColumns(){
        return columnas;
    }
}
