package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.AddConnectionCommand;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.*;

import javax.swing.*;
import java.awt.*;

public class DodajConnectionState implements State{

    InterclassPainter odakle=null;
    InterclassPainter dokle=null;

    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        System.out.println("detektovao pritisak misa "+P.toString());
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep instanceof InterclassPainter && ep.elementAt(P))
            {
                System.out.println("naso kliknutu "+P.toString());
                odakle = (InterclassPainter) ep;
                dv.setP1(P);
                System.out.println("uso postavio pocetnu " + P.toString());


            }
        }
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        dv.setP2(P);
        dv.repaint();
    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {
        Point odP = null, doP = null;
        Interclass interod=null;
        Interclass interdo=null;

        boolean nasoKliknutu=false;

        System.out.println("detektovao otpusten misa "+P.toString());
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep instanceof InterclassPainter && ep.elementAt(P)) {
                nasoKliknutu=true;
                System.out.println("naso kliknutu " + P.toString());

                dokle = (InterclassPainter) ep;
                //ovde zavrsava crtanje i poziva connectionpainter
                System.out.println("naso dokle ide linija " + P.toString());

                //ovde sad pravi vezu

                interod = (Interclass) odakle.getDiagramElement();
                interdo = (Interclass) dokle.getDiagramElement();

                if(odakle == dokle)
                {
                    dv.setP1(null);
                    dv.setP2(null);
                    dv.repaint();
                    return;
                }
                break;
            }
        }

        Connection veza=null;

        if(nasoKliknutu) {

            Object[] options = {"Agregacia", "Generalizacija", "Kompozicija", "Zavisnost"};
            int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Izaberi vrstu veze", "Nova veza", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            ClassyTreeItem item = MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(), MainFrame.getInstance().getClassyTree().getRoot());

            if (choice == 0)//agregacija
            {
                veza = new Agregacija("agregacija", dv.getDiagram(), interod, interdo, odP, doP);
            }
            if (choice == 1)//generalizacija
            {
                veza = new Generalizacija("generalizacija", dv.getDiagram(), interod, interdo, odP, doP);
            }
            if (choice == 2)//komozicija
            {
                veza = new Kompozicija("kompozicija", dv.getDiagram(), interod, interdo, odP, doP);

            }
            if (choice == 3)//zavisnost
            {
                veza = new Zavisnost("zavisnost", dv.getDiagram(), interod, interdo, odP, doP);
            }
        }

        dv.setP1(null);
        dv.setP2(null);

        if(veza!=null) {
            ConnectionPainter connectionPainter=null;

            if(veza instanceof Agregacija)
            {
                connectionPainter = new AgregacijaPainter(veza);
            }
            if(veza instanceof Zavisnost)
            {
                connectionPainter = new ZavisnostPainter(veza);
            }
            if(veza instanceof Kompozicija)
            {
                connectionPainter = new KompozicijaPainter(veza);
            }
            if(veza instanceof Generalizacija)
            {
                connectionPainter = new GeneralizacijaPainter(veza);
            }


            AddConnectionCommand addConnectionCommand = new AddConnectionCommand(dv,veza,connectionPainter);

            dv.getCommandManager().addCommand(addConnectionCommand);
            //ovde dodamo u dv-u kao addCommand
        }

    }
}