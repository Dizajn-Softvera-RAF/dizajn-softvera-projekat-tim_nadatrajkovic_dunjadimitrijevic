package raf.dsw.classycraft.app.controller.stateSablon;

import raf.dsw.classycraft.app.view.DiagramView;

import java.awt.*;

public interface State {

    public void misKliknut(Point P, DiagramView dv);

    public void misPovucen(Point P, DiagramView dv);
    public void misOtpusten(Point P, DiagramView dv);

}
