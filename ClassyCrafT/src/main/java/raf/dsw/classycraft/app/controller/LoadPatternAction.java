package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

public class LoadPatternAction extends AbstractClassyAction {
    public LoadPatternAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Diagram patterns");
        putValue(SHORT_DESCRIPTION, "Open diagram pattern");
    }

    public void actionPerformed(ActionEvent arg0) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || !(selected.getClassyNode() instanceof Diagram))
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Nije izabran dijagram", MessageType.ERROR);
            return;
        }
        JFileChooser jfc = new JFileChooser();
        URL url = getClass().getResource("/savedPatterns");
        if(url != null)
        {
            jfc.setCurrentDirectory(new File(url.getFile()));
        }
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Diagram d = ApplicationFramework.getInstance().getSerializer().loadPattern(file);
                //MainFrame.getInstance().getClassyTree().loadProject(p);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //FileChooser jfc = new JFileChooser();
    }

    private void copyPatternIntoSelectedDiagram(Diagram pattern, Diagram selectedDiagram)
    {

    }
}
