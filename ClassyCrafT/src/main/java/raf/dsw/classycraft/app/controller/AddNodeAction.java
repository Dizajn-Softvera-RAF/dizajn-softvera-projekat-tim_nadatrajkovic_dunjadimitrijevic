package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddNodeAction extends AbstractClassyAction{
    public AddNodeAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Add");
        putValue(SHORT_DESCRIPTION, "Add node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().addChild(selected);
        //System.out.println("add node clicked");
    }
}
