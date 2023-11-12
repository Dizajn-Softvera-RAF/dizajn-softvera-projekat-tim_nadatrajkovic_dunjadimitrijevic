package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber {

    //ovde crta dijagram eto


    public DiagramView() {
        super();

    }

    @Override
    public void Update(Object notification) {
        if(notification.toString().equals("brisi"))
        {
            removeAll();
            repaint();
            revalidate();
        }
    }
}
