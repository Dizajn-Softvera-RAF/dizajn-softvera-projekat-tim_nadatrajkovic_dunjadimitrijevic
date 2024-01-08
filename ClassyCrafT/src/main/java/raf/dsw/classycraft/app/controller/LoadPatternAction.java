package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
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
                Diagram pattern = ApplicationFramework.getInstance().getSerializer().loadPattern(file);
                Diagram.smanjiBrojac();
                copyPatternIntoSelectedDiagram(pattern,(Diagram)selected.getClassyNode());
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
                MainFrame.getInstance().getClassyTree().loadPattern((Diagram)selected.getClassyNode());
                selected.getClassyNode().notifySubscribers(new Notification(null, NotificationType.LOADED_PATTERN));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void copyPatternIntoSelectedDiagram(Diagram pattern, Diagram selectedDiagram)
    {
        selectedDiagram.copyPattern(pattern);
    }
}
