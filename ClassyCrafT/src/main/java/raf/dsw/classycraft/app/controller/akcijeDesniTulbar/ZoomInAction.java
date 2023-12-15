package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractClassyAction {
    public ZoomInAction() {
        putValue(SMALL_ICON, loadIcon("/images/zoomin.png"));
        putValue(NAME, "zoom in");
        putValue(SHORT_DESCRIPTION, "zoom in");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // PackageView statemanager start state
        MainFrame.getInstance().getPackageView().StartZoomInState();

    }
}
