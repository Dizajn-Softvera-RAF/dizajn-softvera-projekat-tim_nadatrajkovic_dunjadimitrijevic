package raf.dsw.classycraft.app.commandPattern.implementations;

import raf.dsw.classycraft.app.commandPattern.AbstractCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.diagramElementi.*;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClanEnumeracije;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Metoda;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;
import raf.dsw.classycraft.app.view.painteri.EnumeracijaPainter;
import raf.dsw.classycraft.app.view.painteri.InterclassPainter;
import raf.dsw.classycraft.app.view.painteri.InterfejsPainter;
import raf.dsw.classycraft.app.view.painteri.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddInterclassCommand extends AbstractCommand {

    private DiagramView dv;
    private Point P;

    private InterclassPainter interclassPainter;
    private Interclass interclass;



    public AddInterclassCommand(DiagramView dv, Interclass interclass, InterclassPainter interclassPainter) {
        this.dv = dv;
        this.interclass = interclass;
        this.interclassPainter=interclassPainter;
    }

    @Override
    public void doCommand() {
        dv.addPainter(interclassPainter);
        interclass.addSubscriber(dv);

        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(dv.getDiagram(),MainFrame.getInstance().getClassyTree().getRoot());
        MainFrame.getInstance().getClassyTree().addDiagramElement(item, interclass);
    }

    @Override
    public void undoCommand() {
        dv.getPainterList().remove(interclassPainter);
        dv.repaint();

        ClassyTreeView treeView=MainFrame.getInstance().getClassyTree().getTreeView();
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().NadjiClassyTreePrekoClassyNode(interclassPainter.getDiagramElement(),MainFrame.getInstance().getClassyTree().getRoot());
        item.removeFromParent();

        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
