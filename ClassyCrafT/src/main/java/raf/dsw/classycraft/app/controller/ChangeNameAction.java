package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.view.AddAutorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNameAction implements ActionListener{
    Project project;
    String tekst;
    AddAutorWindow dialog;

    public ChangeNameAction(AddAutorWindow dialog, Project project) {
        this.project = project;

        System.out.println("napravio changenameacton + " + this.tekst );
        this.dialog=dialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.tekst = dialog.textFromField();
        System.out.println("uso u action prerformed1"+tekst);

        project.setImeAutora(tekst);
        System.out.println("uso u action prerformed"+tekst);

        Component component = (Component) e.getSource();
        JDialog dialog1 = (JDialog) SwingUtilities.getRoot(component);
        dialog1.dispose();
        dialog.dispose();
    }
}
