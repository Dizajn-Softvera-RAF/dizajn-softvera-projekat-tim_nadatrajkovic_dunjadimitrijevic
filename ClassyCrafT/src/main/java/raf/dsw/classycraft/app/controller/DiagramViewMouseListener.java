package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class DiagramViewMouseListener extends MouseAdapter implements MouseMotionListener {

    private DiagramView diagramView;
    private Point pocetneKoordinate;
    private Point krajnjeKoordinate;

    public Point getPocetneKoordinate() {
        return pocetneKoordinate;
    }

    public void setPocetneKoordinate(Point pocetneKoordinate) {
        this.pocetneKoordinate = pocetneKoordinate;
    }

    public Point getKrajnjeKoordinate() {
        return krajnjeKoordinate;
    }

    public void setKrajnjeKoordinate(Point krajnjeKoordinate) {
        this.krajnjeKoordinate = krajnjeKoordinate;
    }

    public DiagramViewMouseListener(DiagramView diagramView) {
        super();
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        AffineTransform af=diagramView.getAf();
        Point p=e.getPoint();
        if(af!=null)
        {
            try {
                af.inverseTransform(p,p);
            } catch (NoninvertibleTransformException ex) {
                throw new RuntimeException(ex);
            }
        }
        pocetneKoordinate=p;
        System.out.println("MOUSE PRESSED " + MainFrame.getInstance().getPackageView().getStateManager().toString());
        MainFrame.getInstance().getPackageView().misPritisnutmng(pocetneKoordinate,diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        AffineTransform af=diagramView.getAf();
        Point p=e.getPoint();
        if(af!=null)
        {
            try {
                af.inverseTransform(p,p);
            } catch (NoninvertibleTransformException ex) {
                throw new RuntimeException(ex);
            }
        }
        pocetneKoordinate=p;

        System.out.println("MOUSE DRAGGED " + MainFrame.getInstance().getPackageView().getStateManager().toString());

        MainFrame.getInstance().getPackageView().misPovucenmng(pocetneKoordinate,diagramView);

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        AffineTransform af=diagramView.getAf();
        Point p=e.getPoint();
        if(af!=null)
        {
            try {
                af.inverseTransform(p,p);
            } catch (NoninvertibleTransformException ex) {
                throw new RuntimeException(ex);
            }
        }
        krajnjeKoordinate=p;
        System.out.println("MOUSE RELEASED " + MainFrame.getInstance().getPackageView().getStateManager().toString());
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
    }
}
