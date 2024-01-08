package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.*;

import javax.swing.*;

public class AddConnectionCommand extends AbstractCommand {

    private DiagramView dv;
    private ConnectionPainter connectionPainter;
    private Connection connection;

    public AddConnectionCommand(DiagramView dv, Connection connection,ConnectionPainter connectionPainter) {
        this.dv = dv;
        this.connection = connection;
        this.connectionPainter=connectionPainter;
    }

    @Override
    public void doCommand() {
        dv.addPainter(connectionPainter);
        connection.addSubscriber(dv);

        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        MainFrame.getInstance().getClassyTree().addDiagramElement(item, connection);
    }

    @Override
    public void undoCommand() {

        dv.getPainterList().remove(connectionPainter);
        dv.repaint();

        ClassyTreeView treeView=MainFrame.getInstance().getClassyTree().getTreeView();
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(connectionPainter.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
        item.removeFromParent();

        SwingUtilities.updateComponentTreeUI(treeView);

    }
}
