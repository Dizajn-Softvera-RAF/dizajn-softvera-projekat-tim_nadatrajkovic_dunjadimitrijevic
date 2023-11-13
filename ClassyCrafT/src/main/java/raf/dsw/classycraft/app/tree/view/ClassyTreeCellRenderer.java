package raf.dsw.classycraft.app.tree.view;

import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        // todo staviti ikonice za cvorove u stablu
//        if (((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer) {
//            imageURL = getClass().getResource("/images/tdiagram.gif");
//        }
//        else if (((ClassyTreeItem)value).getClassyNode() instanceof Project) {
//            imageURL = getClass().getResource("/images/tproject.gif");
//        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }

    @Override
    public void remove(Component comp) {
        super.remove(this);
    }
}
