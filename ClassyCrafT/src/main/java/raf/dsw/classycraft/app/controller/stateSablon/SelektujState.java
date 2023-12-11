package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.DiagramElement;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import java.awt.*;

public class SelektujState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        dv.ukloniSveIzSelektovanih(); // prvo isprazni listu selektovani
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                dv.dodajUSelektovane(ep); // ako je kliknut neki element, dodace ga u listu
                System.out.println("naso selektovan "+ep.toString());
                return;
            }
        }
        // ako nije kliknut nijedan element, lista je svakako prazna

        //dv.setSelektovan(null); //ako je kliknuo u prazno
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
