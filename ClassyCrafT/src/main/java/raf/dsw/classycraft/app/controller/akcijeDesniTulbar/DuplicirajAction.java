package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class DuplicirajAction extends AbstractClassyAction {
    public DuplicirajAction() {
        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME, "dupliciraj");
        putValue(SHORT_DESCRIPTION, "dupliciraj");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().StartDuplicirajState();
    }
}
