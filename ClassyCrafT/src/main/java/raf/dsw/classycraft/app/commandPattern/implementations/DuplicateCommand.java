package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Enumeracija;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interclass;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Interfejs;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.Klasa;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;

public class DuplicateCommand extends AbstractCommand {

    private InterclassPainter interclassPainter;

    private Interclass novi_element;
    private DiagramView dv;

    public DuplicateCommand(DiagramView dv, Interclass interclass) {
        this.novi_element = interclass;
        this.dv = dv;
    }

    @Override
    public void doCommand() {

        if(novi_element instanceof Klasa)
        {
            interclassPainter=new KlasaPainter(novi_element);
        }
        else if(novi_element instanceof Interfejs)
        {
            interclassPainter=new InterfejsPainter(novi_element);
        }
        else if(novi_element instanceof Enumeracija) {
            interclassPainter = new EnumeracijaPainter(novi_element);
        }

        dv.addPainter(interclassPainter);
        interclassPainter.getDiagramElement().addSubscriber(dv);


        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        MainFrame.getInstance().getClassyTree().addDiagramElement(item, novi_element);
    }

    @Override
    public void undoCommand() {
        dv.getPainterList().remove(interclassPainter);
        dv.repaint();

        ClassyTreeView treeView= MainFrame.getInstance().getClassyTree().getTreeView();
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(interclassPainter.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
        item.removeFromParent();

        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
