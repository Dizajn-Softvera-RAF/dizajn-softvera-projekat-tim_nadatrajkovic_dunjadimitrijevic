package raf.dsw.classycraft.app.controller;

import javafx.util.Pair;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
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
        if(e.getClickCount() == 2) {
            ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
            if (selected.getClassyNode() instanceof Package) {
                //selected.openPackage();

////                ArrayList<ClassyNode> package_children = new ArrayList<>();
////                MainFrame.getInstance().getPackageView().getTabs().removeAll();
////
////                package_children = ((Package) selected.getClassyNode()).getChildren();
////                ClassyNode node = selected.getClassyNode();
////                while (!(node instanceof Project)) {
////                    node = node.getParent();
////                }
//
//                node.addSubscriber(MainFrame.getInstance().getPackageView()); // jel ok ovo ovako da uradimo?
//                node.notifySubscribers(node.getName());
//                node.notifySubscribers(new Pair("", ((Project) node).getImeAutora()));

                MainFrame.getInstance().getPackageView().setPaket((Package)(selected.getClassyNode()));
                selected.getClassyNode().addSubscriber(MainFrame.getInstance().getPackageView());

//                if(package_children.isEmpty())
//                {
//                    return;
//                }

//                for (ClassyNode child : package_children) {
//                    if (child instanceof Diagram) {
//
//                        DiagramView dw = new DiagramView((Diagram)child);
//                        selected.getClassyNode().notifySubscribers(child); // sluzi da se doda novi DW u PakcageViewu u TabbedPaneu
//                        //MainFrame.getInstance().getPackageView().getTabs().addTab(child.getName(), dw);
//                        child.addSubscriber(dw);
//                    }
//                }
                //selected.getClassyNode().notifySubscribers(new Notification((Package)selected.getClassyNode(), NotificationType.SHOW)); // ovo ne ovde, nego npr u addChild u Package
            }
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
