package raf.dsw.classycraft.app.controller.stateSablon;

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
        //System.out.println("zasto ne detektuje ovo "+P.toString());

        dv.setP2(P);
        dv.repaint();


        //System.out.println("detektuje pomeranje misa "+P.toString());

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



                double minRastojanje = Integer.MAX_VALUE;


                    // todo srediti i otkomentarisati posle da mogu da se crtaju veze
//                for (Point po : odakle.getConnectionPoints()) {
//                    for (Point pd : dokle.getConnectionPoints()) {
//                        if (udaljenost(po, pd) < minRastojanje) {
//                            odP = po;
//                            doP = pd;
//                            minRastojanje=udaljenost(po,pd);
//                        }
//                    }
//
//                }

                break;
            }
        }

        if(nasoKliknutu) {

            Object[] options = {"Agregacia", "Generalizacija", "Kompozicija", "Zavisnost"};
            int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Izaberi vrstu veze", "Nova veza", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            ClassyTreeItem item = MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(), MainFrame.getInstance().getClassyTree().getRoot());

            if (choice == 0)//agregacija
            {
                Agregacija agregacija = new Agregacija("agregacija", dv.getDiagram(), interod, interdo, odP, doP);

                AgregacijaPainter apainter = new AgregacijaPainter(agregacija);

                dv.addPainter(apainter);
                agregacija.addSubscriber(dv);
                MainFrame.getInstance().getClassyTree().addDiagramElement(item, agregacija);

            }
            if (choice == 1)//generalizacija
            {
                Generalizacija generalizacija = new Generalizacija("generalizacija", dv.getDiagram(), interod, interdo, odP, doP);

                GeneralizacijaPainter apainter = new GeneralizacijaPainter(generalizacija);

                dv.addPainter(apainter);
                generalizacija.addSubscriber(dv);
                MainFrame.getInstance().getClassyTree().addDiagramElement(item, generalizacija);
            }
            if (choice == 2)//komozicija
            {
                Kompozicija kompozicija = new Kompozicija("kompozicija", dv.getDiagram(), interod, interdo, odP, doP);

                KompozicijaPainter kpainter = new KompozicijaPainter(kompozicija);

                dv.addPainter(kpainter);
                kompozicija.addSubscriber(dv);
                MainFrame.getInstance().getClassyTree().addDiagramElement(item, kompozicija);
            }
            if (choice == 3)//zavisnost
            {
                Zavisnost zavisnost = new Zavisnost("zavisnost", dv.getDiagram(), interod, interdo, odP, doP);

                ZavisnostPainter kpainter = new ZavisnostPainter(zavisnost);

                dv.addPainter(kpainter);
                zavisnost.addSubscriber(dv);
                MainFrame.getInstance().getClassyTree().addDiagramElement(item, zavisnost);
            }
        }

        dv.setP1(null);
        dv.setP2(null);



    }

    private double udaljenost(Point p1, Point p2)
    {

        double a2=(p1.x-p2.x)*(p1.x-p2.x);
        double b2=(p1.y-p2.y)*(p1.y-p2.y);
        System.out.println("udaljenost "+p1+" "+p2+" "+Math.sqrt(a2+b2));
        return Math.sqrt(a2+b2);
    }
}