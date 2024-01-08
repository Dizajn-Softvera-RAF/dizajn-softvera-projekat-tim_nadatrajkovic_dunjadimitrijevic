package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.MoveCommand;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;

import java.awt.*;

public class SelektujState implements State{
    private Point klikTacka;
    private Point pocKlikTacka;
    private boolean kliknutoNaElement = false;
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        klikTacka = P;
        pocKlikTacka = new Point(P);
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
            else
            {
                // pomeraj selektovane
                int pomeraj_x = P.x - klikTacka.x;
                int pomeraj_y = P.y - klikTacka.y;

                for(ElementPainter ep : dv.getSelektovaniList())
                {
                    if(ep instanceof InterclassPainter)
                    {
                        Interclass interclass = (Interclass)ep.getDiagramElement();

                        Point nova_tacka = new Point(interclass.getPocetnaTacka().x + pomeraj_x, interclass.getPocetnaTacka().y + pomeraj_y);
                        interclass.setPocetnaTacka(nova_tacka);
                    }
                }
                klikTacka.x += pomeraj_x;
                klikTacka.y += pomeraj_y;
            }
        }

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {
        if(kliknutoNaElement)
        {
            int pomeraj_x = P.x - pocKlikTacka.x;
            int pomeraj_y = P.y - pocKlikTacka.y;
            MoveCommand moveCommand = new MoveCommand(dv, pomeraj_x, pomeraj_y);
            dv.getCommandManager().addCommand(moveCommand);
        }

        dv.removeLasso();
    }
}
