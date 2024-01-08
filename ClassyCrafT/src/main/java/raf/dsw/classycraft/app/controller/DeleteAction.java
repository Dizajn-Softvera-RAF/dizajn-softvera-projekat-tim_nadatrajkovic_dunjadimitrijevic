package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction{

    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/kanta.png"));
        putValue(NAME, "Delete Node");
        putValue(SHORT_DESCRIPTION, "Delete Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem item= MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().removeChild(item);
    }
}