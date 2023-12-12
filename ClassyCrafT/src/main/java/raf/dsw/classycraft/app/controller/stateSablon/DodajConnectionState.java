package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Agregacija;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Connection;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.AgregacijaPainter;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;

import javax.swing.*;
import java.awt.*;

public class DodajConnectionState implements State{

    InterclassPainter odakle=null;
    InterclassPainter dokle=null;
    boolean otpusten=false;

    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        otpusten=false;
        System.out.println("detektovao pritisak misa "+P.toString());
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep instanceof InterclassPainter && ep.elementAt(P))
            {
                System.out.println("naso kliknutu "+P.toString());
                if(odakle==null) {
                    odakle = (InterclassPainter) ep;
                    dv.setP1(P);
                    System.out.println("uso postavio pocetnu " + P.toString());
                }

            }
        }
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        //System.out.println("zasto ne detektuje ovo "+P.toString());
        if(!otpusten)
        {
            dv.setP2(P);
            dv.repaint();
        }

        //System.out.println("detektuje pomeranje misa "+P.toString());

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {
        otpusten=true;

        System.out.println("detektovao otpusten misa "+P.toString());
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep instanceof InterclassPainter && ep.elementAt(P))
            {
                System.out.println("naso kliknutu "+P.toString());

                dokle =(InterclassPainter) ep;
                //ovde zavrsava crtanje i poziva connectionpainter
                System.out.println("naso dokle ide linija "+P.toString());

                //ovde sad pravi vezu


                Interclass interod=(Interclass) odakle.getDiagramElement();
                Interclass interdo=(Interclass) dokle.getDiagramElement();

                Point odP=null,doP=null;

                double minRastojanje=Integer.MAX_VALUE;

                for (Point po:odakle.getConnectionPoints())
                {
                    for (Point pd:dokle.getConnectionPoints())
                    {
                        if(udaljenost(po,pd)<minRastojanje)
                        {
                            odP=po;
                            doP=pd;
                        }
                    }

                }

                Object[] options = {"Agregacia","Generalizacija","Kompozicija","Zavisnost"};
                int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(),"Izaberi vrstu veze", "Nova veza",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
                ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
                if(choice==0)//agregacija
                {
                    Agregacija agregacija=new Agregacija("agregacija",dv.getDiagram(),interod,interdo);

                    AgregacijaPainter apainter=new AgregacijaPainter(agregacija,odP,doP);


                    dv.addPainter(apainter);
                    MainFrame.getInstance().getClassyTree().addDiagramElement(item, agregacija);

                }
                if(choice==1)//generalizacija
                {

                }
                if(choice==2)//komozicija
                {

                }
                if(choice==3)//zavisnost



                dv.setP1(null);
                dv.setP2(null);

            }
        }
    }

    private double udaljenost(Point p1, Point p2)
    {
        double a2=(p1.x-p2.x)*(p1.x-p2.x);
        double b2=(p1.y-p2.y)*(p1.y-p2.y);
        return Math.sqrt(a2+b2);
    }
}