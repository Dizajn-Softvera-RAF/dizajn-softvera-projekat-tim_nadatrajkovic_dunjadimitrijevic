package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import java.awt.*;

public class SelektujState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                dv.setSelektovan(ep);
                System.out.println("naso selektovan "+ep.toString());
                return;
            }
        }
        dv.setSelektovan(null); //ako je kliknuo u prazno
        //jel treba ako se promeni stanje da nije nista  // nije mi jasno pitanje
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        System.out.println("usao u MIS POVUCEN");
    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
