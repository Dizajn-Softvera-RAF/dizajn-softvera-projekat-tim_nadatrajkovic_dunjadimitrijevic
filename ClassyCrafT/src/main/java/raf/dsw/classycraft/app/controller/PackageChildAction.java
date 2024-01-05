package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageChildAction implements ActionListener {
    JRadioButton p;
    JRadioButton d;
    JDialog f;

    // mislim da nam sad ne treba ovaj action jer imamo joptionpane u AddNodeAction
    public PackageChildAction(JRadioButton jpackage, JRadioButton diagram, JDialog dialog) {
        p = jpackage;
        d = diagram;
        f = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedItem = (ClassyTreeItem)MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedItem == null)
        {

        }
        else
        {
            if((ClassyNodeComposite)selectedItem.getClassyNode() instanceof Package)
            {
                Object[] options = {"New Package", "New Diagram"};
                //String nodeType = "";
                int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi paket ili diagram", "Add to package", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
                if(choice == 0)
                {
                    //nodeType = "Package";
                    MainFrame.getInstance().getClassyTree().addChild(selectedItem, NodeType.PACKAGE);
                    return;
                }
                if(choice == 1)
                {
                    MainFrame.getInstance().getClassyTree().addChild(selectedItem, NodeType.DIAGRAM);
                    return;
                }
            }
        }

        NodeType t;
        if(d.isSelected())
        {
            t = NodeType.DIAGRAM;
        }
        else
            t = NodeType.PACKAGE;
        System.out.println("usao u OK action performed");
        System.out.println("t: "+ t);
        //((ClassyRepositoryImplementation)ApplicationFramework.getInstance().getClassyRepository()).setSelectedPackageChild(t);
        //f.setVisible(false);
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        f.dispose();
        f.setVisible(false);
        MainFrame.getInstance().getClassyTree().addChild(selected, t);

        //f.revalidate();
        //f.repaint();
    }
}
