package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.message.MessageType;
import raf.dsw.classycraft.app.view.AboutUsWindow;
import raf.dsw.classycraft.app.view.AddAutorWindow;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddAutorAction extends AbstractClassyAction{


    public AddAutorAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Autor");
        putValue(SHORT_DESCRIPTION, "Add/change Autor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getClassyTree().getSelectedNode()==null)
        {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage("niste izabrali projekat", MessageType.ERROR);
            return;
        }
        AddAutorWindow autorWindow=new AddAutorWindow(MainFrame.getInstance(),MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode());


        autorWindow.setVisible(true);

    }


}
