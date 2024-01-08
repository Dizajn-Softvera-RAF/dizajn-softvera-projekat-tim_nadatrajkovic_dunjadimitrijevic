package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.DeleteCommand;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import java.awt.*;

public class ObrisiState implements State{
    private Point klikTacka;
    private boolean kliknutoNaElement = false;
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        klikTacka = P;
        kliknutoNaElement = false;
        ElementPainter kliknut = null;
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                kliknutoNaElement = true;
                kliknut = ep;
                break;
            }
        }

        if(!dv.getSelektovaniList().isEmpty())
        {
            if(dv.getSelektovaniList().contains(kliknut))
            {
                System.out.println("kliknut je medju selektovanim");
            }
            else
            {
                dv.ukloniSveIzSelektovanih();
            }
        }
        else
        {
            if(kliknut != null)
            {
                dv.dodajUSelektovane(kliknut); // ako je kliknut neki element, dodace ga u listu
            }
        }
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        if(klikTacka != null)
        {
            if(!kliknutoNaElement)
            {
                dv.drawLasso(klikTacka, P);
            }
        }
    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

        if(!dv.getSelektovaniList().isEmpty()) {
            DeleteCommand deleteCommand = new DeleteCommand(dv);
            dv.getCommandManager().addCommand(deleteCommand);
        }
    }
}
