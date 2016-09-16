package co.gov.ideam.sirh.view.util;


import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
/**
 * Utilizado para generar los arboles de opciones que se muestran en el Bean
 * de perfiles
 */
public class SpecialTreeModel extends ChildPropertyTreeModel {
    public SpecialTreeModel() {
    }
    public SpecialTreeModel(java.lang.Object p1, java.lang.String p2) {
        super(p1, p2);
   }

    // indicates whether the getRowData() node is a container element i.e. could ever contain children
    public boolean isContainer() {
        TreeNode node = (TreeNode)getRowData();
            return node.getChildCount()>0;
        }

}

