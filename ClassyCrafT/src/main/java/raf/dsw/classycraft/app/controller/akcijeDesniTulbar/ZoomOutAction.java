package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractClassyAction {
    public ZoomOutAction() {
        putValue(SMALL_ICON, loadIcon("/images/zoomout.png"));
        putValue(NAME, "zoom out");
        putValue(SHORT_DESCRIPTION, "zoom out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().StartZoomOutState();
    }
}
