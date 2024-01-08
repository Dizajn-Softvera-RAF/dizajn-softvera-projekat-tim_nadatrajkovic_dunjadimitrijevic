package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractClassyAction{
    private NodeType type;
    public AddNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Add");
        putValue(SHORT_DESCRIPTION, "Add node");
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("izabrani cvor nije izabran", MessageType.ERROR);
            return;
        }

        if(selected.getClassyNode() instanceof ClassyNodeComposite && selected.getClassyNode() instanceof Package)
        {
            Object[] options = {"New Package", "New Diagram"};
            int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi paket ili diagram", "Add to package", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
            if(choice == 0)
            {
                MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.PACKAGE);
                return;
            }
            if(choice == 1)
            {
                MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.DIAGRAM);
            }
        }
        else if(selected.getClassyNode() instanceof Diagram)
        {
            return;
        }
        else
        {
            MainFrame.getInstance().getClassyTree().addChild(selected, type);
        }
    }
}
