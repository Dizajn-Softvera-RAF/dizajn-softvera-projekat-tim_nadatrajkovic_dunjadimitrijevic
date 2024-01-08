package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.AboutUsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "AboutUs tekst i slika");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsWindow aboutUs=new AboutUsWindow();
        aboutUs.setVisible(true);
    }
}
