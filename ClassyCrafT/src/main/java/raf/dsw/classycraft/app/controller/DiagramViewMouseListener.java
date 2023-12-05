package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

public class DiagramViewMouseListener extends MouseAdapter {

    private DiagramView diagramView;
    private Point pocetneKoordinate;
    private Point krajnjeKoordinate;

    public DiagramViewMouseListener(DiagramView diagramView) {
        super();
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misKliknutmng(e.getPoint(),diagramView);
        //diagramView.update();
        diagramView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        MainFrame.getInstance().getPackageView().misPovucenmng(e.getPoint(),diagramView);
        //diagramView.update();
        diagramView.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        pocetneKoordinate=e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        krajnjeKoordinate=e.getPoint();
        MainFrame.getInstance().getPackageView().misOtpustenmng(krajnjeKoordinate,diagramView);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
