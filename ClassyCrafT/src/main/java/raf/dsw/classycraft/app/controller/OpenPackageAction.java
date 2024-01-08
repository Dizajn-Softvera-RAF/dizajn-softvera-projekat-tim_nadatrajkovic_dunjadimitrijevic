package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OpenPackageAction implements MouseListener {
    public OpenPackageAction() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
            if (selected.getClassyNode() instanceof Package) {
                MainFrame.getInstance().getPackageView().setPaket((Package)(selected.getClassyNode()));
                selected.getClassyNode().addSubscriber(MainFrame.getInstance().getPackageView());
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
