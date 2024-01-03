package raf.dsw.classycraft.app.model.composite_implementation.diagramElementi;

import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.sadrzajInterclass.Atribut;
import raf.dsw.classycraft.app.model.sadrzajInterclass.ClassContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {

    List<ClassContent> sadrzaj;
    InterclassVidljivost vidljivost;
    private Point pocetnaTacka;
    private Point krajnjaTacka;
    private int width;
    private int height;
    private ArrayList<Point> connectionPoints;  //nekako da cuva koji su zauzeti da ne pravi veze u istoj tacki

    public ArrayList<Point> getConnectionPoints() {
        return connectionPoints;
    }

    public void setConnectionPoints(ArrayList<Point> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public Interclass(String name, ClassyNode parent, Point pocetnaTacka) {
        super(name, parent);
        vidljivost=InterclassVidljivost.PUBLIC;
        sadrzaj=new ArrayList<>();
        this.pocetnaTacka = pocetnaTacka;
        connectionPoints = new ArrayList<>();
    }

    private void dodajConnectonPoints()
    {
        connectionPoints.clear();
        int xSkok=width/4;
        int ySkok=height/4;

        for(int i=0;i<=4;i++)
        {
            for(int j=0;j<=4;j++)
            {
                if(!(j==1 && i==1))
                    connectionPoints.add(new Point(pocetnaTacka.x+i*xSkok,pocetnaTacka.y+j*ySkok));
            }
        }
    }

//    public Interclass(String name, ClassyNode parent, InterclassVidljivost vidljivost, Point pocetnaTacka) {
//        super(name, parent);
//        this.vidljivost=vidljivost;
//        sadrzaj=new ArrayList<>();
//        this.pocetnaTacka = pocetnaTacka;
//    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    public void addClassContent(ClassContent classContent)
    {
        sadrzaj.add(classContent);
        //notifySubscribers();
    }

    public Point getPocetnaTacka() {
        return pocetnaTacka;
    }

    public void setPocetnaTacka(Point pocetnaTacka) {
        this.pocetnaTacka = pocetnaTacka;
        notifySubscribers(new Notification(this, NotificationType.MOVE)); //todo obavesti subscribera (interclass painter) da se pomerio interclass
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getKrajnjaTacka() { // todo videti kako width, heigth i krajnju tacku resiti u peinteru
        return krajnjaTacka;
    }

    public void setKrajnjaTacka(int w, int h) {
        width = w;
        height = h;
        this.krajnjaTacka = new Point(pocetnaTacka.x + width, pocetnaTacka.y + height);
        this.dodajConnectonPoints();
    }

    public InterclassVidljivost getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(InterclassVidljivost vidljivost) {
        this.vidljivost = vidljivost;
    }

    public List<ClassContent> getClassContent() {
        return sadrzaj;
    }

    public void setSadrzaj(List<ClassContent> sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
    //public abstract void changeClassContent(ClassContent classContentkoji, ClassContent novi);

}
