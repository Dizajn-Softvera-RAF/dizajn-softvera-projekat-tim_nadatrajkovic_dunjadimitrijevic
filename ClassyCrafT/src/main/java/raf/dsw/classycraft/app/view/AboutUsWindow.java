package raf.dsw.classycraft.app.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AboutUsWindow extends JFrame {


    public AboutUsWindow() throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2 , screenHeight/2);
        setLocationRelativeTo(null);
        setTitle("About Us");

        System.out.println("uso u konstruktor");


        URL imageURL = getClass().getResource("/images/slikaAboutUs.jpeg");
        ImageIcon icon = null;

        if(imageURL != null)
        {
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resource not found: " + "/images/slikaAboutUs.jpeg");
        }

        JLabel labelaSlika = new JLabel("Nada Trajković (levo) i Dunja Dimitrijević (desno)",icon,JLabel.CENTER);
        this.add(labelaSlika);
        labelaSlika.setVisible(true);

    }



}
