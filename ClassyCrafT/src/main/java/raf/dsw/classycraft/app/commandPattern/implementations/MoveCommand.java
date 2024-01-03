package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand {
    private DiagramView dv;
    private int pomeraj_x;
    private int pomeraj_y;
    private boolean move;
    private List<ElementPainter> listMoved;

    public MoveCommand(DiagramView dv, int pomeraj_x, int pomeraj_y) {
        this.dv = dv;
        this.pomeraj_x = pomeraj_x;
        this.pomeraj_y = pomeraj_y;
        listMoved = new ArrayList<>();
        for(ElementPainter ep : dv.getSelektovaniList())
        {
            if(ep instanceof InterclassPainter)
            {
                Interclass interclass = (Interclass)ep.getDiagramElement();

                Point nova_tacka = new Point(interclass.getPocetnaTacka().x - pomeraj_x, interclass.getPocetnaTacka().y - pomeraj_y);
                interclass.setPocetnaTacka(nova_tacka);

            }
            listMoved.add(ep);
        }
    }

    @Override
    public void doCommand() {
        for(ElementPainter ep : listMoved)
        {
            if(ep instanceof InterclassPainter)
            {
                Interclass interclass = (Interclass)ep.getDiagramElement();

                Point nova_tacka = new Point(interclass.getPocetnaTacka().x + pomeraj_x, interclass.getPocetnaTacka().y + pomeraj_y);
                interclass.setPocetnaTacka(nova_tacka);
            }
        }
    }

    @Override
    public void undoCommand() {
        for(ElementPainter ep : listMoved)
        {
            if(ep instanceof InterclassPainter)
            {
                Interclass interclass = (Interclass)ep.getDiagramElement();

                Point nova_tacka = new Point(interclass.getPocetnaTacka().x - pomeraj_x, interclass.getPocetnaTacka().y - pomeraj_y);
                interclass.setPocetnaTacka(nova_tacka);
            }
        }
    }
}
