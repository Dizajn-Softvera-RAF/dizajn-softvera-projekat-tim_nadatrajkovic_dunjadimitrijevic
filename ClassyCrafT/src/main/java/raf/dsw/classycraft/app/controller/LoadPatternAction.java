package raf.dsw.classycraft.app.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LoadPatternAction extends AbstractClassyAction {
    public LoadPatternAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Diagram patterns");
        putValue(SHORT_DESCRIPTION, "Open diagram pattern");
    }

    public void actionPerformed(ActionEvent arg0) {

    }
}
