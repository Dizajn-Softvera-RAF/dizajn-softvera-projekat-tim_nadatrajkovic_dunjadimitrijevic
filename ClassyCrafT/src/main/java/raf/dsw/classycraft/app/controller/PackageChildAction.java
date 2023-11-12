package raf.dsw.classycraft.app.controller;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
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
    public PackageChildAction(JRadioButton jpackage, JRadioButton diagram, JDialog dialog) {
        p = jpackage;
        d = diagram;
        f = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NodeType t;
        if(d.isSelected())
        {
            t = NodeType.DIAGRAM;
        }
        else
            t = NodeType.PACKAGE;
        System.out.println("usao u OK action performed");
        System.out.println("t: "+ t);
        ((ClassyRepositoryImplementation)ApplicationFramework.getInstance().getClassyRepository()).setSelectedPackageChild(t);
        //f.setVisible(false);
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().addChild(selected, t);
        f.dispose();
        f.setVisible(false);
        //f.revalidate();
        //f.repaint();
    }
}
