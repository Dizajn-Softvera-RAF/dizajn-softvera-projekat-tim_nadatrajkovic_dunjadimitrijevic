package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;

public class EditInterclassCommand extends AbstractCommand {
    private DiagramView dv;
    private Interclass originalInterclass;
    private Interclass changedInterclass;
    private InterclassPainter theInterclass;
    public EditInterclassCommand(DiagramView dv, Interclass originalInterclass, Interclass changedInterclass, InterclassPainter theInterclass) {
        this.dv = dv;
        this.originalInterclass = originalInterclass;
        this.changedInterclass = changedInterclass;
        this.theInterclass = theInterclass;
    }

    @Override
    public void doCommand() {
        theInterclass.getDiagramElement().setName(changedInterclass.getName());
        ((Interclass)theInterclass.getDiagramElement()).setSadrzaj(changedInterclass.getClassContent());
        dv.repaint();
    }

    @Override
    public void undoCommand() {
        theInterclass.getDiagramElement().setName(originalInterclass.getName());
        ((Interclass)theInterclass.getDiagramElement()).setSadrzaj(originalInterclass.getClassContent());
        dv.repaint();
    }
}
