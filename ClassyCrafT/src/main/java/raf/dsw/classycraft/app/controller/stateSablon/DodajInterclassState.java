package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

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

            ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
            //System.out.println("item"+item.toString());
            System.out.println( dv.getDiagram().getChildren().toString()+" ovo su parentova deca");

            JTextField nazivtf=new JTextField();

            JTextArea poljata=new JTextArea();
            JTextArea metodeta=new JTextArea();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeKlase"),
                    nazivtf,
                    new JLabel("Polja"),
                    poljata,
                    new JLabel("Metode"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION);

            if(rez == 0)
            {

                System.out.println("kliknut OK");
                //ako je s null proveri

                String s=nazivtf.getText();
                if(s == null || s.equals(""))
                {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("potrebno je uneti ime klase", MessageType.WARNING);
                    // jos nesto?
                }
                else
                {
                    Klasa klasa=new Klasa(s,dv.getDiagram());
                    KlasaPainter klasaPainter=new KlasaPainter(klasa,P);
                    dv.addPainter(klasaPainter);

                    //nodeType = "Package";
                    MainFrame.getInstance().getClassyTree().addDiagramElement(item, klasa);
                    //return;
                }

            }
            else if(rez == 1)
            {
                // ako je kliknut cancel - onda nista
            }

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
