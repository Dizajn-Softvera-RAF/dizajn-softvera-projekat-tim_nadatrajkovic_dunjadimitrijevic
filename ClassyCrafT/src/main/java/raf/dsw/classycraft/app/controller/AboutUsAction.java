package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.AboutUsWindow;
import raf.dsw.classycraft.app.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction()
    {



        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/exit.png"));
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "AboutUs tekst i slika");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Pokretanjem te akcije (klikom na opciju iz menija) aplikacija treba da izbaci novi prozor koji će sadržati imena i prezimena studenata koji rade na
        //projektu kao i njihove slike raspoređene na vama željeni način


        AboutUsWindow aboutUs=new AboutUsWindow();

        aboutUs.setVisible(true);

    }
}
