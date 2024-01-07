package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveImageAction extends AbstractClassyAction{

    public SaveImageAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save_as_img.png"));
        putValue(NAME, "Save diagram as an image");
        putValue(SHORT_DESCRIPTION, "Save diagram as an image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram))
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Nije izabran dijagram", MessageType.ERROR);
            return;
        }
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg", "bmp"));

        Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();

        DiagramView dv = MainFrame.getInstance().getPackageView().diagramViewOfDiagram(diagram);
        File pictureFile = null;
        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            pictureFile = jfc.getSelectedFile();

        } else {
            return;
        }
        String filePath = pictureFile.getPath();

        dv.saveDiagramAsImage(filePath);


    }
}
