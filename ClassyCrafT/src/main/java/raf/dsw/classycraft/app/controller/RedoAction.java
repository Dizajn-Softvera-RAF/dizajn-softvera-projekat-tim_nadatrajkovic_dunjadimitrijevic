package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractClassyAction{
    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(SMALL_ICON, loadIcon("/images/editredo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        //ovde dobijemo diagramView i pozivamo njegov commandManager i doCommand

        //ApplicationFramework.getInstance().getCommandManager().doCommand();
        MainFrame.getInstance().getPackageView().trenutniDv().getCommandManager().doCommand();
    }

}
