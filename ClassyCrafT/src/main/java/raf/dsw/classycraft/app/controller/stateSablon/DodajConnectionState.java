package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import java.awt.*;

public class DodajConnectionState implements State{

    ElementPainter odakle=null;
    ElementPainter dokle=null;

    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        System.out.println("detektovao pritisak misa "+P.toString());
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                System.out.println("naso kliknutu "+P.toString());
                if(odakle==null) {
                    odakle = ep;
                    dv.setP1(P);
                    System.out.println("uso postavio pocetnu "+P.toString());

                }
                else
                {
                    dokle = ep;
                    //ovde zavrsava crtanje i poziva connectionpainter
                    System.out.println("naso dokle ide linija "+P.toString());
                    dv.setP1(null);
                    dv.setP2(null);
                }
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

    }
}
