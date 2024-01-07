package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class LoadAction extends AbstractClassyAction {
    public LoadAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    public void actionPerformed(ActionEvent arg0) {

        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getClassyTree().loadProject(p);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
