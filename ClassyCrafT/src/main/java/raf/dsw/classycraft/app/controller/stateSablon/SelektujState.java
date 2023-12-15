package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Connection;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ConnectionPainter;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;

import java.awt.*;

public class SelektujState implements State{
    private Point klikTacka;
    private boolean kliknutoNaElement = false;
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        //System.out.println("FUNKY");
        klikTacka = P;
        //dv.ukloniSveIzSelektovanih(); // prvo isprazni listu selektovani
        kliknutoNaElement = false;
        ElementPainter kliknut = null;
        for (ElementPainter ep:dv.getPainterList()) {
            if(ep.elementAt(P))
            {
                //dv.dodajUSelektovane(ep); // ako je kliknut neki element, dodace ga u listu
                kliknutoNaElement = true;
                kliknut = ep;
                System.out.println("naso selektovan "+ep.toString());
                //return;
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
        // ako nije kliknut nijedan element, lista je svakako prazna

        //dv.setSelektovan(null); //ako je kliknuo u prazno
        //jel treba ako se promeni stanje da nije nista  // nije mi jasno pitanje
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {
        System.out.println("usao u MIS POVUCEN");
        System.out.println("kliknutoNaElement : "+ kliknutoNaElement);
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

//                        int pomeraj_x = P.x - interclass.getPocetnaTacka().x;
//                        int pomeraj_y = P.y - interclass.getPocetnaTacka().y;
                        Point nova_tacka = new Point(interclass.getPocetnaTacka().x + pomeraj_x, interclass.getPocetnaTacka().y + pomeraj_y);
                        //klikTacka = P;
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
        dv.removeLasso();
    }
}
