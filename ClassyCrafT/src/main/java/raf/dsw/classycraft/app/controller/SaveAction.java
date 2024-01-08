package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAction extends AbstractClassyAction{

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    public void actionPerformed(ActionEvent arg0) {
        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project))
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Nije izabran projekat", MessageType.ERROR);
            return;
        }

        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        File projectFile = null;

        if (!project.isChanged()) {
            return;
        }

        if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            } else {
                return;
            }

        }

        if(projectFile == null)
            projectFile = new File(project.getFilePath());

        ApplicationFramework.getInstance().getSerializer()
                .saveProject(project,projectFile);

        ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("Projekat je sacuvan.", MessageType.INFO);

        project.setChanged(false);
    }
}
