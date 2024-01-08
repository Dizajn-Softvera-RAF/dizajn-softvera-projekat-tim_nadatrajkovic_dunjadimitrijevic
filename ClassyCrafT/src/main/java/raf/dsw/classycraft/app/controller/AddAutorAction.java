package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.composite_implementation.Project;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.AddAutorWindow;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddAutorAction extends AbstractClassyAction{


    public AddAutorAction() {
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Autor");
        putValue(SHORT_DESCRIPTION, "Add/change Autor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getClassyTree().getSelectedNode()==null)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("niste izabrali projekat", MessageType.ERROR);
        }
        else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project )
        {
            AddAutorWindow autorWindow = new AddAutorWindow(MainFrame.getInstance(), MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode());
            autorWindow.setVisible(true);
        }
        else
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("samo projekat ima ime autora", MessageType.ERROR);
        }
    }

}
