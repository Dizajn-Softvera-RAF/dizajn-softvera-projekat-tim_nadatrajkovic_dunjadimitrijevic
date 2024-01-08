package raf.dsw.classycraft.app.view;

import javax.swing.*;

public class DesniToolBar extends JToolBar {
    public DesniToolBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getDodajConnectionAction());
        add(MainFrame.getInstance().getActionManager().getDodajInterclassAction());
        add(MainFrame.getInstance().getActionManager().getObrisiAction());
        add(MainFrame.getInstance().getActionManager().getPromeniKlasuAction());
        add(MainFrame.getInstance().getActionManager().getSelektujAction());
        add(MainFrame.getInstance().getActionManager().getDuplicirajAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());

    }
}
