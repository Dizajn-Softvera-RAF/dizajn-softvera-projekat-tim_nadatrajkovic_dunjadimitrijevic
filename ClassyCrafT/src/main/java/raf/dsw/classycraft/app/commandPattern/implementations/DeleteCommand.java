package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand {

    private DiagramView dv;
    private List<ElementPainter> dvPainteriSelektovani;

    public DeleteCommand(DiagramView dv) {
        this.dv = dv;
        dvPainteriSelektovani=dv.getSelektovaniList();
    }

    @Override
    public void doCommand() {
        for(ElementPainter ep : dvPainteriSelektovani)
        {
            dv.getPainterList().remove(ep);
            dv.repaint();

            ClassyTreeView treeView= MainFrame.getInstance().getClassyTree().getTreeView();
            ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(ep.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
            item.removeFromParent();

            SwingUtilities.updateComponentTreeUI(treeView);

        }
        dv.removeLasso();
    }

    @Override
    public void undoCommand() {
        for(ElementPainter ep : dvPainteriSelektovani)
        {
            //nacrta ih
            dv.addPainter(ep);
            ep.getDiagramElement().addSubscriber(dv);


            ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
            MainFrame.getInstance().getClassyTree().addDiagramElement(item, ep.getDiagramElement());


        }

    }
}
