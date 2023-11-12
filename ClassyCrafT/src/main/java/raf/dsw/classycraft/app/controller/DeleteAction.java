package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractClassyAction{

    public DeleteAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/kanta.png"));
        putValue(NAME, "Delete Node");
        putValue(SHORT_DESCRIPTION, "Delete Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().getSelectedNode();
        item.removeAllChildren();
        item.removeFromParent();


    }
}
