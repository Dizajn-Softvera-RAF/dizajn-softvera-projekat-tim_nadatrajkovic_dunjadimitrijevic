package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DodajInterclassState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {

        Object[] options = {"Klasa","Interfejs","Enum"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
                //MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa", JOptionPane., JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        if(choice == 0)
        {
            Klasa klasa=new Klasa("novo ime",dv.getDiagram());
            ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
            System.out.println("kao dodao klasu");
            System.out.println( dv.getDiagram().getChildren().toString()+" ovo su parentova deca");
            //nodeType = "Package";
            //MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.PACKAGE);
            //return;
        }
        if(choice == 1)
        {
            //MainFrame.getInstance().getClassyTree().addChild(selected, NodeType.DIAGRAM);
            return;
        }
        if(choice==2)
        {
            return;
        }

    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
