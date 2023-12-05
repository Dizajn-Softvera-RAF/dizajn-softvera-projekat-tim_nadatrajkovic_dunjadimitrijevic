package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class ObrisiAction extends AbstractClassyAction {

    public ObrisiAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantadiagram.png"));
        putValue(NAME, "obrisi");
        putValue(SHORT_DESCRIPTION, "obrisi");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // PackageView statemanager start state
        MainFrame.getInstance().getPackageView().StartObrisiState();

    }

}
