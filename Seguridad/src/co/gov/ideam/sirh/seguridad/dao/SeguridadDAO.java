package co.gov.ideam.sirh.seguridad.dao;

import co.gov.ideam.sirh.seguridad.model.PerfilVO;

import co.gov.ideam.sirh.util.IdeamException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sentencias SQL que no pueden ser ejecutadas via JPA
 */
public class SeguridadDAO {
    /**
     * Retorna un Texto con todos los permisos de menu y opciones que se encuentran
     * habilitadas para el perfil recibido como parametro.
     * La consulta se realizapor medio de connect by, sentencia propia en la
     * base de datos Oracle
     * @param perfil
     * @param con
     * @return
     * @throws FenixException
     */
    public String getPermisosTexto(PerfilVO perfil, Connection con)throws IdeamException{
        String sql = "select lpad(' ',4*(level-1)) || to_char(nombre) s " + 
        "from ( " + 
        "select  menu_codigo as codigo, menu_nombre as nombre, menu_padre as padre " + 
        "from    sast_menu m, sast_menu_grupo g " + 
        "where   m.menu_codigo = g.megr_menu_codigo " + 
        "and     g.megr_grps_codigo = ? " + 
        "union " + 
        "select  opc_codigo as codigo, opc_nombre as nombre, opc_codigomenu as padre " + 
        "from    sast_opciones o, sast_menu_opciones m " + 
        "where   o.opc_codigo = m.meop_opc_codigo " + 
        "and     m.meop_grps_codigo = ? " + 
        ") " + 
        "  start with padre is null " + 
        "  connect by prior codigo = padre";

        PreparedStatement statement = null;
        ResultSet query = null;
        String data = "";
        try {
            statement = con.prepareStatement(sql);
            statement.setLong(1, perfil.getCodigo());
            statement.setLong(2, perfil.getCodigo());
            query = statement.executeQuery();
            while(query.next()){
                data += query.getString(1) + "\n";
            }
            return data;
        }catch (SQLException e) {
            throw new IdeamException(e);
        }finally{
            try{
                if (query!=null){
                    query.close();
                }
                if (statement!=null){
                    statement.close();
                }
            }catch(SQLException e){
                throw new IdeamException(e);
            }
        }
    }
}
