package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        //ExitAction ea = new ExitAction();
        add(MainFrame.getInstance().getActionManager().getExitAction());
    }
}
