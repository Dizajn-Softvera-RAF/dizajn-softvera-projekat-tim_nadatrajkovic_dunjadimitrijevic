package raf.dsw.classycraft.app.controller;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
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

        //System.out.println("e: "+ e.);
        MainFrame.getInstance().getActionManager().getAddNodeAction().setType(t);
        f.setVisible(false);

    }
}
