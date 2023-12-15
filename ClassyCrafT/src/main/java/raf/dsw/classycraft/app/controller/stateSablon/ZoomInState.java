package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.view.DiagramView;

import java.awt.*;

public class ZoomInState implements State {


    @Override
    public void misPritisnut(Point P, DiagramView dv) {
        dv.setZoomFactor(dv.getZoomFactor()*1.1);
        dv.setZoomer(true);
        dv.repaint();
    }

    @Override
    public void misPovucen(Point P, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point P, DiagramView dv) {

    }
}
