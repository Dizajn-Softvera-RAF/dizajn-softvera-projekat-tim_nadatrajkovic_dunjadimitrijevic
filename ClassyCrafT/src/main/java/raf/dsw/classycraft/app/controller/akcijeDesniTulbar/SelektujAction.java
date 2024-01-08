package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelektujAction extends AbstractClassyAction {
    public SelektujAction() {
        putValue(SMALL_ICON, loadIcon("/images/select.png"));
        putValue(NAME, "selektuj");
        putValue(SHORT_DESCRIPTION, "selektuj");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().StartSelektujState();
    }
}
