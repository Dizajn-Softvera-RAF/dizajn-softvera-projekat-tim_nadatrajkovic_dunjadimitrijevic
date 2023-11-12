package raf.dsw.classycraft.app.controller;

import javafx.util.Pair;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OpenPackageAction implements MouseListener {
    public OpenPackageAction() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2)
        {
            ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
            ArrayList<ClassyNode> package_children = new ArrayList<>();
            MainFrame.getInstance().getPackageView().getTabs().removeAll();
            if(selected.getClassyNode() instanceof Package)
            {
                package_children = ((Package) selected.getClassyNode()).getChildren();

                ClassyNode node=selected.getClassyNode();
                while(!(node instanceof Project))
                {
                    node=node.getParent();
                }

                node.addSubscriber(MainFrame.getInstance().getPackageView());
                node.notifySubscribers(node.getName());
                node.notifySubscribers(new Pair("",((Project) node).getImeAutora()));


                selected.getClassyNode().addSubscriber(MainFrame.getInstance().getPackageView());
                //MainFrame.getInstance().getPackageView().setProjectNaziv(((Package)selected.getClassyNode()).getName());
            }
            if(package_children.isEmpty())
            {
                return;
            }
            //MainFrame.getInstance().getTabs().removeAll();
            for (ClassyNode child:package_children) {
                if(!(child instanceof Package)) {

                    DiagramView dw = new DiagramView();
                    selected.getClassyNode().notifySubscribers(child);
                    //MainFrame.getInstance().getPackageView().getTabs().addTab(child.getName(), dw);
                    ((Diagram)child).addSubscriber(dw);
                }

            }
            //MainFrame.getInstance().getTabs().addTab()
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
