package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction{

    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("/images/editundo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        //ovde dobijemo diagramView i pozivamo njegov commandManager i undoCommand
        MainFrame.getInstance().getPackageView().trenutniDv().getCommandManager().undoCommand();
    }
}
