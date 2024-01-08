package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

public class SavePatternAction extends AbstractClassyAction {

    public SavePatternAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(NAME, "Save diagram");
        putValue(SHORT_DESCRIPTION, "Save diagram as a pattern");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        if(jfc.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION)
        {
            ApplicationFramework.getInstance().getSerializer().saveDiagramAsPattern((Diagram)selected.getClassyNode(), jfc.getSelectedFile());

        }

    }
}
