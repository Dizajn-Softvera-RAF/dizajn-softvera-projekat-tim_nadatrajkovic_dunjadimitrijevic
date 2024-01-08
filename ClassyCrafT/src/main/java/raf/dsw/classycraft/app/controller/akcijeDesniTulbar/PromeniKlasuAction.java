package raf.dsw.classycraft.app.controller.akcijeDesniTulbar;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.event.ActionEvent;

public class PromeniKlasuAction extends AbstractClassyAction {
    public PromeniKlasuAction() {
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "promeni");
        putValue(SHORT_DESCRIPTION, "promeni");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().StartPromeniKlasuState();
    }
}
