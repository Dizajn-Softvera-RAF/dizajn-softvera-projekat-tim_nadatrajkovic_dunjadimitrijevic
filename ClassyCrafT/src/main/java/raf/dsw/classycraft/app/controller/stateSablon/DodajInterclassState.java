package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.NodeType;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Enumeracija;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.InterclassVidljivost;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;

public class DodajInterclassState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {

        Object[] options = {"Klasa","Interfejs","Enum"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
                //MainFrame.getInstance(),"Izaberi klasu, interfejs ili enum", "Nova interklasa", JOptionPane., JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        if(choice == 0)//crta klasu
        {

            //System.out.println("item"+item.toString());
            System.out.println( dv.getDiagram().getChildren().toString()+" ovo su parentova deca");

            JTextField nazivtf=new JTextField();

            JTextArea poljata=new JTextArea();
            JTextArea metodeta=new JTextArea();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeKlase"),
                    nazivtf,
                    new JLabel("Polja, primer: private String ime"),
                    poljata,
                    new JLabel("Metode, primer: private int izracunaj(int a, int b)"),
                    metodeta
            };

            JOptionPane novaKlasa=new JOptionPane(inputs);
            novaKlasa.setSize(500,500);
            novaKlasa.createDialog(MainFrame.getInstance(),"nova klasa");
            novaKlasa.setVisible(true);

            int rez = novaKlasa.showConfirmDialog(MainFrame.getInstance(),inputs,"novaKlasa",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    /*,
                    "Complete the sentence:\n"
                            + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "ham");*/


            //ako je s null proveri
            String s=nazivtf.getText();


            for (String line : poljata.getText().split("\\n"))
            {


            }

            Klasa klasa=new Klasa(s,dv.getDiagram());
            KlasaPainter klasaPainter=new KlasaPainter(klasa,P);
            dv.addPainter(klasaPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, klasa);
            //return;
        }
        if(choice == 1)//crta interfejs
        {

            JTextField nazivtf=new JTextField();

            JTextArea metodeta=new JTextArea();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("ImeInterfejsa"),
                    nazivtf,
                    new JLabel("Metode, primer: private int izracunaj(int a, int b)"),
                    metodeta
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"novInterfejs",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            //ako je s null proveri
            String s=nazivtf.getText();


            Interfejs interfejs =new Interfejs(s,dv.getDiagram());
            interfejs.addClassContent(new Metoda("max", InterclassVidljivost.PRIVATE,"int"));
            interfejs.addClassContent(new Metoda("min", InterclassVidljivost.PUBLIC,"int"));
            InterfejsPainter interfejsPainter=new InterfejsPainter(interfejs,P);
            dv.addPainter(interfejsPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, interfejs);
            //return;
        }
        if(choice==2)
        {
            JTextField nazivtf=new JTextField();

            JTextField enumeracije=new JTextField();


            JComponent[] inputs = new JComponent[] {
                    new JLabel("Ime enumeracije"),
                    nazivtf,
                    new JLabel("upisi elemente enumeracije razdvojene zarezom"),
                    enumeracije
            };

            int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),inputs,"nova enumeracija",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

            //ako je s null proveri
            String s=nazivtf.getText();

            Enumeracija enumeracija =new Enumeracija(s,dv.getDiagram());

            String[] tipoviEnum=enumeracije.getText().split(",");
            for (String jedanEnum: tipoviEnum)
            {
                ClanEnumeracije clan=new ClanEnumeracije(jedanEnum);
                enumeracija.addClassContent(clan);
            }

            EnumeracijaPainter enumeracijaPainter=new EnumeracijaPainter(enumeracija,P);
            dv.addPainter(enumeracijaPainter);

            //nodeType = "Package";
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, enumeracija);
        }

    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
