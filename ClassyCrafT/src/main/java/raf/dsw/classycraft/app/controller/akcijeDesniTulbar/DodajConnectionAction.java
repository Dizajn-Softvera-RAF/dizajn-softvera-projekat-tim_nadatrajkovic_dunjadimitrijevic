package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class DodajConnectionAction extends AbstractClassyAction {


    public DodajConnectionAction() {
        putValue(SMALL_ICON, loadIcon("/images/connection.png"));
        putValue(NAME, "dodaj vezu");
        putValue(SHORT_DESCRIPTION, "dodaj vezu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().StartDodajConnectionState();
    }
}
