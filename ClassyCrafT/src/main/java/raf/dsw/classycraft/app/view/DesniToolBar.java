package raf.dsw.classycraft.app.view;

import javax.swing.*;
import java.awt.*;

public class DesniToolBar extends JToolBar {
    public DesniToolBar() {
        super(VERTICAL);
        setFloatable(false);

        //setBackground(Color.BLUE);

        //setAlignmentX(getParent().getWidth()-this.getWidth());

        add(MainFrame.getInstance().getActionManager().getDodajConnectionAction());
        add(MainFrame.getInstance().getActionManager().getDodajInterclassAction());
        add(MainFrame.getInstance().getActionManager().getObrisiAction());
        add(MainFrame.getInstance().getActionManager().getPromeniKlasuAction());
        add(MainFrame.getInstance().getActionManager().getSelektujAction());

    }
}
