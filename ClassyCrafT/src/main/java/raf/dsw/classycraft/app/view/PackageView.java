package raf.dsw.classycraft.app.view;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.Observer.Notification;
import raf.dsw.classycraft.app.Observer.NotificationType;
import raf.dsw.classycraft.app.controller.stateSablon.StateManager;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.composite_implementation.Diagram;
import raf.dsw.classycraft.app.model.composite_implementation.Package;
import raf.dsw.classycraft.app.model.composite_implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PackageView extends JPanel implements ISubscriber {


    private JPanel desktopinfo;
    private Label project;
    private Label author;
    private JPanel tabs_panel;

    private JTabbedPane tabs;

    private Package paket; // paket koji se trenutno prikazuje

    private StateManager stateManager; //packageView je moderator za state sablon

    private DesniToolBar desniToolBar;

    private ArrayList<DiagramView> tabNames;




    public PackageView() {
        super();
        desktopinfo = new JPanel();
        desktopinfo.setLayout(new BoxLayout(desktopinfo, BoxLayout.Y_AXIS));
        project = new Label("Current project: ");
        author = new Label("Author: ");
        desktopinfo.add(project);
        desktopinfo.add(author);

        this.setLayout(new BorderLayout());
        this.add(desktopinfo, BorderLayout.NORTH);
        tabs_panel = new JPanel();
        tabs_panel.setLayout(new BorderLayout());
        this.add(tabs_panel, BorderLayout.CENTER);
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        //tabs.setSize(500,300);
        tabNames=new ArrayList<DiagramView>();

        tabs_panel.add(tabs);

        stateManager = new StateManager();

        desniToolBar=new DesniToolBar();
        add(desniToolBar, BorderLayout.EAST);
    }

    public Package getPaket() {
        return paket;
    }

    public void setPaket(Package paket) {
        this.paket = paket;
        this.Update(new Notification(paket, NotificationType.SHOW));
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setAutor(String autor) {
        author.setText("Author: " + autor);
    }

    public void setProjectNaziv(String naziv) {
        project.setText("Current project: " + naziv);
    }

    private void showComponents() {
        project.setVisible(true);
        author.setVisible(true);
        tabs_panel.setVisible(true);
        tabs.setVisible(true);
    }

    private void createTabs() {
        this.getTabs().removeAll();
        for (DiagramView dv : tabNames) {
            this.getTabs().addTab(dv.getDiagram().getName(), dv);
        }
    }

    private void hideComponents() {

    }

    @Override
    public void Update(Object notification) {
        System.out.println("usao u PackageView update()" + notification.toString());


        /// Mora da se sredi sa Notification
        if (notification instanceof Notification) {
            if (((Notification) notification).getNotificationType() == NotificationType.SHOW) {
                MainFrame.getInstance().setPackageView(this);
                System.out.println(paket.getName());
                //Package currentPackage = (Package) ((Notification) notification).getObjectOfNotification();
                //ClassyNode node = (ClassyNode) ((Notification) notification).getObjectOfNotification();
                ClassyNode node = (ClassyNode) paket;

                while (!(node instanceof Project)) {
                    node = node.getParent();
                }
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                node.addSubscriber(this);
//                if(tabs.getTabCount() > 0)
//                {
//                    showComponents();
//                    this.setProjectNaziv(((Project) node).getName());
//                    this.setAutor(((Project) node).getImeAutora());
//                    return;
//                }
                ClassyNodeComposite cp = (ClassyNodeComposite) paket;
                //tabNames = new ArrayList<>();


                for (ClassyNode c : cp.getChildren()) {
                    if (c instanceof Diagram) {
                        //((Diagram)c).addSubscriber(this); // ?
                        DiagramView dw = new DiagramView((Diagram) c);
                        //dw.Update(new Notification(c, NotificationType.SHOWDIAGRAM));
                        c.addSubscriber(dw);
                        //tabs.add(dw);
                        //cp.addSubscriber(dw); // jel mi treba ovo?
                        tabNames.add(dw);
                    }

                }
                createTabs();
                this.setProjectNaziv(((Project) node).getName());
                this.setAutor(((Project) node).getImeAutora());
                showComponents();

            } else if (((Notification) notification).getNotificationType() == NotificationType.RENAME) {
                if (((Notification) notification).getObjectOfNotification() instanceof Project) // za promenu imena Autora projekta i imena projekta
                {
                    this.setAutor(((Project) ((Notification) notification).getObjectOfNotification()).getImeAutora());
                    this.setProjectNaziv(((Project) ((Notification) notification).getObjectOfNotification()).getName());
                } else if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {

                }
            } else if (((Notification) notification).getNotificationType() == NotificationType.ADD) {
                if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {
                    Diagram d = (Diagram) ((Notification) notification).getObjectOfNotification();
                    if (d.getParent() == paket) {
                        System.out.println("usao u 'jeste se dodalo na otvoreni paket' ");
                        DiagramView dv = new DiagramView(d);
                        //d.addSubscriber(this);
                        System.out.println(d.getSubscriberList());
                        d.addSubscriber(dv);
                        System.out.println(d.getSubscriberList());
                        //dv.Update(new Notification(d, NotificationType.SHOWDIAGRAM));
                        //paket.addSubscriber(dv);
                        this.getTabs().addTab(dv.getDiagram().getName(), dv);
                    }


                    //tabs.add(dw);


                }
            } else if (((Notification) notification).getNotificationType() == NotificationType.DELETE) {
                if (((Notification) notification).getObjectOfNotification() instanceof Project) {
                    // vrv dopuniti kasnije
                    hideComponents();
                    return;
                }
                if (((Notification) notification).getObjectOfNotification() instanceof Package) {
                    // vrv dopuniti kasnije
                    hideComponents();
                    return;
                }
                if (((Notification) notification).getObjectOfNotification() instanceof Diagram) {
                    for (int i = 0; i < tabs.getTabCount(); i++) {
                        DiagramView dv = (DiagramView) tabs.getComponentAt(i);
                        if (dv.getDiagram().getName().equals(((Diagram) ((Notification) notification).getObjectOfNotification()).getName())) {
                            tabs.remove(i);
                            return;
                        }
                    }
                }

            }

        }
//        else if(notification.toString().equals("brisi"))
//        {
////            //removeAll();
////            //tabs.remove(DiagramView);
////            repaint();
////            revalidate();
//        }
//        else if(notification instanceof String) // sredi ove notifikacije, da se zna sta je sta
//        {
//            //setProjectNaziv(notification.toString());
//        }
//        else if(notification instanceof Diagram)
//        {
//            //DiagramView dw = new DiagramView();
//            //this.getTabs().addTab(((Diagram) notification).getName(), dw);
//            //MainFrame.getInstance().getPackageView().getTabs().addTab(((Diagram) notification).getName(), dw);
//            //((Diagram)notification).addSubscriber(dw);
//        }


    }

    //funkcije za stateManager
    public void StartDodajConnectionState()
    {
        stateManager.setDodajConnectionState();
    }

    public void StartDodajInterclassState()
    {
        stateManager.setDodajInterclassState();
    }

    public void StartObrisiState()
    {
        stateManager.setObrisiState();
    }
    public void StartPromeniKlasuState()
    {
        stateManager.setPromeniKlasuState();
    }

    public void StartSelektujState()
    {
        stateManager.setSelektujState();
    }

    public void misPritisnutmng(Point P, DiagramView dv) // mozda preimenovati u misPritisnutMng da odgovara ostalim analognim metodama
    {
        stateManager.getCurrentState().misPritisnut(P,dv);
    }
    public void misPovucenmng(Point P, DiagramView dv)
    {
        stateManager.getCurrentState().misPovucen(P,dv);
    }
    public void misOtpustenmng(Point P, DiagramView dv)
    {
        stateManager.getCurrentState().misOtpusten(P,dv);
    }
}
