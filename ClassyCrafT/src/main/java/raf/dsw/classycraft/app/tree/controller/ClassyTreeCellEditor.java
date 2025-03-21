package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

//sluzi samo za editovanje cvorova u stablu
public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;
    private JTextField edit = null;

    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    //treba da prikaze zamenski oblik
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5); //?
        // todo videti sta je ovo
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    //treba da kaze na koju akciju se Cell otvara
    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent) arg0).getClickCount() == 3) {
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e) {

        if (!(clickedOn instanceof ClassyTreeItem))
            return;

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());
        clicked.getClassyNode().setName(e.getActionCommand());
        //clicked.getClassyNode().notifySubscribers(e.getActionCommand()); ovo treba u ClassyNode da ide (u setName)
    }
}