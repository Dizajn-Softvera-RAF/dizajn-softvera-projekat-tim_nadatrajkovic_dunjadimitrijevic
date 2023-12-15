package raf.dsw.classycraft.app.controller.stateSablon;

import javafx.scene.control.TreeView;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.ElementPainter;

import javax.swing.*;
import java.awt.*;

public class ObrisiState implements State{
    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        for (ElementPainter p:dv.getPainterList())
        {
            if(p.elementAt(P))
            {
                dv.getPainterList().remove(p);
                dv.repaint();

                ClassyTreeView treeView=MainFrame.getInstance().getClassyTree().getTreeView();
                ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(p.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
                item.removeFromParent();

                SwingUtilities.updateComponentTreeUI(treeView);

                break;
            }

        }

    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
