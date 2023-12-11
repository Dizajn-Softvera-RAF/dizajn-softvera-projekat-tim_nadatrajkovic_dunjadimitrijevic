package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.view.DiagramView;
import raf.dsw.classycraft.app.view.MainFrame;

import java.awt.*;
import java.awt.event.*;

public class DiagramViewMouseListener extends MouseAdapter implements MouseMotionListener {

    private DiagramView diagramView;
    private Point pocetneKoordinate;
    private Point krajnjeKoordinate;

    public DiagramViewMouseListener(DiagramView diagramView) {
        super();
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        //OVO JE NIJE BILO IZKOMENTARISANO,  prebacila sam ovo u mousePressed
//        MainFrame.getInstance().getPackageView().misPritisnutmng(e.getPoint(),diagramView);
//            //diagramView.update(); (ovo jeste)
//        diagramView.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        pocetneKoordinate=e.getPoint();
        System.out.println("MOUSE PRESSED");
        MainFrame.getInstance().getPackageView().misPritisnutmng(e.getPoint(),diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        System.out.println("MOUSE DRAGGED");
//
//        MainFrame.getInstance().getPackageView().misPovucenmng(e.getPoint(),diagramView);
        //diagramView.update();
        //diagramView.repaint();

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        krajnjeKoordinate=e.getPoint();
        System.out.println("MOUSE RELEASED");
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
//        //super.mouseMoved(e);
//        System.out.println("MOUSE MOVED");
//        MainFrame.getInstance().getPackageView().misPovucenmng(e.getPoint(),diagramView);
//        //diagramView.update();
//        diagramView.repaint();
//        System.out.println("uso u listener mmoved");
    }
}
