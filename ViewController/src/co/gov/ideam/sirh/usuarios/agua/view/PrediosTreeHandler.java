package co.gov.ideam.sirh.usuarios.agua.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.component.core.data.CoreTree;
import org.apache.myfaces.trinidad.model.TreeModel;

public class PrediosTreeHandler {
    private List focusRowKey = new ArrayList();


    private Object selectedNode;
    private TreeModel treemodel;
    private CoreTree jsfTree;

    public PrediosTreeHandler() {
    }

    public void setSelectedNode(Object selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Object getSelectedNode() {
        return selectedNode;
    }

    public void setTreemodel(TreeModel treemodel) {
        this.treemodel = treemodel;
    }

    public TreeModel getTreemodel() {
        return treemodel;
    }

    public void setJsfTree(CoreTree jsfTree) {
        this.jsfTree = jsfTree;
    }

    public CoreTree getJsfTree() {
        return jsfTree;
    }

    public void setFocusRowKey(List focusRowKey) {
        this.focusRowKey = focusRowKey;
    }

    public List getFocusRowKey() {
        return focusRowKey;
    }
}