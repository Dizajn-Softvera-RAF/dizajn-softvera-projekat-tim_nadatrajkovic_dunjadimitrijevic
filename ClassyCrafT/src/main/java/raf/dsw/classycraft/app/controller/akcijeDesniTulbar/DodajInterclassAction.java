package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class DodajInterclassAction extends AbstractClassyAction {
    public DodajInterclassAction() {
        putValue(SMALL_ICON, loadIcon("/images/interclass.png"));
        putValue(NAME, "dodaj klasu ");
        putValue(SHORT_DESCRIPTION, "dodaj klasu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // PackageView statemanager start state
        MainFrame.getInstance().getPackageView().StartDodajInterclassState();

    }
}
