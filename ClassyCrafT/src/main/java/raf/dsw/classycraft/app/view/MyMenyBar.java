package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAddNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAddAutorAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        JMenuItem aboutusItem = new JMenuItem("About Us");
        editMenu.add(aboutusItem);
        add(editMenu);


        //aboutusItem.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        aboutusItem.addActionListener(MainFrame.getInstance().getActionManager().getAboutUsAction());
    }

}
