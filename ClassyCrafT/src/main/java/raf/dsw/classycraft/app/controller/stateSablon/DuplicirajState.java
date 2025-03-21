package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.commandPattern.implementations.DuplicateCommand;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.*;

import javax.swing.*;
import java.awt.*;

public class DuplicirajState implements State{
    private Interclass duplicirajElement(DiagramElement de, Point P)
    {
        Interclass novi_element = null;
        if(de instanceof Klasa)
        {
            novi_element = new Klasa(de.getName(), de.getParent(), P);

        }
        else if(de instanceof Interfejs)
        {
            novi_element = new Interfejs(de.getName(), de.getParent(), P);

        }
        else if(de instanceof Enumeracija)
        {
            novi_element = new Enumeracija(de.getName(), de.getParent(), P);
        }
        if(novi_element != null)
        {
            for (ClassContent classContent : ((Interclass) de).getSadrzaj()) {
                novi_element.addClassContent(classContent);
            }
        }

        return novi_element;
    }
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        if(!dv.getSelektovaniList().isEmpty())
        {
            dv.ukloniSveIzSelektovanih();
        }
        if(dv.getSelektovaniList().isEmpty())
        {
            ElementPainter kliknut = null;
            for (ElementPainter ep:dv.getPainterList()) {
                if(ep.elementAt(P))
                {
                    dv.dodajUSelektovane(ep); // ako je kliknut neki element, dodace ga u listu
                    kliknut = ep;
                    break;
                }
            }
            // ako kliknut != null && kliknut je Interclass -> dupliciraj ga
            if(kliknut instanceof InterclassPainter)
            {

                int rez = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Dupliciraj element "+ kliknut.getDiagramElement().getName()+ "?", "Potvrdi", JOptionPane.OK_CANCEL_OPTION);
                if(rez == 0) // ok -> dupliciraj element
                {
                    DiagramElement selectedInterclass = kliknut.getDiagramElement();
                    Interclass novi_element = duplicirajElement(selectedInterclass, P);

                    DuplicateCommand duplicateCommand = new DuplicateCommand(dv,novi_element);
                    dv.getCommandManager().addCommand(duplicateCommand);
                }
                else if(rez == 1)
                {
                    dv.ukloniSveIzSelektovanih();
                }
            }
        }
        dv.ukloniSveIzSelektovanih();
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
