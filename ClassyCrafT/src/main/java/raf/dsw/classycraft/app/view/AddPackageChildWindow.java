package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.controller.PackageChildAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;

import javax.swing.*;
import java.awt.*;

public class AddPackageChildWindow extends JDialog{
    JRadioButton r1;
    JRadioButton r2;
    ButtonGroup bg;
    JButton ok;
    public AddPackageChildWindow(JFrame parent){
        super(parent, true);
        //((ClassyRepositoryImplementation)ApplicationFramework.getInstance().getClassyRepository()).setSelectedPackageChild(null);
        r1=new JRadioButton("New Package");
        r1.setSelected(true);
        r2=new JRadioButton("New Diagram");
        r1.setBounds(75,50,200,30);
        r2.setBounds(75,100,200,30);
        ok = new JButton("OK");
        ok.setVisible(true);
        ok.setSize(50,50);

        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        //bg.clearSelection();
        //bg.setSelected(r1);
        this.add(r1);
        this.add(r2);
//        panel.add(r1);
//        panel.add(r2);
        PackageChildAction buttonListener = new PackageChildAction(r1, r2, this);
        ok.addActionListener(buttonListener);
        ok.setBounds(50, 50, 100, 50);
        this.add(ok);
        //panel.add(ok);
        this.setSize(300,300);
        //this.setLayout(null);
        System.out.println("usao u pcwindow konstruktor");
    }

    public JRadioButton getR1() {
        return r1;
    }

    public JRadioButton getR2() {
        return r2;
    }

    public ButtonGroup getBg() {
        return bg;
    }
}
